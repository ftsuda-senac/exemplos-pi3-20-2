<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo POST com form</title>
    </head>
    <body>
        <div>
            <h1>Exemplo POST com form</h1>
            <c:if test="${false}">
                <div style="background-color: aquamarine">
                    Mensagem para usuário
                </div>
            </c:if>
            <form method="post" action="formulario-salvar">
                <div>
                    <label>Nome</label>
                    <input type="text" name="nome">
                    <c:if test="${false}">
                        <span>Mensagem de erro</span>
                    </c:if>
                </div>
                <div>
                    <label>Data de nascimento</label>
                    <input type="date" name="dataNascimento">
                </div>
                <div>
                    <label>Altura</label>
                    <input type="number" step="0.01" name="altura">
                </div>
                <div>
                    <label>Peso</label>
                    <input type="number" step="0.1" name="peso">
                </div>
                <div>
                    <label>E-mail</label>
                    <input type="email" name="email">
                </div>
                <div>
                    <label>Senha</label>
                    <input type="password" name="senha">
                </div>
                <div>
                    <label>Genero</label>
                    <input type="radio" value="0" name="genero" id="generoF"><label for="generoF">Feminino</label>
                    <input type="radio" value="1" name="genero" id="generoM"><label for="generoM">Masculino</label>
                </div>
                <div>
                    <label>Interesses</label>
                    <input type="checkbox" value="Tecnologia" name="interesses" id="interesse1"><label for="interesse1">Tecnologia</label>
                    <input type="checkbox" value="Gastronomia" name="interesses" id="interesse2"><label for="interesse2">Gastronomia</label>
                    <input type="checkbox" value="Viagens" name="interesses" id="interesse3"><label for="interesse3">Viagens</label>
                    <input type="checkbox" value="Esportes" name="interesses" id="interesse4"><label for="interesse4">Esportes</label>
                    <input type="checkbox" value="Investimentos" name="interesses" id="interesse5"><label for="interesse5">Investimentos</label>
                </div>
                <div>
                    <button type="reset">Reiniciar dados</button>
                    <button type="submit">Enviar dados</button>
                </div>
            </form>
        </div>
    </body>
</html>
