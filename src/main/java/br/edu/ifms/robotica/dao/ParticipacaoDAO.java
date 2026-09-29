package br.edu.ifms.robotica.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.robotica.model.Participacao;
import br.edu.ifms.robotica.util.Conexao;

public class ParticipacaoDAO {

	public void inserir(Participacao participacao) throws SQLException {

		String sql = "INSERT INTO participacao " + "(estudante_id, atividade_id, funcao, descricao_contribuicao) "
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, participacao.getEstudanteId());
			stmt.setLong(2, participacao.getAtividadeId());
			stmt.setString(3, participacao.getFuncao());
			stmt.setString(4, participacao.getDescricaoContribuicao());

			stmt.executeUpdate();
		}
	}

	public List<Participacao> listar() throws SQLException {

		List<Participacao> participacoes = new ArrayList<>();

		String sql = "SELECT p.estudante_id, p.atividade_id, " + "p.funcao, p.descricao_contribuicao, "
				+ "e.nome AS nome_estudante, " + "a.titulo AS titulo_atividade " + "FROM participacao p "
				+ "INNER JOIN estudante e ON p.estudante_id = e.id "
				+ "INNER JOIN atividade a ON p.atividade_id = a.id " + "ORDER BY e.nome";

		try (Connection conexao = Conexao.conectar();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery()) {

			while (resultado.next()) {

				Participacao participacao = new Participacao();

				participacao.setEstudanteId(resultado.getLong("estudante_id"));

				participacao.setAtividadeId(resultado.getLong("atividade_id"));

				participacao.setFuncao(resultado.getString("funcao"));

				participacao.setDescricaoContribuicao(resultado.getString("descricao_contribuicao"));

				participacao.setNomeEstudante(resultado.getString("nome_estudante"));

				participacao.setTituloAtividade(resultado.getString("titulo_atividade"));

				participacoes.add(participacao);
			}
		}

		return participacoes;
	}

	public void excluir(Long estudanteId, Long atividadeId) throws SQLException {

		String sql = "DELETE FROM participacao " + "WHERE estudante_id = ? AND atividade_id = ?";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, estudanteId);
			stmt.setLong(2, atividadeId);

			stmt.executeUpdate();
		}
	}

	public List<Participacao> listarPorAtividade(Long atividadeId) throws SQLException {

		List<Participacao> participacoes = new ArrayList<>();

		String sql = "SELECT p.estudante_id, p.atividade_id, " + "p.funcao, p.descricao_contribuicao, "
				+ "e.nome AS nome_estudante, " + "a.titulo AS titulo_atividade " + "FROM participacao p "
				+ "INNER JOIN estudante e ON p.estudante_id = e.id "
				+ "INNER JOIN atividade a ON p.atividade_id = a.id " + "WHERE p.atividade_id = ? " + "ORDER BY e.nome";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, atividadeId);

			try (ResultSet resultado = stmt.executeQuery()) {

				while (resultado.next()) {

					Participacao participacao = new Participacao();

					participacao.setEstudanteId(resultado.getLong("estudante_id"));

					participacao.setAtividadeId(resultado.getLong("atividade_id"));

					participacao.setFuncao(resultado.getString("funcao"));

					participacao.setDescricaoContribuicao(resultado.getString("descricao_contribuicao"));

					participacao.setNomeEstudante(resultado.getString("nome_estudante"));

					participacao.setTituloAtividade(resultado.getString("titulo_atividade"));

					participacoes.add(participacao);
				}
			}
		}

		return participacoes;
	}

	public boolean existe(Long estudanteId, Long atividadeId) throws SQLException {

		String sql = "SELECT COUNT(*) " + "FROM participacao " + "WHERE estudante_id = ? " + "AND atividade_id = ?";

		try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setLong(1, estudanteId);
			stmt.setLong(2, atividadeId);

			try (ResultSet resultado = stmt.executeQuery()) {

				if (resultado.next()) {
					return resultado.getInt(1) > 0;
				}
			}
		}

		return false;
	}
}