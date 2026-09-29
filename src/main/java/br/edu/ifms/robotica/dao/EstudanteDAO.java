package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.model.Estudante;
import br.edu.ifms.robotica.util.Conexao;

public class EstudanteDAO {

    public void inserir(Estudante estudante) throws SQLException {

        String sql = "INSERT INTO estudante (nome, minibio, foto) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, estudante.getNome());
            stmt.setString(2, estudante.getMinibio());
            stmt.setString(3, estudante.getFoto());

            stmt.executeUpdate();
        }
    }
    
    public List<Estudante> listar() throws SQLException {

        List<Estudante> estudantes = new ArrayList<>();

        String sql = "SELECT id, nome, minibio, foto FROM estudante ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Estudante estudante = new Estudante();

                estudante.setId(resultado.getLong("id"));
                estudante.setNome(resultado.getString("nome"));
                estudante.setMinibio(resultado.getString("minibio"));
                estudante.setFoto(resultado.getString("foto"));

                estudantes.add(estudante);
            }
        }

        return estudantes;
    }
    
    public void excluir(Long id) throws SQLException {

        String sql = "DELETE FROM estudante WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
    
    public List<String> listarAtividadesPorEstudante(Long estudanteId)
            throws SQLException {

        List<String> atividades = new ArrayList<>();

        String sql = """
            SELECT a.titulo
            FROM atividade a
            INNER JOIN participacao p
                ON p.atividade_id = a.id
            WHERE p.estudante_id = ?
            ORDER BY a.titulo
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, estudanteId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    atividades.add(rs.getString("titulo"));
                }
            }
        }

        return atividades;
    }
}