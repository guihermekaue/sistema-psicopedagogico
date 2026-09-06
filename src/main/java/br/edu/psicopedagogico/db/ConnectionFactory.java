package br.edu.psicopedagogico.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fabrica simples de conexoes JDBC nativas (sem pool, sem framework).
 * Ajuste URL, USER e PASSWORD conforme seu ambiente local.
 *
 * Requer o driver do MySQL (mysql-connector-j-x.x.x.jar) em
 * WebContent/WEB-INF/lib.
 */
public class ConnectionFactory {

    private static final String URL =
            "jdbc:mysql://localhost:3306/psicopedagogico?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                "Driver JDBC do MySQL nao encontrado. "
                + "Adicione o mysql-connector-j.jar em WebContent/WEB-INF/lib.", e);
        }
    }

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
