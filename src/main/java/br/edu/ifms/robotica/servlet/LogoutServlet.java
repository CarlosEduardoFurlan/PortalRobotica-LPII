package br.edu.ifms.robotica.servlet;
import java.io.IOException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.*;
@WebServlet("/logout") public class LogoutServlet extends HttpServlet{private static final long serialVersionUID=1L;@Override protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{HttpSession s=r.getSession(false);if(s!=null)s.invalidate();p.sendRedirect(r.getContextPath()+"/login?sucesso=logout");}}
