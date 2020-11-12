/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploauth.servlet;

import br.senac.tads.pi3.exemploauth.usuario.UsuarioSistema;
import br.senac.tads.pi3.exemploauth.usuario.UsuarioSistemaService;
import br.senac.tads.pi3.exemploauth.usuario.UsuarioSistemaServiceMockImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fedts
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        
        // 1) Verificar se existe usuario com username informado
        UsuarioSistemaService usuarioService = new UsuarioSistemaServiceMockImpl();
        UsuarioSistema usuario = usuarioService.findByUsername(username);
        
        if (usuario != null && usuario.validarSenha(senha)) {
            // 2) Se usuario não for nulo e a senha for valida, coloca usuario
            // na Sessao e redireciona para /home
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // 3) Se usuario não for encontrado ou a senha for inválida,
            // reapresenta tela de login com msg de erro.
            request.setAttribute("msgErro", "Usuário ou senha inválido");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

}
