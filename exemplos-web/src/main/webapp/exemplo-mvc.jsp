<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Exemplo Servlet+JSP (MVC)</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Exemplo Servlet+JSP (MVC)</h1>
        <p>Data e hora: <c:out value="${dataHoraAttr}" /></p>
        <p>User agent: <c:out value="${uaAttr}" /></p>
        <hr>
        <h2><c:out value="${dadosAttr.getNome()}" /></h2>
        <p>Data de nascimento: <c:out value="${dadosAttr.getDataNascimento()}" /></p>
        <p>Idade: <c:out value="${dadosAttr.getIdade()}" /></p>
        <p>Altura: <c:out value="${dadosAttr.getAltura()}" /></p>
        <p>Peso: <c:out value="${dadosAttr.getPeso()}" /></p>
        <p>IMC: <c:out value="${dadosAttr.getImc()}" /></p>
    </body>
</html>
