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

	public Long inserir(Atividade atividade) throws SQLException {

	    String sql = "INSERT INTO atividade "
	            + "(titulo, tipo, descricao, data_inicio, data_fim, situacao, coordenador_id) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?) "
	            + "RETURNING id";

	    try (Connection conexao = Conexao.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql)) {

	        stmt.setString(1, atividade.getTitulo());
	        stmt.setString(2, atividade.getTipo());
	        stmt.setString(3, atividade.getDescricao());
	        stmt.setDate(4, atividade.getDataInicio());
	        stmt.setDate(5, atividade.getDataFim());
	        stmt.setString(6, atividade.getStatus());
	        stmt.setLong(7, atividade.getCoordenadorId());

	        try (ResultSet resultado = stmt.executeQuery()) {

	            if (resultado.next()) {
	                return resultado.getLong("id");
	            }
	        }
	    }

	    throw new SQLException(
	        "Não foi possível obter o ID da atividade cadastrada."
	    );
	}

    public Long inserirComPeriodos(Atividade atividade, List<Long> periodoIds) throws SQLException {
        try (Connection conexao = Conexao.conectar()) {
            conexao.setAutoCommit(false);
            try {
                Long id = inserir(conexao, atividade);
                substituirPeriodos(conexao, id, periodoIds);
                conexao.commit();
                return id;
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        }
    }

    public void atualizarComPeriodos(Atividade atividade, List<Long> periodoIds) throws SQLException {
        String sql = "UPDATE atividade SET titulo=?,tipo=?,descricao=?,data_inicio=?,data_fim=?,situacao=?,coordenador_id=? WHERE id=?";
        try (Connection conexao = Conexao.conectar()) {
            conexao.setAutoCommit(false);
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                preencher(ps, atividade); ps.setLong(8, atividade.getId());
                if (ps.executeUpdate() != 1) throw new SQLException("Atividade não encontrada.");
                substituirPeriodos(conexao, atividade.getId(), periodoIds);
                conexao.commit();
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally { conexao.setAutoCommit(true); }
        }
    }

    private Long inserir(Connection c, Atividade a) throws SQLException {
        String sql="INSERT INTO atividade(titulo,tipo,descricao,data_inicio,data_fim,situacao,coordenador_id) VALUES(?,?,?,?,?,?,?) RETURNING id";
        try(PreparedStatement ps=c.prepareStatement(sql)){preencher(ps,a);try(ResultSet rs=ps.executeQuery()){if(rs.next())return rs.getLong(1);}}
        throw new SQLException("Não foi possível obter o ID da atividade.");
    }

    private void preencher(PreparedStatement ps, Atividade a)throws SQLException{ps.setString(1,a.getTitulo());ps.setString(2,a.getTipo());ps.setString(3,a.getDescricao());ps.setDate(4,a.getDataInicio());ps.setDate(5,a.getDataFim());ps.setString(6,a.getStatus());ps.setLong(7,a.getCoordenadorId());}

    private void substituirPeriodos(Connection c,Long atividadeId,List<Long> ids)throws SQLException{
        try(PreparedStatement ps=c.prepareStatement("DELETE FROM atividade_periodo WHERE atividade_id=?")){ps.setLong(1,atividadeId);ps.executeUpdate();}
        try(PreparedStatement ps=c.prepareStatement("INSERT INTO atividade_periodo(atividade_id,periodo_id) VALUES(?,?)")){
            for(Long id:ids){ps.setLong(1,atividadeId);ps.setLong(2,id);ps.addBatch();} ps.executeBatch();
        }
    }

	public List<Atividade> listar() throws SQLException {

	    List<Atividade> atividades = new ArrayList<>();

	    String sql = "SELECT a.id, a.titulo, a.tipo, a.descricao, a.data_inicio, "
	               + "a.data_fim, a.situacao, a.coordenador_id, c.nome AS nome_coordenador "
	               + "FROM atividade a JOIN coordenador c ON c.id=a.coordenador_id "
	               + "ORDER BY data_inicio DESC";

	    try (Connection conexao = Conexao.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql);
	         ResultSet resultado = stmt.executeQuery()) {

	        while (resultado.next()) {

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
	            atividade.setNomeCoordenador(resultado.getString("nome_coordenador"));

	            atividades.add(atividade);
	        }
	    }

	    
	    for (Atividade atividade : atividades) {

	        atividade.setPeriodos(
	            listarPeriodosPorAtividade(
	                atividade.getId()
	            )
	        );
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
            "SELECT a.id, a.titulo, a.tipo, a.descricao, " +
            "a.data_inicio, a.data_fim, a.situacao, a.coordenador_id, c.nome AS nome_coordenador " +
            "FROM atividade a JOIN coordenador c ON c.id=a.coordenador_id " +
            "WHERE a.id = ?";

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
                    atividade.setNomeCoordenador(resultado.getString("nome_coordenador"));

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

    public List<Atividade> listarPorPeriodo(int ano, int semestre) throws SQLException {
        List<Atividade> lista=new ArrayList<>();
        String sql="SELECT a.id,a.titulo,a.tipo,a.descricao,a.data_inicio,a.data_fim,a.situacao,a.coordenador_id,c.nome AS nome_coordenador FROM atividade a JOIN coordenador c ON c.id=a.coordenador_id JOIN atividade_periodo ap ON ap.atividade_id=a.id JOIN periodo_letivo p ON p.id=ap.periodo_id WHERE p.ano=? AND p.semestre=? ORDER BY a.titulo";
        try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){ps.setInt(1,ano);ps.setInt(2,semestre);try(ResultSet rs=ps.executeQuery()){while(rs.next()){Atividade a=new Atividade();a.setId(rs.getLong("id"));a.setTitulo(rs.getString("titulo"));a.setTipo(rs.getString("tipo"));a.setDescricao(rs.getString("descricao"));a.setDataInicio(rs.getDate("data_inicio"));a.setDataFim(rs.getDate("data_fim"));a.setStatus(rs.getString("situacao"));a.setCoordenadorId(rs.getLong("coordenador_id"));a.setNomeCoordenador(rs.getString("nome_coordenador"));lista.add(a);}}}
        return lista;
    }
}
