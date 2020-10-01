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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExemploServlet", urlPatterns = "/exemplo-servlet")
public class ExemploServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String ua = request.getHeader("User-Agent");
        LocalDateTime dataHora = LocalDateTime.now();
        
        Dados dados = new Dados();
        dados.setId(1);
        dados.setNome("Fulano da Silva");
        dados.setDataNascimento(LocalDate.of(2000, 10, 10));
        dados.setPeso(new BigDecimal(80.5));
        dados.setAltura(new BigDecimal(1.75));
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exemplo Servlet</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exemplo Servlet</h1>");
            out.println("<p>Data e hora: " + dataHora + "</p>");
            out.println("<p>User agent: " + ua + "</p>");
            out.println("<hr>");
            out.println("<h2>" + dados.getNome() + "</h2>");
            out.println("<p>Data nascimento: " + dados.getDataNascimento() + "</p>");
            out.println("<p>Idade: " + dados.getIdade() + "</p>");
            out.println("<p>Altura: " + dados.getAltura() + "</p>");
            out.println("<p>Peso: " + dados.getPeso() + "</p>");
            out.println("<p>IMC:" + dados.getImc() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
}
