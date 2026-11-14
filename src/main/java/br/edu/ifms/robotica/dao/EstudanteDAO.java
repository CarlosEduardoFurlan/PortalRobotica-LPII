package br.edu.ifms.robotica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifms.robotica.model.Estudante;
import br.edu.ifms.robotica.util.Conexao;

public class EstudanteDAO {
    public void inserir(Estudante e) throws SQLException {
        String sql = "INSERT INTO estudante(nome,minibio,foto,email,senha_hash,ativo) VALUES(?,?,?,?,?,TRUE)";
        try (Connection c=Conexao.conectar(); PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1,e.getNome()); ps.setString(2,e.getMinibio()); ps.setString(3,e.getFoto());
            ps.setString(4,normalizarEmail(e.getEmail())); ps.setString(5,e.getSenhaHash()); ps.executeUpdate();
        }
    }
    public List<Estudante> listar() throws SQLException { return listar(false); }
    public List<Estudante> listarAtivos() throws SQLException { return listar(true); }
    private List<Estudante> listar(boolean ativos) throws SQLException {
        List<Estudante> l=new ArrayList<>();
        String sql="SELECT id,nome,minibio,foto,email,ativo FROM estudante "+(ativos?"WHERE ativo=TRUE ":"")+"ORDER BY nome";
        try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql);ResultSet rs=ps.executeQuery()){while(rs.next())l.add(mapear(rs,false));}
        return l;
    }
    public Estudante buscarPorId(Long id)throws SQLException{return buscar("SELECT id,nome,minibio,foto,email,senha_hash,ativo FROM estudante WHERE id=?",id,null);}
    public Estudante buscarPorEmail(String email)throws SQLException{return buscar("SELECT id,nome,minibio,foto,email,senha_hash,ativo FROM estudante WHERE lower(trim(email))=?",null,normalizarEmail(email));}
    private Estudante buscar(String sql,Long id,String email)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){if(id!=null)ps.setLong(1,id);else ps.setString(1,email);try(ResultSet rs=ps.executeQuery()){return rs.next()?mapear(rs,true):null;}}}
    public void atualizar(Estudante e)throws SQLException{String sql="UPDATE estudante SET nome=?,minibio=?,foto=COALESCE(?,foto),email=? WHERE id=?";try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){ps.setString(1,e.getNome());ps.setString(2,e.getMinibio());ps.setString(3,e.getFoto());ps.setString(4,normalizarEmail(e.getEmail()));ps.setLong(5,e.getId());validar(ps.executeUpdate());}}
    public void alterarAtivo(Long id,boolean ativo)throws SQLException{try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement("UPDATE estudante SET ativo=? WHERE id=?")){ps.setBoolean(1,ativo);ps.setLong(2,id);validar(ps.executeUpdate());}}
    public void atualizarSenha(Long id,String hash)throws SQLException{try(Connection c=Conexao.conectar()){atualizarSenha(c,id,hash);}}
    public void atualizarSenha(Connection c,Long id,String hash)throws SQLException{try(PreparedStatement ps=c.prepareStatement("UPDATE estudante SET senha_hash=? WHERE id=? AND ativo=TRUE")){ps.setString(1,hash);ps.setLong(2,id);validar(ps.executeUpdate());}}
    public boolean emailEmUso(String email,Long ignorar)throws SQLException{String sql="SELECT 1 FROM estudante WHERE lower(trim(email))=? AND (? IS NULL OR id<>?)";try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){ps.setString(1,normalizarEmail(email));if(ignorar==null){ps.setNull(2,java.sql.Types.BIGINT);ps.setNull(3,java.sql.Types.BIGINT);}else{ps.setLong(2,ignorar);ps.setLong(3,ignorar);}try(ResultSet rs=ps.executeQuery()){return rs.next();}}}
    public List<String> listarAtividadesPorEstudante(Long id)throws SQLException{List<String> l=new ArrayList<>();String sql="SELECT a.titulo FROM atividade a JOIN participacao p ON p.atividade_id=a.id WHERE p.estudante_id=? ORDER BY a.titulo";try(Connection c=Conexao.conectar();PreparedStatement ps=c.prepareStatement(sql)){ps.setLong(1,id);try(ResultSet rs=ps.executeQuery()){while(rs.next())l.add(rs.getString(1));}}return l;}
    private Estudante mapear(ResultSet rs,boolean hash)throws SQLException{Estudante e=new Estudante();e.setId(rs.getLong("id"));e.setNome(rs.getString("nome"));e.setMinibio(rs.getString("minibio"));e.setFoto(rs.getString("foto"));e.setEmail(rs.getString("email"));e.setAtivo(rs.getBoolean("ativo"));if(hash)e.setSenhaHash(rs.getString("senha_hash"));return e;}
    private static void validar(int n)throws SQLException{if(n!=1)throw new SQLException("Estudante não encontrado.");}
    public static String normalizarEmail(String email){return email==null?null:email.trim().toLowerCase(Locale.ROOT);}
}
