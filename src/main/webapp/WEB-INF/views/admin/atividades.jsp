<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${empty sessionScope.idioma}">
	<c:set var="idioma" value="pt_BR" scope="session" />
</c:if>

<c:if test="${not empty param.lang}">
	<c:set var="idioma" value="${param.lang}" scope="session" />
</c:if>

<fmt:setLocale value="${sessionScope.idioma}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>

<html lang="${sessionScope.idioma}">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><fmt:message key="admin.atividades.titulo" /> - <fmt:message
		key="titulo.portal" /></title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<jsp:include page="/WEB-INF/views/includes/admin-header.jsp" />


	<main class="area-admin">

		<div class="container">


			<%-- TÍTULO --%>

			<div class="titulo-pagina">

				<h1>
					<fmt:message key="admin.atividades.titulo" />
				</h1>

			</div>


			<%-- MENSAGEM DE ERRO --%>

			<c:if test="${not empty erro}">

				<div class="alert alert-danger" role="alert">

					<strong> <fmt:message key="admin.erro" />:
					</strong>

					<fmt:message key="${erro}" />

				</div>

			</c:if>

			<c:if test="${param.sucesso == 'cadastro'}">
				<div class="alert alert-success" role="alert">
					<fmt:message key="admin.atividades.sucessoCadastro" />
				</div>
			</c:if>

			<c:if test="${param.sucesso == 'exclusao'}">
				<div class="alert alert-danger" role="alert">
					<fmt:message key="admin.atividades.sucessoExclusao" />
				</div>
			</c:if>


			<%-- FORMULÁRIO --%>

			<div class="card card-formulario-admin shadow-sm mb-4">

				<div class="card-body p-4">

					<h2 class="h5 mb-4">

						<fmt:message key="admin.atividades.cadastrar" />

					</h2>


					<form method="post"
						action="${pageContext.request.contextPath}/admin/atividades">


						<div class="row g-3">


							<%-- TÍTULO --%>

							<div class="col-md-8">

								<label for="titulo" class="form-label"> <fmt:message
										key="admin.atividades.tituloCampo" />

								</label> <input type="text" class="form-control" id="titulo"
									name="titulo" required>

							</div>


							<%-- TIPO --%>

							<div class="col-md-4">

								<label for="tipo" class="form-label"> <fmt:message
										key="admin.atividades.tipo" />
								</label> <select class="form-select" id="tipo" name="tipo" required>

									<option value="">
										<fmt:message key="admin.selecione" />
									</option>

									<option value="projeto">
										<fmt:message key="atividade.tipo.projeto" />
									</option>

									<option value="estagio">
										<fmt:message key="atividade.tipo.estagio" />
									</option>

									<option value="tarefa">
										<fmt:message key="atividade.tipo.tarefa" />
									</option>

									<option value="oficina">
										<fmt:message key="atividade.tipo.oficina" />
									</option>

									<option value="palestra">
										<fmt:message key="atividade.tipo.palestra" />
									</option>

									<option value="evento">
										<fmt:message key="atividade.tipo.evento" />
									</option>

									<option value="competicao">
										<fmt:message key="atividade.tipo.competicao" />
									</option>

									<option value="visita">
										<fmt:message key="atividade.tipo.visita" />
									</option>

								</select>

							</div>


							<%-- DESCRIÇÃO --%>

							<div class="col-12">

								<label for="descricao" class="form-label"> <fmt:message
										key="admin.atividades.descricao" />

								</label>

								<textarea class="form-control" id="descricao" name="descricao"
									rows="4" required></textarea>

							</div>


							<%-- DATA DE INÍCIO --%>

							<div class="col-md-3">

								<label for="dataInicio" class="form-label"> <fmt:message
										key="admin.atividades.dataInicio" />

								</label> <input type="date" class="form-control" id="dataInicio"
									name="dataInicio" required>

							</div>


							<%-- DATA DE FIM --%>

							<div class="col-md-3">

								<label for="dataFim" class="form-label"> <fmt:message
										key="admin.atividades.dataFim" />

								</label> <input type="date" class="form-control" id="dataFim"
									name="dataFim">

							</div>


							<%-- SITUAÇÃO --%>

							<div class="col-md-3">

								<label for="status" class="form-label"> <fmt:message
										key="admin.atividades.situacao" />
								</label> <select class="form-select" id="status" name="status" required>

									<option value="">
										<fmt:message key="admin.selecione" />
									</option>

									<option value="planejada">
										<fmt:message key="atividade.status.planejada" />
									</option>

									<option value="em_andamento">
										<fmt:message key="atividade.status.em_andamento" />
									</option>

									<option value="concluida">
										<fmt:message key="atividade.status.concluida" />
									</option>

								</select>

							</div>


							<%-- COORDENADOR --%>

							<div class="col-md-3">

								<label for="coordenadorId" class="form-label"> <fmt:message
										key="admin.atividades.coordenador" />

								</label> <select class="form-select" id="coordenadorId"
									name="coordenadorId" required>

									<option value="">
										<fmt:message key="admin.selecione" />
									</option>

									<c:forEach var="coordenador" items="${coordenadores}">

										<option value="${coordenador.id}">
											${coordenador.nome}</option>

									</c:forEach>

								</select>

							</div>


							<%-- BOTÃO --%>

							<div class="col-12">

								<button type="submit" class="btn btn-ifms">

									<fmt:message key="admin.atividades.botaoCadastrar" />

								</button>

							</div>


						</div>

					</form>

				</div>

			</div>


			<%-- LISTAGEM --%>

			<div class="card shadow-sm">

				<div class="card-body p-4">


					<h2 class="h5 mb-4">

						<fmt:message key="admin.atividades.lista" />

					</h2>


					<c:choose>

						<c:when test="${empty atividades}">

							<div class="alert alert-light border mb-0" role="alert">

								<fmt:message key="admin.atividades.vazio" />

							</div>

						</c:when>


						<c:otherwise>

							<div class="table-responsive">

								<table class="table tabela-admin align-middle mb-0">

									<thead>

										<tr>

											<th><fmt:message key="admin.id" /></th>

											<th><fmt:message key="admin.atividades.tituloCampo" />
											</th>

											<th><fmt:message key="admin.atividades.tipo" /></th>

											<th><fmt:message key="admin.atividades.dataInicio" /></th>

											<th><fmt:message key="admin.atividades.dataFim" /></th>

											<th><fmt:message key="admin.atividades.situacao" /></th>

											<th class="text-end"><fmt:message key="admin.acoes" />
											</th>

										</tr>

									</thead>


									<tbody>

										<c:forEach var="atividade" items="${atividades}">

											<tr>

												<td>${atividade.id}</td>

												<td><strong> ${atividade.titulo} </strong></td>

												<td>
												    <fmt:message key="atividade.tipo.${atividade.tipo}" />
												</td>

												<td><fmt:formatDate value="${atividade.dataInicio}"
														pattern="dd/MM/yyyy" /></td>

												<td>
												    <c:choose>
												
												        <c:when test="${not empty atividade.dataFim}">
												            <fmt:formatDate
												                value="${atividade.dataFim}"
												                pattern="dd/MM/yyyy" />
												        </c:when>
												
												        <c:otherwise>
												            -
												        </c:otherwise>
												
												    </c:choose>
												</td>

												<td>
												    <fmt:message key="atividade.status.${atividade.status}" />
												</td>

												<td class="text-end">

													<form method="post"
														action="${pageContext.request.contextPath}/admin/atividades"
														class="d-inline">

														<input type="hidden" name="acao" value="excluir">

														<input type="hidden" name="id" value="${atividade.id}">

														<button type="submit"
															class="btn btn-outline-danger btn-sm"
															onclick="return confirm('<fmt:message key="admin.atividades.confirmarExclusao" />');">

															<fmt:message key="admin.atividades.excluir" />

														</button>

													</form>

												</td>

											</tr>

										</c:forEach>

									</tbody>

								</table>

							</div>

						</c:otherwise>

					</c:choose>


				</div>

			</div>


		</div>

	</main>


	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>