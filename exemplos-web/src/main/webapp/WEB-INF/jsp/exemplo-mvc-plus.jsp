<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Exemplo Servlet+JSP Plus (MVC)</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <h1>Exemplo Servlet+JSP Plus (MVC)</h1>
        <p>Data e hora: <c:out value="${dataHoraAttr}" /></p>
        <p>User agent: <c:out value="${uaAttr}" /></p>
        <hr>
        <h2><c:out value="${dadosAttr.getNome()}" /></h2>
        <p>Data de nascimento: <c:out value="${dadosAttr.getDataNascimento()}" /></p>
        <p>Idade: <c:out value="${dadosAttr.getIdade()}" /></p>
        <p>Altura: <c:out value="${dadosAttr.getAltura()}" /></p>
        <p>Peso: <c:out value="${dadosAttr.getPeso()}" /></p>
        <p>IMC: <c:out value="${dadosAttr.getImc()}" /></p>
        <p>Condição IMC:
            <c:choose>
                <c:when test="${dadosAttr.getImcResult() == 1}">
                    <span class="magreza">Magreza</span>
                </c:when>
                <c:when test="${dadosAttr.getImcResult() == 2}">
                    <span class="normal">Normal</span>
                </c:when>
                <c:when test="${dadosAttr.getImcResult() == 3}">
                    <span class="sobrepeso">Sobrepeso</span>
                </c:when>
                <c:when test="${dadosAttr.getImcResult() == 4}">
                    <span class="obesidade">Obesidade</span>
                </c:when>
                <c:when test="${dadosAttr.getImcResult() == 5}">
                    <span class="obesidade-grave">Obesidade grave</span>
                </c:when>
                <c:otherwise>
                    <span>Não determinado</span>
                </c:otherwise>
            </c:choose>
        
        
        <p>Interesses</p>
        <c:choose>
            <c:when test="${dadosAttr.getInteresses() != null}">
                <ul>
                    <c:forEach var="interesse" items="${dadosAttr.getInteresses()}">
                        <li><c:out value="${interesse}" /></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Não tem nenhum interesse informado</p>
            </c:otherwise>
        </c:choose>

        
    </body>
</html>
