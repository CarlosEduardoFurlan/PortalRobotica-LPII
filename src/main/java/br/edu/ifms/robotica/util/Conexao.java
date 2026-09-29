package br.edu.ifms.robotica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/portal_robotica";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection conectar() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException e) {

            throw new SQLException("Driver PostgreSQL não encontrado.", e);
        }
    }
}