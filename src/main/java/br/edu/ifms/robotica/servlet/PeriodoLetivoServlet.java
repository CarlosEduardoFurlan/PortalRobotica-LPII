package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.robotica.dao.PeriodoLetivoDAO;
import br.edu.ifms.robotica.model.PeriodoLetivo;

@WebServlet("/admin/periodos")
public class PeriodoLetivoServlet extends HttpServlet {

	private PeriodoLetivoDAO periodoLetivoDAO;

	@Override
	public void init() {
		periodoLetivoDAO = new PeriodoLetivoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<PeriodoLetivo> periodos = periodoLetivoDAO.listar();

			request.setAttribute("periodos", periodos);

			request.getRequestDispatcher("/WEB-INF/views/admin/periodos.jsp").forward(request, response);

		} catch (SQLException e) {
			throw new ServletException("Erro ao listar períodos letivos.", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String acao = request.getParameter("acao");

		try {

			// EXCLUSÃO
			if ("excluir".equals(acao)) {

				Long id = Long.parseLong(request.getParameter("id"));

				if (periodoLetivoDAO.possuiAtividades(id)) {

					request.setAttribute("erro",
							"erro.periodo.possuiAtividades");

					doGet(request, response);
					return;
				}

				periodoLetivoDAO.excluir(id);

				response.sendRedirect(
					    request.getContextPath()
					    + "/admin/periodos?sucesso=exclusao"
					);

					return;

			} else {

				Integer ano = Integer.parseInt(request.getParameter("ano"));

				Integer semestre = Integer.parseInt(request.getParameter("semestre"));

				// Verifica se o período já existe
				if (periodoLetivoDAO.existe(ano, semestre)) {

					request.setAttribute("erro", "erro.periodo.duplicado");

					doGet(request, response);

					return;
				}

				PeriodoLetivo periodo = new PeriodoLetivo();

				periodo.setAno(ano);
				periodo.setSemestre(semestre);

				periodoLetivoDAO.inserir(periodo);
			}

			response.sendRedirect(
				    request.getContextPath()
				    + "/admin/periodos?sucesso=cadastro"
				);

		} catch (SQLException e) {

			throw new ServletException("Erro ao processar período letivo.", e);
		}
	}
}