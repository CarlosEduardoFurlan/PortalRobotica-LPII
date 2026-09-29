package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.util.Conexao;
import br.edu.ifms.robotica.model.AtividadePeriodo;

public class AtividadePeriodoDAO {

	public void inserir(Long atividadeId, Long periodoId) throws SQLException {

		String sql = "INSERT INTO atividade_periodo " + "(atividade_id, periodo_id) " + "VALUES (?, ?)";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, atividadeId);
			stmt.setLong(2, periodoId);

			stmt.executeUpdate();
		}
	}

	public List<AtividadePeriodo> listar() throws SQLException {

	    List<AtividadePeriodo> relacionamentos = new ArrayList<>();

	    String sql = """
	        SELECT
	            ap.atividade_id,
	            ap.periodo_id,
	            a.titulo AS titulo_atividade,
	            p.ano,
	            p.semestre
	        FROM atividade_periodo ap
	        INNER JOIN atividade a
	            ON a.id = ap.atividade_id
	        INNER JOIN periodo_letivo p
	            ON p.id = ap.periodo_id
	        ORDER BY p.ano DESC, p.semestre DESC, a.titulo
	        """;

	    try (Connection conexao = Conexao.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql);
	         ResultSet resultado = stmt.executeQuery()) {

	        while (resultado.next()) {

	            AtividadePeriodo relacionamento = new AtividadePeriodo();

	            relacionamento.setAtividadeId(
	                resultado.getLong("atividade_id")
	            );

	            relacionamento.setPeriodoId(
	                resultado.getLong("periodo_id")
	            );

	            relacionamento.setTituloAtividade(
	                resultado.getString("titulo_atividade")
	            );

	            relacionamento.setPeriodoFormatado(
	                resultado.getInt("ano")
	                + "/"
	                + resultado.getInt("semestre")
	            );

	            relacionamentos.add(relacionamento);
	        }
	    }

	    return relacionamentos;
	}

	public void excluir(Long atividadeId, Long periodoId) throws SQLException {

		String sql = "DELETE FROM atividade_periodo " + "WHERE atividade_id = ? AND periodo_id = ?";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, atividadeId);
			stmt.setLong(2, periodoId);

			stmt.executeUpdate();
		}
	}

	public boolean existe(Long atividadeId, Long periodoId) throws SQLException {

		String sql = "SELECT COUNT(*) " + "FROM atividade_periodo " + "WHERE atividade_id = ? " + "AND periodo_id = ?";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, atividadeId);
			stmt.setLong(2, periodoId);

			try (ResultSet resultado = stmt.executeQuery()) {

				if (resultado.next()) {
					return resultado.getInt(1) > 0;
				}
			}
		}

		return false;
	}
}