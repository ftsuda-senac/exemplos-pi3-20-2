<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo POST com form</title>
        <style>
            .erro {
                color: red;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Exemplo POST com form</h1>
            <c:if test="${false}">
                <div style="background-color: aquamarine">
                    Mensagem para usuário
                </div>
            </c:if>
            <form method="post" action="form-validacao-salvar" novalidate>
                <div>
                    <label>Nome</label>
                    <input type="text" name="nome" required value="${nome}">
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>Data de nascimento</label>
                    <input type="date" name="dataNascimento" value="${dataNascimento}">
                    <c:if test="${dataNascimentoErro != null}">
                        <span class="erro"><c:out value="${dataNascimentoErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>Altura</label>
                    <input type="number" step="0.01" name="altura" max="3" value="${altura}">
                    <c:if test="${alturaErro != null}">
                        <span class="erro"><c:out value="${alturaErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>Peso</label>
                    <input type="number" step="0.1" name="peso" max="400" value="${peso}">
                    <c:if test="${pesoErro != null}">
                        <span class="erro"><c:out value="${pesoErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>E-mail</label>
                    <input type="email" name="email" required value="${email}">
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>Senha</label>
                    <input type="password" name="senha">
                </div>
                <div>
                    <label>Genero</label>
                    <input type="radio" value="0" name="genero" id="generoF" ${genero != '1' ? 'checked' : ''}><label for="generoF">Feminino</label>
                    <input type="radio" value="1" name="genero" id="generoM" ${genero == '1' ? 'checked' : ''}><label for="generoM">Masculino</label>
                </div>
                <div>
                    <label>Interesses</label>
                    <input type="checkbox" value="Tecnologia" name="interesses" id="interesse1" ${interesses.contains('Tecnologia') ? 'checked' : ''}><label for="interesse1">Tecnologia</label>
                    <input type="checkbox" value="Gastronomia" name="interesses" id="interesse2"  ${interesses.contains('Gastronomia') ? 'checked' : ''}><label for="interesse2">Gastronomia</label>
                    <input type="checkbox" value="Viagens" name="interesses" id="interesse3" ${interesses.contains('Viagens') ? 'checked' : ''}><label for="interesse3">Viagens</label>
                    <input type="checkbox" value="Esportes" name="interesses" id="interesse4" ${interesses.contains('Esportes') ? 'checked' : ''}><label for="interesse4">Esportes</label>
                    <input type="checkbox" value="Investimentos" name="interesses" id="interesse5"  ${interesses.contains('Investimentos') ? 'checked' : ''}><label for="interesse5">Investimentos</label>
                </div>
                <div>
                    <button type="reset">Reiniciar dados</button>
                    <button type="submit">Enviar dados</button>
                </div>
            </form>
        </div>
    </body>
</html>
