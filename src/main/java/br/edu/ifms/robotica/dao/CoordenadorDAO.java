package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.model.Coordenador;
import br.edu.ifms.robotica.util.Conexao;

public class CoordenadorDAO {

    public void inserir(Coordenador coordenador) throws SQLException {

        String sql = "INSERT INTO coordenador (nome) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, coordenador.getNome());

            stmt.executeUpdate();
        }
    }

    public List<Coordenador> listar() throws SQLException {

        List<Coordenador> coordenadores = new ArrayList<>();

        String sql = "SELECT id, nome FROM coordenador ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Coordenador coordenador = new Coordenador();

                coordenador.setId(resultado.getLong("id"));
                coordenador.setNome(resultado.getString("nome"));

                coordenadores.add(coordenador);
            }
        }

        return coordenadores;
    }

    public void excluir(Long id) throws SQLException {

        String sql = "DELETE FROM coordenador WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
    
    public boolean possuiAtividades(Long coordenadorId)
            throws SQLException {

        String sql =
            "SELECT COUNT(*) " +
            "FROM atividade " +
            "WHERE coordenador_id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setLong(1, coordenadorId);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        }

        return false;
    }
}