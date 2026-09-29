package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.robotica.dao.CoordenadorDAO;
import br.edu.ifms.robotica.model.Coordenador;

@WebServlet("/admin/coordenadores")
public class CoordenadorServlet extends HttpServlet {

    private CoordenadorDAO coordenadorDAO;

    @Override
    public void init() {
        coordenadorDAO = new CoordenadorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Coordenador> coordenadores = coordenadorDAO.listar();

            request.setAttribute("coordenadores", coordenadores);

            request.getRequestDispatcher("/WEB-INF/views/admin/coordenadores.jsp")
                   .forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao listar coordenadores.", e
            );
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");

        try {

            // EXCLUSÃO
            if ("excluir".equals(acao)) {

                Long id = Long.parseLong(
                        request.getParameter("id")
                );

                // Verifica se o coordenador possui atividades
                if (coordenadorDAO.possuiAtividades(id)) {

                    request.setAttribute(
                        "erro",
                        "erro.coordenador.possuiAtividades"
                    );

                    doGet(request, response);

                    return;
                }

                coordenadorDAO.excluir(id);
                
                response.sendRedirect(
                	    request.getContextPath()
                	    + "/admin/coordenadores?sucesso=exclusao"
                	);

                	return;

            // CADASTRO
            } else {

                String nome =
                        request.getParameter("nome");

                Coordenador coordenador =
                        new Coordenador();

                coordenador.setNome(nome);

                coordenadorDAO.inserir(
                        coordenador
                );
            }

            response.sendRedirect(
            	    request.getContextPath()
            	    + "/admin/coordenadores?sucesso=cadastro"
            	);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao processar coordenador.",
                e
            );
        }
    }
}