<%@page import="java.math.BigDecimal"%>
<%@page import="java.time.LocalDate"%>
<%@page import="br.senac.tads.pi3.exemplosweb.Dados"%>
<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Exemplo JSP</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Exemplo JSP</h1>
        <h2><b>**** NÃO USAR ESTE EXEMPLO!!! ****</b></h2>
        <%
            // SCRIPTLET --> NÃO USAR NO PROJETO
            String ua = request.getHeader("User-agent");
            LocalDateTime dataHora = LocalDateTime.now();
            
            Dados dados = new Dados();
            dados.setId(2);
            dados.setNome("Ciclano de Souza");
            dados.setDataNascimento(LocalDate.of(2000, 05, 20));
            dados.setPeso(new BigDecimal(73.8));
            dados.setAltura(new BigDecimal(1.81));
        %>
        <p>Data e hora: <%= dataHora %></p>
        <p>User agent: <%= ua %></p>
        <hr>
        <h2><%= dados.getNome()%></h2>
        <p>Data de nascimento: <%= dados.getDataNascimento()%></p>
        <p>Idade: <%= dados.getIdade()%></p>
        <p>Altura: <%= dados.getAltura()%></p>
        <p>Peso: <%= dados.getPeso()%></p>
        <p>IMC: <%= dados.getImc()%></p>
    </body>
</html>
