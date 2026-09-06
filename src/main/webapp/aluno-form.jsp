<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.edu.psicopedagogico.model.Usuario"%>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    boolean isAdminPsico = usuarioLogado.isAdminPsico();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Aluno</title>
</head>
<body>
    <h1>Cadastrar Aluno</h1>

    <form action="<%= request.getContextPath() %>/aluno" method="post">
        <label>Nome:</label><br>
        <input type="text" name="nome" required="required"><br><br>

        <label>Data de Nascimento:</label><br>
        <input type="date" name="dataNascimento" required="required"><br><br>

        <label>Ciclo:</label><br>
        <select name="ciclo">
            <option value="INFANTIL">Infantil</option>
            <option value="FUNDAMENTAL_1">Fundamental 1</option>
            <option value="FUNDAMENTAL_2">Fundamental 2</option>
        </select><br><br>

        <label>Status:</label><br>
        <select name="status">
            <option value="ATIVO">Ativo</option>
            <option value="INATIVO">Inativo</option>
        </select><br><br>

        <% if (isAdminPsico) { %>
        <label>Anotacoes Clinicas:</label><br>
        <textarea name="anotacoesClinicas" rows="5" cols="40"></textarea><br><br>
        <% } %>
        <%-- Se o usuario nao for ADMIN_PSICO, o campo acima nem chega a ser
             renderizado no HTML - ele nao existe na tela nem no request. --%>

        <input type="submit" value="Salvar">
    </form>

    <p><a href="<%= request.getContextPath() %>/home.jsp">Voltar</a></p>
</body>
</html>
