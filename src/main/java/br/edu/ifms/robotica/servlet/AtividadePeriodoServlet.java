package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.robotica.dao.AtividadePeriodoDAO;
import br.edu.ifms.robotica.dao.AtividadeDAO;
import br.edu.ifms.robotica.dao.PeriodoLetivoDAO;
import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.PeriodoLetivo;
import br.edu.ifms.robotica.model.AtividadePeriodo;

@WebServlet("/admin/atividade-periodo")
public class AtividadePeriodoServlet extends HttpServlet {

    private AtividadePeriodoDAO atividadePeriodoDAO;
    private AtividadeDAO atividadeDAO;
    private PeriodoLetivoDAO periodoLetivoDAO;

    @Override
    public void init() {
        atividadePeriodoDAO = new AtividadePeriodoDAO();
        atividadeDAO = new AtividadeDAO();
        periodoLetivoDAO = new PeriodoLetivoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

        	List<AtividadePeriodo> relacionamentos =
        	        atividadePeriodoDAO.listar();

            List<Atividade> atividades =
                    atividadeDAO.listar();

            List<PeriodoLetivo> periodos =
                    periodoLetivoDAO.listar();

            request.setAttribute(
                    "relacionamentos",
                    relacionamentos
            );

            request.setAttribute(
                    "atividades",
                    atividades
            );

            request.setAttribute(
                    "periodos",
                    periodos
            );

            request.getRequestDispatcher(
            		"/WEB-INF/views/admin/atividade-periodo.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Erro ao listar associações.",
                    e
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

            Long atividadeId = Long.parseLong(
                    request.getParameter("atividadeId")
            );

            Long periodoId = Long.parseLong(
                    request.getParameter("periodoId")
            );

            // EXCLUSÃO
            if ("excluir".equals(acao)) {

                atividadePeriodoDAO.excluir(
                        atividadeId,
                        periodoId
                );
                
                response.sendRedirect(
                	    request.getContextPath()
                	    + "/admin/atividade-periodo?sucesso=exclusao"
                	);

                	return;

            // CADASTRO
            } else {

                // Verifica se a associação já existe
                if (atividadePeriodoDAO.existe(
                        atividadeId,
                        periodoId)) {

                    request.setAttribute(
                        "erro",
                        "erro.atividadePeriodo.duplicada"
                    );

                    doGet(request, response);

                    return;
                }

                AtividadePeriodo relacionamento =
                        new AtividadePeriodo();

                relacionamento.setAtividadeId(
                        atividadeId
                );

                relacionamento.setPeriodoId(
                        periodoId
                );

                atividadePeriodoDAO.inserir(
                        atividadeId,
                        periodoId
                );
            }

            response.sendRedirect(
            	    request.getContextPath()
            	    + "/admin/atividade-periodo?sucesso=cadastro"
            	);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao processar associação entre atividade e período.",
                e
            );
        }
    }
}