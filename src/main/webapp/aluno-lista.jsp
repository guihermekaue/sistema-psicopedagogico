<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.edu.psicopedagogico.model.Usuario"%>
<%@ page import="br.edu.psicopedagogico.model.Aluno"%>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    boolean isAdminPsico = usuarioLogado.isAdminPsico();

    @SuppressWarnings("unchecked")
    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Alunos</title>
</head>
<body>
    <h1>Alunos Cadastrados</h1>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Nome</th>
            <th>Data de Nascimento</th>
            <th>Ciclo</th>
            <th>Status</th>
            <% if (isAdminPsico) { %>
            <th>Anotacoes Clinicas</th>
            <% } %>
        </tr>
        <% for (Aluno aluno : alunos) { %>
        <tr>
            <td><%= aluno.getNome() %></td>
            <td><%= aluno.getDataNascimento() %></td>
            <td><%= aluno.getCiclo() %></td>
            <td><%= aluno.getStatus() %></td>
            <% if (isAdminPsico) { %>
            <td><%= aluno.getAnotacoesClinicas() != null ? aluno.getAnotacoesClinicas() : "" %></td>
            <% } %>
            <%-- Para PROFESSOR e RESPONSAVEL a coluna acima nem existe na tabela. --%>
        </tr>
        <% } %>
    </table>

    <p>
        <a href="<%= request.getContextPath() %>/aluno?acao=novo">Cadastrar Novo Aluno</a> |
        <a href="<%= request.getContextPath() %>/home.jsp">Voltar</a>
    </p>
</body>
</html>
