package br.edu.psicopedagogico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.psicopedagogico.db.ConnectionFactory;
import br.edu.psicopedagogico.model.Usuario;

public class UsuarioDAO {

    /**
     * Busca um usuario por email e senha (texto simples, apenas para fins
     * didaticos - em producao use hash de senha, ex.: BCrypt).
     *
     * @return o Usuario encontrado, ou null se as credenciais forem invalidas.
     */
    public Usuario validarLogin(String email, String senha) {
        String sql = "SELECT id, nome, email, senha, perfil FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setPerfil(rs.getString("perfil"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login.", e);
        }

        return null;
    }
}
