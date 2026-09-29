package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.robotica.dao.AtividadeDAO;
import br.edu.ifms.robotica.dao.ParticipacaoDAO;
import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.Participacao;
import br.edu.ifms.robotica.model.PeriodoLetivo;

@WebServlet("/atividade")
public class AtividadeDetalheServlet extends HttpServlet {

    private AtividadeDAO atividadeDAO;
    private ParticipacaoDAO participacaoDAO;

    @Override
    public void init() {
        atividadeDAO = new AtividadeDAO();
        participacaoDAO = new ParticipacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Long id = Long.parseLong(
                request.getParameter("id")
            );

            Atividade atividade =
                atividadeDAO.buscarPorId(id);
            
            List<PeriodoLetivo> periodos =
                    atividadeDAO.listarPeriodosPorAtividade(
                            atividade.getId()
                    );

            atividade.setPeriodos(periodos);
            
            List<Participacao> participacoes =
                    participacaoDAO.listarPorAtividade(id);

            request.setAttribute(
                "atividade",
                atividade
            );
            
            request.setAttribute(
                    "participacoes",
                    participacoes
            );

            request.getRequestDispatcher(
            		"/WEB-INF/views/public/atividade-detalhe.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao buscar atividade.",
                e
            );
        }
    }
}