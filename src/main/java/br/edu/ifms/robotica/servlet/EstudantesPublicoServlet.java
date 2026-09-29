package br.edu.ifms.robotica.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.robotica.dao.EstudanteDAO;
import br.edu.ifms.robotica.model.Estudante;

@WebServlet("/estudantes")
public class EstudantesPublicoServlet extends HttpServlet {

	private EstudanteDAO estudanteDAO;

	@Override
	public void init() {
		estudanteDAO = new EstudanteDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Estudante> estudantes = estudanteDAO.listar();

			for (Estudante estudante : estudantes) {

				List<String> atividades = estudanteDAO.listarAtividadesPorEstudante(estudante.getId());

				estudante.setAtividades(atividades);
			}

			request.setAttribute("estudantes", estudantes);

			request.getRequestDispatcher("/WEB-INF/views/public/estudantes.jsp").forward(request, response);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}