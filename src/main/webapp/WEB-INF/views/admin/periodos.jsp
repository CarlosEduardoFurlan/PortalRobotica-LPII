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

<title><fmt:message key="admin.periodos.titulo" /> - <fmt:message
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
					<fmt:message key="admin.periodos.titulo" />
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
						<fmt:message key="admin.periodos.sucessoCadastro" />
					</div>
				</c:if>

				<c:if test="${param.sucesso == 'exclusao'}">
					<div class="alert alert-danger" role="alert">
						<fmt:message key="admin.periodos.sucessoExclusao" />
					</div>
				</c:if>


				<%-- FORMULÁRIO --%>

				<div class="col-lg-4">

					<div class="card card-formulario-admin shadow-sm">

						<div class="card-body p-4">

							<h2 class="h5 mb-4">

								<fmt:message key="admin.periodos.cadastrar" />

							</h2>


							<form method="post"
								action="${pageContext.request.contextPath}/admin/periodos">


								<%-- ANO --%>

								<div class="mb-3">

									<label for="ano" class="form-label"> <fmt:message
											key="admin.periodos.ano" />

									</label> <input type="number" class="form-control" id="ano" name="ano"
										min="2000" max="2100" required>

								</div>


								<%-- SEMESTRE --%>

								<div class="mb-4">

									<label for="semestre" class="form-label"> <fmt:message
											key="admin.periodos.semestre" />

									</label> <select class="form-select" id="semestre" name="semestre"
										required>

										<option value="">
											<fmt:message key="admin.selecione" />
										</option>

										<option value="1">
											<fmt:message key="admin.periodos.primeiroSemestre" />
										</option>

										<option value="2">
											<fmt:message key="admin.periodos.segundoSemestre" />
										</option>

									</select>

								</div>


								<%-- BOTÃO --%>

								<button type="submit" class="btn btn-ifms w-100">

									<fmt:message key="admin.periodos.botaoCadastrar" />

								</button>


							</form>

						</div>

					</div>

				</div>


				<%-- LISTAGEM --%>

				<div class="col-lg-8">

					<div class="card shadow-sm">

						<div class="card-body p-4">

							<h2 class="h5 mb-4">

								<fmt:message key="admin.periodos.lista" />

							</h2>


							<c:choose>

								<c:when test="${empty periodos}">

									<div class="alert alert-light border mb-0" role="alert">

										<fmt:message key="admin.periodos.vazio" />

									</div>

								</c:when>


								<c:otherwise>

									<div class="table-responsive">

										<table class="table tabela-admin align-middle mb-0">

											<thead>

												<tr>

													<th><fmt:message key="admin.id" /></th>

													<th><fmt:message key="admin.periodos.ano" /></th>

													<th><fmt:message key="admin.periodos.semestre" /></th>

													<th class="text-end"><fmt:message key="admin.acoes" />
													</th>

												</tr>

											</thead>


											<tbody>

												<c:forEach var="periodo" items="${periodos}">

													<tr>

														<td>${periodo.id}</td>

														<td><strong> ${periodo.ano} </strong></td>

														<td><c:choose>

																<c:when test="${periodo.semestre == 1}">

																	<fmt:message key="admin.periodos.primeiroSemestre" />

																</c:when>

																<c:otherwise>

																	<fmt:message key="admin.periodos.segundoSemestre" />

																</c:otherwise>

															</c:choose></td>

														<td class="text-end">

															<form method="post"
																action="${pageContext.request.contextPath}/admin/periodos"
																class="d-inline">

																<input type="hidden" name="acao" value="excluir">

																<input type="hidden" name="id" value="${periodo.id}">

																<button type="submit"
																	class="btn btn-outline-danger btn-sm"
																	onclick="return confirm('<fmt:message key="admin.periodos.confirmarExclusao" />');">

																	<fmt:message key="admin.periodos.excluir" />

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