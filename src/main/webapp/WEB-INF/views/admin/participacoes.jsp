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

<title><fmt:message key="admin.participacoes.titulo" /> - <fmt:message
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
					<fmt:message key="admin.participacoes.titulo" />
				</h1>

			</div>


			<%-- ERRO --%>

			<c:if test="${not empty erro}">

				<div class="alert alert-danger" role="alert">

					<strong> <fmt:message key="admin.erro" />:
					</strong>

					<fmt:message key="${erro}" />

				</div>

			</c:if>

			<c:if test="${param.sucesso == 'cadastro'}">
				<div class="alert alert-success" role="alert">
					<fmt:message key="admin.participacoes.sucessoCadastro" />
				</div>
			</c:if>

			<c:if test="${param.sucesso == 'exclusao'}">
				<div class="alert alert-danger" role="alert">
					<fmt:message key="admin.participacoes.sucessoExclusao" />
				</div>
			</c:if>


			<%-- FORMULÁRIO --%>

			<div class="card card-formulario-admin shadow-sm mb-4">

				<div class="card-body p-4">

					<h2 class="h5 mb-4">

						<fmt:message key="admin.participacoes.cadastrar" />

					</h2>


					<form method="post"
						action="${pageContext.request.contextPath}/admin/participacoes">


						<div class="row g-3">


							<%-- ESTUDANTE --%>

							<div class="col-md-6">

								<label for="estudanteId" class="form-label"> <fmt:message
										key="admin.participacoes.estudante" />

								</label> <select class="form-select" id="estudanteId" name="estudanteId"
									required>

									<option value="">
										<fmt:message key="admin.selecione" />
									</option>

									<c:forEach var="estudante" items="${estudantes}">

										<option value="${estudante.id}">${estudante.nome}</option>

									</c:forEach>

								</select>

							</div>


							<%-- ATIVIDADE --%>

							<div class="col-md-6">

								<label for="atividadeId" class="form-label"> <fmt:message
										key="admin.participacoes.atividade" />

								</label> <select class="form-select" id="atividadeId" name="atividadeId"
									required>

									<option value="">
										<fmt:message key="admin.selecione" />
									</option>

									<c:forEach var="atividade" items="${atividades}">

										<option value="${atividade.id}">${atividade.titulo}</option>

									</c:forEach>

								</select>

							</div>


							<%-- FUNÇÃO --%>

							<div class="col-md-4">

								<label for="funcao" class="form-label"> <fmt:message
										key="admin.participacoes.funcao" />

								</label> <input type="text" class="form-control" id="funcao"
									name="funcao">

							</div>


							<%-- CONTRIBUIÇÃO --%>

							<div class="col-md-8">

								<label for="descricaoContribuicao" class="form-label"> <fmt:message
										key="admin.participacoes.contribuicao" />

								</label>

								<textarea class="form-control" id="descricaoContribuicao"
									name="descricaoContribuicao" rows="3"></textarea>

							</div>


							<%-- BOTÃO --%>

							<div class="col-12">

								<button type="submit" class="btn btn-ifms">

									<fmt:message key="admin.participacoes.botaoCadastrar" />

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

						<fmt:message key="admin.participacoes.lista" />

					</h2>


					<c:choose>

						<c:when test="${empty participacoes}">

							<div class="alert alert-light border mb-0" role="alert">

								<fmt:message key="admin.participacoes.vazio" />

							</div>

						</c:when>


						<c:otherwise>

							<div class="table-responsive">

								<table class="table tabela-admin align-middle mb-0">

									<thead>

										<tr>

											<th><fmt:message key="admin.participacoes.estudante" />
											</th>

											<th><fmt:message key="admin.participacoes.atividade" />
											</th>

											<th><fmt:message key="admin.participacoes.funcao" /></th>

											<th><fmt:message key="admin.participacoes.contribuicao" />
											</th>

											<th class="text-end"><fmt:message key="admin.acoes" />
											</th>

										</tr>

									</thead>


									<tbody>

										<c:forEach var="participacao" items="${participacoes}">

											<tr>

												<td><strong> ${participacao.nomeEstudante} </strong></td>

												<td>${participacao.tituloAtividade}</td>

												<td>${participacao.funcao}</td>

												<td class="text-secondary">
													${participacao.descricaoContribuicao}</td>

												<td class="text-end">

													<form method="post"
														action="${pageContext.request.contextPath}/admin/participacoes"
														class="d-inline">

														<input type="hidden" name="acao" value="excluir">

														<input type="hidden" name="estudanteId"
															value="${participacao.estudanteId}"> <input
															type="hidden" name="atividadeId"
															value="${participacao.atividadeId}">

														<button type="submit"
															class="btn btn-outline-danger btn-sm"
															onclick="return confirm('<fmt:message key="admin.participacoes.confirmarExclusao" />');">

															<fmt:message key="admin.participacoes.excluir" />

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