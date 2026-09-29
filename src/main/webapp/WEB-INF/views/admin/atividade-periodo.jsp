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

<title><fmt:message key="admin.atividadePeriodo.titulo" /> - <fmt:message
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
					<fmt:message key="admin.atividadePeriodo.titulo" />
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


			<div class="row g-4">

				<c:if test="${param.sucesso == 'cadastro'}">
					<div class="alert alert-success" role="alert">
						<fmt:message key="admin.atividadePeriodo.sucessoCadastro" />
					</div>
				</c:if>

				<c:if test="${param.sucesso == 'exclusao'}">
					<div class="alert alert-danger" role="alert">
						<fmt:message key="admin.atividadePeriodo.sucessoExclusao" />
					</div>
				</c:if>


				<%-- FORMULÁRIO --%>

				<div class="col-lg-5">

					<div class="card card-formulario-admin shadow-sm">

						<div class="card-body p-4">

							<h2 class="h5 mb-4">

								<fmt:message key="admin.atividadePeriodo.cadastrar" />

							</h2>


							<form method="post"
								action="${pageContext.request.contextPath}/admin/atividade-periodo">


								<%-- ATIVIDADE --%>

								<div class="mb-3">

									<label for="atividadeId" class="form-label"> <fmt:message
											key="admin.atividadePeriodo.atividade" />

									</label> <select class="form-select" id="atividadeId"
										name="atividadeId" required>

										<option value="">
											<fmt:message key="admin.selecione" />
										</option>

										<c:forEach var="atividade" items="${atividades}">

											<option value="${atividade.id}">${atividade.titulo}
											</option>

										</c:forEach>

									</select>

								</div>


								<%-- PERÍODO --%>

								<div class="mb-4">

									<label for="periodoId" class="form-label"> <fmt:message
											key="admin.atividadePeriodo.periodo" />

									</label> <select class="form-select" id="periodoId" name="periodoId"
										required>

										<option value="">
											<fmt:message key="admin.selecione" />
										</option>

										<c:forEach var="periodo" items="${periodos}">

											<option value="${periodo.id}">
												${periodo.ano}/${periodo.semestre}</option>

										</c:forEach>

									</select>

								</div>


								<%-- BOTÃO --%>

								<button type="submit" class="btn btn-ifms w-100">

									<fmt:message key="admin.atividadePeriodo.botaoCadastrar" />

								</button>


							</form>

						</div>

					</div>

				</div>


				<%-- LISTAGEM --%>

				<div class="col-lg-7">

					<div class="card shadow-sm">

						<div class="card-body p-4">

							<h2 class="h5 mb-4">

								<fmt:message key="admin.atividadePeriodo.lista" />

							</h2>


							<c:choose>

								<c:when test="${empty relacionamentos}">

									<div class="alert alert-light border mb-0" role="alert">

										<fmt:message key="admin.atividadePeriodo.vazio" />

									</div>

								</c:when>


								<c:otherwise>

									<div class="table-responsive">

										<table class="table tabela-admin align-middle mb-0">

											<thead>

												<tr>

													<th><fmt:message
															key="admin.atividadePeriodo.atividade" /></th>

													<th><fmt:message key="admin.atividadePeriodo.periodo" />
													</th>

													<th class="text-end"><fmt:message key="admin.acoes" />
													</th>

												</tr>

											</thead>


											<tbody>

												<c:forEach var="relacionamento" items="${relacionamentos}">

													<tr>

														<td>${relacionamento.tituloAtividade}</td>

														<td>${relacionamento.periodoFormatado}</td>

														<td class="text-end">

															<form method="post"
																action="${pageContext.request.contextPath}/admin/atividade-periodo"
																class="d-inline">

																<input type="hidden" name="acao" value="excluir">

																<input type="hidden" name="atividadeId"
																	value="${relacionamento.atividadeId}"> <input
																	type="hidden" name="periodoId"
																	value="${relacionamento.periodoId}">

																<button type="submit"
																	class="btn btn-outline-danger btn-sm"
																	onclick="return confirm('<fmt:message key="admin.atividadePeriodo.confirmarExclusao" />');">

																	<fmt:message key="admin.atividadePeriodo.excluir" />

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


			</div>

		</div>

	</main>


	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>