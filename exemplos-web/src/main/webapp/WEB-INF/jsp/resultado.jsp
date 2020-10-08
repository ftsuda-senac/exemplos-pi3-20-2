<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultado dados preenchidos</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <h1>Resultado dados preenchidos</h1>
        <h2><c:out value="${dados.nome}" /></h2>
        <p>Data de nascimento: <c:out value="${dados.dataNascimento}" /></p>
        <p>Idade: <c:out value="${dados.idade}" /></p>
        <p>E-mail: <c:out value="${dados.email}" /></p>
        <p>Senha: <c:out value="${dados.senha}" /></p>
        <p>Gênero:
            <c:choose>
                <c:when test="${dados.genero == 1}">Masculino</c:when>
                <c:otherwise>Feminino</c:otherwise>
            </c:choose>
        <p>Altura: <c:out value="${dados.altura}" /></p>
        <p>Peso: <c:out value="${dados.peso}" /></p>
        <p>IMC: <c:out value="${dados.imc}" /></p>
        <p>Condição IMC:
            <c:choose>
                <c:when test="${dados.imcResult == 1}">
                    <span class="magreza">Magreza</span>
                </c:when>
                <c:when test="${dados.imcResult == 2}">
                    <span class="normal">Normal</span>
                </c:when>
                <c:when test="${dados.imcResult == 3}">
                    <span class="sobrepeso">Sobrepeso</span>
                </c:when>
                <c:when test="${dados.imcResult == 4}">
                    <span class="obesidade">Obesidade</span>
                </c:when>
                <c:when test="${dados.imcResult == 5}">
                    <span class="obesidade-grave">Obesidade grave</span>
                </c:when>
                <c:otherwise>
                    <span>Não determinado</span>
                </c:otherwise>
            </c:choose>
        <p>Interesses</p>
        <c:choose>
            <c:when test="${dados.interesses != null || dados.interesses.length < 1}">
                <ul>
                    <c:forEach var="interesse" items="${dados.interesses}">
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
