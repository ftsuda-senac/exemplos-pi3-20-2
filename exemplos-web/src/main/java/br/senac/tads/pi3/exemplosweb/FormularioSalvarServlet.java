/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FormularioSalvarServlet", urlPatterns = {"/formulario-salvar"})
public class FormularioSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Recuperando dados preenchidos no formulário
        String nome = request.getParameter("nome");
        String dtNascimentoStr = request.getParameter("dataNascimento");
        String alturaStr = request.getParameter("altura");
        String pesoStr = request.getParameter("peso");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String generoStr = request.getParameter("genero");
        
        String[] interesses = request.getParameterValues("interesses");
        
        DadosPlus dados = new DadosPlus();
        dados.setNome(nome);
        dados.setDataNascimento(LocalDate.parse(dtNascimentoStr));
        dados.setAltura(new BigDecimal(alturaStr));
        dados.setPeso(new BigDecimal(pesoStr));
        dados.setInteresses(interesses);
        dados.setEmail(email);
        dados.setSenha(senha);
        if ("1".equals(generoStr)) {
            dados.setGenero(1);
        } else {
            dados.setGenero(0);
        }
        request.setAttribute("dados", dados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
        dispatcher.forward(request, response);
    }

}
