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
import br.edu.ifms.robotica.dao.EstudanteDAO;
import br.edu.ifms.robotica.dao.ParticipacaoDAO;

import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.Estudante;
import br.edu.ifms.robotica.model.Participacao;

@WebServlet("/admin/participacoes")
public class ParticipacaoServlet extends HttpServlet {

    private ParticipacaoDAO participacaoDAO;
    private EstudanteDAO estudanteDAO;
    private AtividadeDAO atividadeDAO;

    @Override
    public void init() {

        participacaoDAO = new ParticipacaoDAO();
        estudanteDAO = new EstudanteDAO();
        atividadeDAO = new AtividadeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Participacao> participacoes =
                    participacaoDAO.listar();

            List<Estudante> estudantes =
                    estudanteDAO.listar();

            List<Atividade> atividades =
                    atividadeDAO.listar();

            request.setAttribute(
                    "participacoes",
                    participacoes
            );

            request.setAttribute(
                    "estudantes",
                    estudantes
            );

            request.setAttribute(
                    "atividades",
                    atividades
            );

            request.getRequestDispatcher(
            		"/WEB-INF/views/admin/participacoes.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Erro ao listar participações.",
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

            Long estudanteId = Long.parseLong(
                    request.getParameter("estudanteId")
            );

            Long atividadeId = Long.parseLong(
                    request.getParameter("atividadeId")
            );

            // EXCLUSÃO
            if ("excluir".equals(acao)) {

                participacaoDAO.excluir(
                        estudanteId,
                        atividadeId
                );
                
                response.sendRedirect(
                	    request.getContextPath()
                	    + "/admin/participacoes?sucesso=exclusao"
                	);

                	return;

            // CADASTRO
            } else {

                // Verifica se a participação já existe
                if (participacaoDAO.existe(
                        estudanteId,
                        atividadeId)) {

                    request.setAttribute(
                        "erro",
                        "erro.participacao.duplicada"
                    );

                    doGet(request, response);

                    return;
                }

                String funcao =
                        request.getParameter("funcao");

                String descricaoContribuicao =
                        request.getParameter(
                            "descricaoContribuicao"
                        );

                Participacao participacao =
                        new Participacao();

                participacao.setEstudanteId(
                        estudanteId
                );

                participacao.setAtividadeId(
                        atividadeId
                );

                participacao.setFuncao(
                        funcao
                );

                participacao.setDescricaoContribuicao(
                        descricaoContribuicao
                );

                participacaoDAO.inserir(
                        participacao
                );
            }

            response.sendRedirect(
            	    request.getContextPath()
            	    + "/admin/participacoes?sucesso=cadastro"
            	);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao processar participação.",
                e
            );
        }
    }
}