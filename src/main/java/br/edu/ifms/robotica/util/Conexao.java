package br.edu.ifms.robotica.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexao {

    private static final String JNDI_NAME = "java:comp/env/jdbc/PortalRoboticaDB";

    public static Connection conectar() throws SQLException {

        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup(JNDI_NAME);
            return dataSource.getConnection();
        } catch (NamingException e) {
            throw new SQLException("DataSource JNDI não encontrado: " + JNDI_NAME, e);
        }
    }
}
