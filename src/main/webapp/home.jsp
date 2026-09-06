<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.edu.psicopedagogico.model.Usuario"%>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio - Sistema Psicopedagogico</title>
</head>
<body>
    <h1>Bem-vindo(a), <%= usuarioLogado.getNome() %></h1>
    <p>Perfil: <strong><%= usuarioLogado.getPerfil() %></strong></p>

    <ul>
        <li><a href="<%= request.getContextPath() %>/aluno">Listar Alunos</a></li>
        <li><a href="<%= request.getContextPath() %>/aluno?acao=novo">Cadastrar Aluno</a></li>
        <li><a href="<%= request.getContextPath() %>/logout">Sair</a></li>
    </ul>
</body>
</html>
