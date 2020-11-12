<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Exemplo upload de arquivos</h1>
        <c:if test="${sessionScope.msgSucesso != null}">
            <div class="sucesso">
                <c:out value="${sessionScope.msgSucesso}" />
                <c:remove scope="session" var="msgSucesso" />
            </div>
        </c:if>
        <div>
            <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
                <input type="file" name="arquivo">
                <button type="submit">Enviar</button>
            </form>
        </div>
    </body>
</html>
