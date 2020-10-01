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
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ExemploMvcPlus", urlPatterns = {"/exemplo-mvc-plus"})
public class ExemploMvcPlus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Recuperar dados queryString
        String nome = request.getParameter("nome");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        String alturaStr = request.getParameter("altura");
        String pesoStr = request.getParameter("peso");
        
        String[] interesses = new String[] { "Esportes", "Comida", "Baladas" };

        String ua = request.getHeader("User-Agent");
        LocalDateTime dataHora = LocalDateTime.now();
        
        Dados dados = new Dados();
        dados.setId(1);
        dados.setNome(nome);
        dados.setDataNascimento(LocalDate.parse(dataNascimentoStr));
        dados.setAltura(new BigDecimal(alturaStr));
        dados.setPeso(new BigDecimal(pesoStr));
        dados.setInteresses(interesses);
        
        request.setAttribute("uaAttr", ua);
        request.setAttribute("dataHoraAttr", dataHora);
        request.setAttribute("dadosAttr", dados);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/exemplo-mvc-plus.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
