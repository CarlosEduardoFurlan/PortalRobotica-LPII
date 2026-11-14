package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.edu.ifms.robotica.dao.CoordenadorDAO;
import br.edu.ifms.robotica.model.Coordenador;
import br.edu.ifms.robotica.seguranca.SenhaUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private final CoordenadorDAO dao=new CoordenadorDAO();
    @Override protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{p.setHeader("Cache-Control","no-store");r.getRequestDispatcher("/WEB-INF/views/public/login.jsp").forward(r,p);}
    @Override protected void doPost(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{r.setCharacterEncoding("UTF-8");try{Coordenador c=dao.buscarPorEmail(r.getParameter("email"));if(c==null||!c.isAtivo()||!SenhaUtil.verificar(r.getParameter("senha"),c.getSenhaHash())){r.setAttribute("erro","erro.login.invalido");doGet(r,p);return;}HttpSession antiga=r.getSession(false);if(antiga!=null)antiga.invalidate();HttpSession s=r.getSession(true);r.changeSessionId();s.setAttribute("coordenadorId",c.getId());s.setAttribute("coordenadorNome",c.getNome());p.sendRedirect(r.getContextPath()+"/admin/atividades");}catch(SQLException e){throw new ServletException("Erro ao autenticar.",e);}}
}
