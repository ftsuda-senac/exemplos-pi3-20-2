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
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FormValidacaoSalvarServlet", urlPatterns = {"/form-validacao-salvar"})
public class FormValidacaoSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        DadosPlus dados = (DadosPlus) sessao.getAttribute("dados");
        sessao.removeAttribute("dados");

        request.setAttribute("dados", dados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
        dispatcher.forward(request, response);
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

        BigDecimal altura = null;
        if (alturaStr != null && alturaStr.trim().length() > 0) {
            altura = new BigDecimal(alturaStr);
        }
        BigDecimal peso = null;
        if (pesoStr != null && pesoStr.trim().length() > 0) {
            peso = new BigDecimal(pesoStr);
        }
        LocalDate dataNascimento = null;
        if (dtNascimentoStr != null && dtNascimentoStr.trim().length() > 0) {
            dataNascimento = LocalDate.parse(dtNascimentoStr);
        }

        // Validação do nome
        boolean nomeValido = (nome != null && nome.trim().length() > 0);

        // Validacao do e-mail
        boolean emailValido = (email != null && email.trim().length() > 0);
        if (emailValido) {
            Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
            Matcher emailMatcher = emailPattern.matcher(email);
            emailValido = emailValido && emailMatcher.matches();
        }

        // Validação da altura
        boolean alturaValida = (altura != null && altura.compareTo(new BigDecimal("4.00")) < 0);

        // Validacao do peso
        boolean pesoValido = (peso != null && peso.compareTo(new BigDecimal("500.0")) < 0);

        // Validação da data de nascimento (deve estar no passado)
        boolean dataNascimentoValida = (dataNascimento != null && dataNascimento.isBefore(LocalDate.now()));

        boolean camposValidosGlobal = nomeValido && emailValido && alturaValida && pesoValido && dataNascimentoValida;
        if (!camposValidosGlobal) {
            // Reapresentar form com mensagens de erro
            if (!nomeValido) {
                request.setAttribute("nomeErro", "Nome deve ser preenchido");
            }
            if (!emailValido) {
                request.setAttribute("emailErro", "E-mail deve ser preenchido");
            }
            if (!alturaValida) {
                request.setAttribute("alturaErro", "Altura inválida");
            }
            if (!pesoValido) {
                request.setAttribute("pesoErro", "Peso inválido");
            }
            if (!dataNascimentoValida) {
                request.setAttribute("dataNascimentoErro", "Data de nascimento inválida");
            }
            request.setAttribute("nome", nome);
            request.setAttribute("dataNascimento", dtNascimentoStr);
            request.setAttribute("altura", alturaStr);
            request.setAttribute("peso", pesoStr);
            request.setAttribute("email", email);
            request.setAttribute("genero", generoStr);
            List<String> interessesList = Arrays.asList(interesses);
            request.setAttribute("interesses", interessesList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-validacao.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // TODO: salvar no banco de dados
        DadosPlus dados = new DadosPlus();
        dados.setNome(nome);
        dados.setDataNascimento(dataNascimento);
        dados.setAltura(altura);
        dados.setPeso(peso);
        dados.setInteresses(interesses);
        dados.setEmail(email);
        dados.setSenha(senha);
        if ("1".equals(generoStr)) {
            dados.setGenero(1);
        } else {
            dados.setGenero(0);
        }

//        request.setAttribute("dados", dados);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
//        dispatcher.forward(request, response);
        HttpSession sessao = request.getSession();
        sessao.setAttribute("dados", dados);
        response.sendRedirect("form-validacao-salvar");
    }

}
