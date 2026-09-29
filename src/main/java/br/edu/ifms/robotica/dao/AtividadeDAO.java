package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.PeriodoLetivo;
import br.edu.ifms.robotica.util.Conexao;

public class AtividadeDAO {

    public void inserir(Atividade atividade) throws SQLException {

        String sql = "INSERT INTO atividade " +
                     "(titulo, tipo, descricao, data_inicio, data_fim, situacao, coordenador_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, atividade.getTitulo());
            stmt.setString(2, atividade.getTipo());
            stmt.setString(3, atividade.getDescricao());
            stmt.setDate(4, atividade.getDataInicio());
            stmt.setDate(5, atividade.getDataFim());
            stmt.setString(6, atividade.getStatus());
            stmt.setLong(7, atividade.getCoordenadorId());

            stmt.executeUpdate();
        }
    }

    public List<Atividade> listar() throws SQLException {

        List<Atividade> atividades = new ArrayList<>();

        String sql = "SELECT id, titulo, tipo, descricao, data_inicio, " +
                     "data_fim, situacao, coordenador_id " +
                     "FROM atividade " +
                     "ORDER BY data_inicio DESC";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Atividade atividade = new Atividade();

                atividade.setId(resultado.getLong("id"));
                atividade.setTitulo(resultado.getString("titulo"));
                atividade.setTipo(resultado.getString("tipo"));
                atividade.setDescricao(resultado.getString("descricao"));
                atividade.setDataInicio(resultado.getDate("data_inicio"));
                atividade.setDataFim(resultado.getDate("data_fim"));
                atividade.setStatus(resultado.getString("situacao"));
                atividade.setCoordenadorId(
                    resultado.getLong("coordenador_id")
                );

                atividades.add(atividade);
            }
        }

        return atividades;
    }

    public void excluir(Long id) throws SQLException {

        String sql = "DELETE FROM atividade WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
    
    public Atividade buscarPorId(Long id) throws SQLException {

        String sql =
            "SELECT id, titulo, tipo, descricao, " +
            "data_inicio, data_fim, situacao, coordenador_id " +
            "FROM atividade " +
            "WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {

                    Atividade atividade = new Atividade();

                    atividade.setId(
                        resultado.getLong("id")
                    );

                    atividade.setTitulo(
                        resultado.getString("titulo")
                    );

                    atividade.setTipo(
                        resultado.getString("tipo")
                    );

                    atividade.setDescricao(
                        resultado.getString("descricao")
                    );

                    atividade.setDataInicio(
                        resultado.getDate("data_inicio")
                    );

                    atividade.setDataFim(
                        resultado.getDate("data_fim")
                    );

                    atividade.setStatus(
                        resultado.getString("situacao")
                    );

                    atividade.setCoordenadorId(
                        resultado.getLong("coordenador_id")
                    );

                    return atividade;
                }
            }
        }

        return null;
    }
    
    public List<PeriodoLetivo> listarPeriodosPorAtividade(Long atividadeId)
            throws SQLException {

        List<PeriodoLetivo> periodos = new ArrayList<>();

        String sql = """
            SELECT p.id, p.ano, p.semestre
            FROM periodo_letivo p
            INNER JOIN atividade_periodo ap
                ON ap.periodo_id = p.id
            WHERE ap.atividade_id = ?
            ORDER BY p.ano, p.semestre
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, atividadeId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    PeriodoLetivo periodo = new PeriodoLetivo();

                    periodo.setId(rs.getLong("id"));
                    periodo.setAno(rs.getInt("ano"));
                    periodo.setSemestre(rs.getInt("semestre"));

                    periodos.add(periodo);
                }
            }
        }

        return periodos;
    }
}