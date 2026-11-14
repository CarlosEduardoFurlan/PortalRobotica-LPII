package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifms.robotica.model.Coordenador;
import br.edu.ifms.robotica.util.Conexao;

public class CoordenadorDAO {
    public void inserir(Coordenador c)throws SQLException{String sql="INSERT INTO coordenador(nome,minibio,foto,email,senha_hash,ativo) VALUES(?,?,?,?,?,TRUE)";try(Connection cn=Conexao.conectar();PreparedStatement ps=cn.prepareStatement(sql)){ps.setString(1,c.getNome());ps.setString(2,c.getMinibio());ps.setString(3,c.getFoto());ps.setString(4,normalizarEmail(c.getEmail()));ps.setString(5,c.getSenhaHash());ps.executeUpdate();}}
    public List<Coordenador> listar()throws SQLException{return listar(false);} public List<Coordenador> listarAtivos()throws SQLException{return listar(true);}
    private List<Coordenador> listar(boolean ativos)throws SQLException{List<Coordenador> l=new ArrayList<>();String sql="SELECT id,nome,minibio,foto,email,ativo FROM coordenador "+(ativos?"WHERE ativo=TRUE ":"")+"ORDER BY nome";try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql);ResultSet rs=ps.executeQuery()){while(rs.next())l.add(mapear(rs,false));}return l;}
    public Coordenador buscarPorId(Long id)throws SQLException{return buscar("SELECT id,nome,minibio,foto,email,senha_hash,ativo FROM coordenador WHERE id=?",id,null);}
    public Coordenador buscarPorEmail(String email)throws SQLException{return buscar("SELECT id,nome,minibio,foto,email,senha_hash,ativo FROM coordenador WHERE lower(trim(email))=?",null,normalizarEmail(email));}
    private Coordenador buscar(String sql,Long id,String email)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){if(id!=null)ps.setLong(1,id);else ps.setString(1,email);try(ResultSet rs=ps.executeQuery()){return rs.next()?mapear(rs,true):null;}}}
    public void atualizar(Coordenador c)throws SQLException{String sql="UPDATE coordenador SET nome=?,minibio=?,foto=COALESCE(?,foto),email=? WHERE id=?";try(Connection cn=Conexao.conectar();PreparedStatement ps=cn.prepareStatement(sql)){ps.setString(1,c.getNome());ps.setString(2,c.getMinibio());ps.setString(3,c.getFoto());ps.setString(4,normalizarEmail(c.getEmail()));ps.setLong(5,c.getId());validar(ps.executeUpdate());}}
    public void alterarAtivo(Long id,boolean ativo)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement("UPDATE coordenador SET ativo=? WHERE id=?")){ps.setBoolean(1,ativo);ps.setLong(2,id);validar(ps.executeUpdate());}}
    public void atualizarSenha(Long id,String hash)throws SQLException{try(Connection c=Conexao.conectar()){atualizarSenha(c,id,hash);}}
    public void atualizarSenha(Connection c,Long id,String hash)throws SQLException{try(PreparedStatement ps=c.prepareStatement("UPDATE coordenador SET senha_hash=? WHERE id=? AND ativo=TRUE")){ps.setString(1,hash);ps.setLong(2,id);validar(ps.executeUpdate());}}
    public boolean emailEmUso(String email,Long ignorar)throws SQLException{String sql="SELECT 1 FROM coordenador WHERE lower(trim(email))=? AND (? IS NULL OR id<>?)";try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){ps.setString(1,normalizarEmail(email));if(ignorar==null){ps.setNull(2,java.sql.Types.BIGINT);ps.setNull(3,java.sql.Types.BIGINT);}else{ps.setLong(2,ignorar);ps.setLong(3,ignorar);}try(ResultSet rs=ps.executeQuery()){return rs.next();}}}
    public boolean possuiAtividades(Long id)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement("SELECT 1 FROM atividade WHERE coordenador_id=? LIMIT 1")){ps.setLong(1,id);try(ResultSet rs=ps.executeQuery()){return rs.next();}}}
    private Coordenador mapear(ResultSet rs,boolean hash)throws SQLException{Coordenador c=new Coordenador();c.setId(rs.getLong("id"));c.setNome(rs.getString("nome"));c.setMinibio(rs.getString("minibio"));c.setFoto(rs.getString("foto"));c.setEmail(rs.getString("email"));c.setAtivo(rs.getBoolean("ativo"));if(hash)c.setSenhaHash(rs.getString("senha_hash"));return c;}
    private static void validar(int n)throws SQLException{if(n!=1)throw new SQLException("Coordenador não encontrado.");}
    public static String normalizarEmail(String email){return email==null?null:email.trim().toLowerCase(Locale.ROOT);}
}
