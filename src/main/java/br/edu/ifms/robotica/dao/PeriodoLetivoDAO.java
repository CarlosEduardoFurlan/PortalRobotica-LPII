package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.model.PeriodoLetivo;
import br.edu.ifms.robotica.util.Conexao;

public class PeriodoLetivoDAO {

    public void inserir(PeriodoLetivo periodo) throws SQLException {

        String sql = "INSERT INTO periodo_letivo (ano, semestre) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, periodo.getAno());
            stmt.setInt(2, periodo.getSemestre());

            stmt.executeUpdate();
        }
    }

    public List<PeriodoLetivo> listar() throws SQLException {

        List<PeriodoLetivo> periodos = new ArrayList<>();

        String sql = "SELECT id, ano, semestre " +
                     "FROM periodo_letivo " +
                     "ORDER BY ano DESC, semestre DESC";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                PeriodoLetivo periodo = new PeriodoLetivo();

                periodo.setId(resultado.getLong("id"));
                periodo.setAno(resultado.getInt("ano"));
                periodo.setSemestre(resultado.getInt("semestre"));

                periodos.add(periodo);
            }
        }

        return periodos;
    }

    public void excluir(Long id) throws SQLException {

        String sql = "DELETE FROM periodo_letivo WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
    
    public boolean existe(Integer ano, Integer semestre)
            throws SQLException {

        String sql =
            "SELECT COUNT(*) " +
            "FROM periodo_letivo " +
            "WHERE ano = ? AND semestre = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, ano);
            stmt.setInt(2, semestre);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        }

        return false;
    }
    
    public boolean possuiAtividades(Long periodoId) throws SQLException {

        String sql =
            "SELECT COUNT(*) " +
            "FROM atividade_periodo " +
            "WHERE periodo_id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, periodoId);

            try (ResultSet resultado = comando.executeQuery()) {

                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        }

        return false;
    }
}