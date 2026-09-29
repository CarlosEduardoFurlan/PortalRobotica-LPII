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
import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.PeriodoLetivo;

@WebServlet("/atividades")
public class AtividadesPublicoServlet extends HttpServlet {

	private AtividadeDAO atividadeDAO;

	@Override
	public void init() {
		atividadeDAO = new AtividadeDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Atividade> atividades = atividadeDAO.listar();

			for (Atividade atividade : atividades) {

				List<PeriodoLetivo> periodos = atividadeDAO.listarPeriodosPorAtividade(atividade.getId());

				atividade.setPeriodos(periodos);
			}

			request.setAttribute("atividades", atividades);

			request.getRequestDispatcher("/WEB-INF/views/public/atividades.jsp").forward(request, response);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}