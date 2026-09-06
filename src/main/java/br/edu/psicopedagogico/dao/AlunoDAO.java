package br.edu.psicopedagogico.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.psicopedagogico.db.ConnectionFactory;
import br.edu.psicopedagogico.model.Aluno;

public class AlunoDAO {

    /**
     * Cadastra um novo aluno, gerando o id (UUID) automaticamente.
     * Se anotacoesClinicas vier null (usuario sem permissao), grava NULL.
     */
    public void cadastrar(Aluno aluno) {
        String sql = "INSERT INTO aluno (id, nome, data_nascimento, ciclo, status, anotacoes_clinicas) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        aluno.setId(UUID.randomUUID().toString());

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(4, aluno.getCiclo());
            stmt.setString(5, aluno.getStatus());
            stmt.setString(6, aluno.getAnotacoesClinicas());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar aluno.", e);
        }
    }

    /** Lista todos os alunos (todas as colunas, inclusive anotacoes_clinicas). */
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, data_nascimento, ciclo, status, anotacoes_clinicas "
                   + "FROM aluno ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alunos.add(montarAluno(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos.", e);
        }

        return alunos;
    }

    /** Busca um aluno pelo id (UUID). */
    public Aluno buscarPorId(String id) {
        String sql = "SELECT id, nome, data_nascimento, ciclo, status, anotacoes_clinicas "
                   + "FROM aluno WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarAluno(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno.", e);
        }

        return null;
    }

    private Aluno montarAluno(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getString("id"));
        aluno.setNome(rs.getString("nome"));

        Date dataNasc = rs.getDate("data_nascimento");
        if (dataNasc != null) {
            aluno.setDataNascimento(dataNasc.toLocalDate());
        }

        aluno.setCiclo(rs.getString("ciclo"));
        aluno.setStatus(rs.getString("status"));
        aluno.setAnotacoesClinicas(rs.getString("anotacoes_clinicas"));
        return aluno;
    }
}
