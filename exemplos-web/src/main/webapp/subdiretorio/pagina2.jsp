<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    </head>
    <body class="exemplo-link">
        <c:import url="../cabecalho.jsp" />
        <h1>Pagina 2</h1>
        <a href="${pageContext.request.contextPath}/pagina1.jsp">Acessar pagina 1</a>
    </body>
</html>
