package br.edu.ifms.robotica.filter;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.edu.ifms.robotica.dao.CoordenadorDAO;
import br.edu.ifms.robotica.model.Coordenador;

@WebFilter("/admin/*")
public class AutenticacaoFilter implements Filter {
    private final CoordenadorDAO dao=new CoordenadorDAO();
    @Override public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{
        HttpServletRequest r=(HttpServletRequest)req;HttpServletResponse p=(HttpServletResponse)res;
        p.setHeader("Cache-Control","no-store, no-cache, must-revalidate");p.setHeader("Pragma","no-cache");
        HttpSession s=r.getSession(false);Long id=s==null?null:(Long)s.getAttribute("coordenadorId");
        try{Coordenador c=id==null?null:dao.buscarPorId(id);if(c==null||!c.isAtivo()){if(s!=null)s.invalidate();p.sendRedirect(r.getContextPath()+"/login?erro=autenticacao");return;}chain.doFilter(req,res);}catch(SQLException e){throw new ServletException("Erro ao validar a sessão administrativa.",e);}
    }
}
