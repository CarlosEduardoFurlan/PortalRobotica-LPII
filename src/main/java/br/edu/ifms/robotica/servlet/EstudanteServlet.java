package br.edu.ifms.robotica.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.edu.ifms.robotica.dao.EstudanteDAO;
import br.edu.ifms.robotica.model.Estudante;

@WebServlet("/admin/estudantes")
@MultipartConfig
public class EstudanteServlet extends HttpServlet {

    private EstudanteDAO estudanteDAO;

    @Override
    public void init() {
        estudanteDAO = new EstudanteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Estudante> estudantes = estudanteDAO.listar();

            request.setAttribute("estudantes", estudantes);

            request.getRequestDispatcher( "/WEB-INF/views/admin/estudantes.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar estudantes.", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");

        try {

            if ("excluir".equals(acao)) {

                Long id = Long.parseLong(request.getParameter("id"));

                estudanteDAO.excluir(id);
                
                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/estudantes?sucesso=exclusao"
                );
                
                return;

            } else {

            	String nome = request.getParameter("nome");
            	String minibio = request.getParameter("minibio");

            	Part fotoPart = request.getPart("foto");

            	String nomeFoto = null;

            	if (fotoPart != null && fotoPart.getSize() > 0) {

            	    String nomeOriginal =
            	            fotoPart.getSubmittedFileName();

            	    String extensao = "";

            	    int ponto = nomeOriginal.lastIndexOf('.');

            	    if (ponto >= 0) {
            	        extensao = nomeOriginal.substring(ponto);
            	    }

            	    nomeFoto =
            	            System.currentTimeMillis()
            	            + "-"
            	            + nome.replaceAll("[^a-zA-Z0-9]", "_")
            	            + extensao;


            	    String pastaUploads =
            	            System.getProperty("user.home")
            	            + File.separator
            	            + "portal-robotica"
            	            + File.separator
            	            + "uploads"
            	            + File.separator
            	            + "estudantes";

            	    File pasta = new File(pastaUploads);

            	    if (!pasta.exists()) {
            	        pasta.mkdirs();
            	    }

            	    fotoPart.write(
            	            pastaUploads
            	            + File.separator
            	            + nomeFoto
            	    );
            	}

            	Estudante estudante = new Estudante();

            	estudante.setNome(nome);
            	estudante.setMinibio(minibio);
            	estudante.setFoto(nomeFoto);

            	estudanteDAO.inserir(estudante);
            }

            response.sendRedirect(
                request.getContextPath() + "/admin/estudantes?sucesso=cadastro"
            );

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao processar estudante.",
                e
            );
        }
    }
}