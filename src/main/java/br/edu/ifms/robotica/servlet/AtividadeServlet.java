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
import br.edu.ifms.robotica.dao.AtividadePeriodoDAO;
import br.edu.ifms.robotica.dao.CoordenadorDAO;
import br.edu.ifms.robotica.dao.PeriodoLetivoDAO;
import br.edu.ifms.robotica.model.Atividade;
import br.edu.ifms.robotica.model.Coordenador;
import br.edu.ifms.robotica.model.PeriodoLetivo;

@WebServlet("/admin/atividades")
public class AtividadeServlet extends HttpServlet {

    private AtividadeDAO atividadeDAO;
    private CoordenadorDAO coordenadorDAO;
    private PeriodoLetivoDAO periodoLetivoDAO;
    private AtividadePeriodoDAO atividadePeriodoDAO;

    @Override
    public void init() {

        atividadeDAO = new AtividadeDAO();
        coordenadorDAO = new CoordenadorDAO();
        periodoLetivoDAO = new PeriodoLetivoDAO();
        atividadePeriodoDAO = new AtividadePeriodoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	List<Atividade> atividades =
        	        atividadeDAO.listar();

        	List<Coordenador> coordenadores =
        	        coordenadorDAO.listar();

        	List<PeriodoLetivo> periodos =
        	        periodoLetivoDAO.listar();

        	request.setAttribute(
        	        "atividades",
        	        atividades
        	);

        	request.setAttribute(
        	        "coordenadores",
        	        coordenadores
        	);

        	request.setAttribute(
        	        "periodos",
        	        periodos
        	);

            request.getRequestDispatcher("/WEB-INF/views/admin/atividades.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar atividades.", e);
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

                atividadeDAO.excluir(id);
                
                response.sendRedirect(
                	    request.getContextPath()
                	    + "/admin/atividades?sucesso=exclusao"
                	);

                	return;

            // CADASTRO
            } else {

                String titulo =
                        request.getParameter("titulo");

                String tipo =
                        request.getParameter("tipo");

                String descricao =
                        request.getParameter("descricao");

                String dataInicio =
                        request.getParameter("dataInicio");

                String dataFim =
                        request.getParameter("dataFim");

                String status =
                        request.getParameter("status");

                Long coordenadorId =
                        Long.parseLong(
                            request.getParameter("coordenadorId")
                        );
                
                Long periodoId1 =
                        Long.parseLong(
                            request.getParameter("periodoId1")
                        );

                String periodoId2Param =
                        request.getParameter("periodoId2");
                
                List<String> tiposPermitidos = List.of(
                        "projeto",
                        "estagio",
                        "tarefa",
                        "oficina",
                        "palestra",
                        "evento",
                        "competicao",
                        "visita"
                );

                List<String> statusPermitidos = List.of(
                        "planejada",
                        "em_andamento",
                        "concluida"
                );
                
                if (!tiposPermitidos.contains(tipo)) {

                    request.setAttribute(
                        "erro",
                        "erro.atividade.tipoInvalido"
                    );

                    doGet(request, response);
                    return;
                }

                if (!statusPermitidos.contains(status)) {

                    request.setAttribute(
                        "erro",
                        "erro.atividade.statusInvalido"
                    );

                    doGet(request, response);
                    return;
                }

                // Converte a data inicial
                java.sql.Date inicio =
                        java.sql.Date.valueOf(dataInicio);

                // A data final pode ser nula
                java.sql.Date fim = null;

                if (dataFim != null && !dataFim.isEmpty()) {

                    fim = java.sql.Date.valueOf(dataFim);
                }

                // Validação das datas
                if (fim != null && fim.before(inicio)) {

                    request.setAttribute(
                        "erro",
                        "erro.atividade.dataInvalida"
                    );

                    doGet(request, response);

                    return;
                }

                Atividade atividade = new Atividade();

                atividade.setTitulo(titulo);
                atividade.setTipo(tipo);
                atividade.setDescricao(descricao);
                atividade.setDataInicio(inicio);
                atividade.setDataFim(fim);
                atividade.setStatus(status);
                atividade.setCoordenadorId(coordenadorId);

                
                Long atividadeId =
                        atividadeDAO.inserir(atividade);
                
                atividadePeriodoDAO.inserir(
                        atividadeId,
                        periodoId1
                );
                
                if (periodoId2Param != null
                        && !periodoId2Param.isEmpty()) {

                    Long periodoId2 =
                            Long.parseLong(periodoId2Param);

                    if (!periodoId1.equals(periodoId2)) {

                        atividadePeriodoDAO.inserir(
                                atividadeId,
                                periodoId2
                        );
                    }
                }
            }

            // Depois de cadastrar ou excluir,
            // volta para a listagem
            response.sendRedirect(
            	    request.getContextPath()
            	    + "/admin/atividades?sucesso=cadastro"
            	);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao processar atividade.",
                e
            );
        }
    }
}