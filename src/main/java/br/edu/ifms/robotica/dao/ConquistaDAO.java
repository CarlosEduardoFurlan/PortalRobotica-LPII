package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifms.robotica.model.Conquista;
import br.edu.ifms.robotica.util.Conexao;

public class ConquistaDAO {
    public void inserir(Conquista c)throws SQLException{executar("INSERT INTO conquista(atividade_id,titulo,data,descricao) VALUES(?,?,?,?)",c,false);}
    public void atualizar(Conquista c)throws SQLException{executar("UPDATE conquista SET atividade_id=?,titulo=?,data=?,descricao=? WHERE id=?",c,true);}
    private void executar(String sql,Conquista c,boolean id)throws SQLException{try(Connection cn=Conexao.conectar();PreparedStatement ps=cn.prepareStatement(sql)){ps.setLong(1,c.getAtividadeId());ps.setString(2,c.getTitulo());ps.setDate(3,c.getData());ps.setString(4,c.getDescricao());if(id)ps.setLong(5,c.getId());int n=ps.executeUpdate();if(id&&n!=1)throw new SQLException("Conquista não encontrada.");}}
    public void excluir(Long id)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement("DELETE FROM conquista WHERE id=?")){ps.setLong(1,id);ps.executeUpdate();}}
    public List<Conquista> listar()throws SQLException{return consultar("SELECT id,atividade_id,titulo,data,descricao FROM conquista ORDER BY data DESC NULLS LAST,titulo",null);}
    public List<Conquista> listarPorAtividade(Long id)throws SQLException{return consultar("SELECT id,atividade_id,titulo,data,descricao FROM conquista WHERE atividade_id=? ORDER BY data DESC NULLS LAST,titulo",id);}
    private List<Conquista> consultar(String sql,Long id)throws SQLException{List<Conquista> l=new ArrayList<>();try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){if(id!=null)ps.setLong(1,id);try(ResultSet rs=ps.executeQuery()){while(rs.next()){Conquista q=new Conquista();q.setId(rs.getLong("id"));q.setAtividadeId(rs.getLong("atividade_id"));q.setTitulo(rs.getString("titulo"));q.setData(rs.getDate("data"));q.setDescricao(rs.getString("descricao"));l.add(q);}}}return l;}
}
