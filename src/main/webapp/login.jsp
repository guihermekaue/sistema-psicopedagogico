<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Sistema Psicopedagogico</title>
</head>
<body>
    <h1>Sistema Psicopedagogico Escolar</h1>
    <h2>Login</h2>

    <% if (request.getAttribute("erro") != null) { %>
        <p style="color:red;"><%= request.getAttribute("erro") %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required="required"><br><br>

        <label>Senha:</label><br>
        <input type="password" name="senha" required="required"><br><br>

        <input type="submit" value="Entrar">
    </form>

    <hr>
    <p><small>
        Usuarios de teste (senha "123456"):<br>
        psicologa@escola.com (ADMIN_PSICO)<br>
        professor@escola.com (PROFESSOR)<br>
        responsavel@escola.com (RESPONSAVEL)
    </small></p>
</body>
</html>
