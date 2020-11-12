/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploauth.filter;

import br.senac.tads.pi3.exemploauth.usuario.UsuarioSistema;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fedts
 */
@WebFilter(filterName = "AutorizacaoFilter", urlPatterns = { "/protegido/*" }, servletNames = { "HomeServlet" } )
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // CAST para objetos do tipo HttpServlet*
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 1) Verificar se usuário está logado
        HttpSession sessao = httpRequest.getSession();
        if (sessao.getAttribute("usuario") == null) {
            // Usuario não logado -> redirecionar para tela de login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");
        
        // 2) Verificar se usuario possui papel para acessar funcionalidade
        if (verificarAcesso(usuario, httpRequest)) {
            // USUARIO PODE ACESSAR FUNCIONALIDADE -> Requisição é encaminhada para Servlet
            chain.doFilter(request, response);
        } else {
            // Acesso do Usuário não autorizado -> Mostra tela de erro
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/erro-nao-autorizado.jsp");
        }
        
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
    
    private boolean verificarAcesso(UsuarioSistema usuario, HttpServletRequest httpRequest) {
        // RECUPERAR A URL USADA NO ACESSO AO SISTEMA
        String paginaAcessada = httpRequest.getRequestURI();
        System.out.println("***** PAGINA ACESSADA: " + paginaAcessada);
        if (paginaAcessada.endsWith("/home")) {
            return true;
        } else if (paginaAcessada.endsWith("/protegido/peao-page")
                && usuario.verificarPapel("PEAO")) {
            return true;
        } else if (paginaAcessada.endsWith("/protegido/fodon-page")
                && usuario.verificarPapel("FODON")) {
            return true;
        } else if (paginaAcessada.endsWith("/protegido/god-page")
                && usuario.verificarPapel("GOD")) {
            return true;
        }
        return false;
    }
}
