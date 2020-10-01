/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosweb;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExemploMvc", urlPatterns = {"/exemplo-mvc"})
public class ExemploMvc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String ua = request.getHeader("User-Agent");
        LocalDateTime dataHora = LocalDateTime.now();
        
        Dados dados = new Dados();
        dados.setId(1);
        dados.setNome("Beltrano dos Santos");
        dados.setDataNascimento(LocalDate.of(1999, 8, 15));
        dados.setPeso(new BigDecimal(85.6));
        dados.setAltura(new BigDecimal(1.78));
        
        request.setAttribute("uaAttr", ua);
        request.setAttribute("dataHoraAttr", dataHora);
        request.setAttribute("dadosAttr", dados);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("exemplo-mvc.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
