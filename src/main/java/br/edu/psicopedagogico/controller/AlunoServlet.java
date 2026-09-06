package br.edu.psicopedagogico.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.psicopedagogico.dao.AlunoDAO;
import br.edu.psicopedagogico.model.Aluno;
import br.edu.psicopedagogico.model.Usuario;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = getUsuarioLogado(request);
        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String acao = request.getParameter("acao");

        if ("novo".equals(acao)) {
            // Apenas exibe o formulario de cadastro
            request.getRequestDispatcher("aluno-form.jsp").forward(request, response);
            return;
        }

        // Listagem padrao
        request.setAttribute("alunos", alunoDAO.listarTodos());
        request.getRequestDispatcher("aluno-lista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = getUsuarioLogado(request);
        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(request.getParameter("nome"));
        aluno.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento")));
        aluno.setCiclo(request.getParameter("ciclo"));
        aluno.setStatus(request.getParameter("status"));

        // Regra de negocio: somente ADMIN_PSICO pode gravar anotacoes clinicas.
        // Mesmo que o campo venha manipulado no POST (ex.: via ferramenta externa),
        // o valor e ignorado se o perfil da sessao nao for ADMIN_PSICO.
        if (usuarioLogado.isAdminPsico()) {
            aluno.setAnotacoesClinicas(request.getParameter("anotacoesClinicas"));
        } else {
            aluno.setAnotacoesClinicas(null);
        }

        alunoDAO.cadastrar(aluno);

        response.sendRedirect(request.getContextPath() + "/aluno");
    }

    private Usuario getUsuarioLogado(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (Usuario) session.getAttribute("usuarioLogado");
    }
}
