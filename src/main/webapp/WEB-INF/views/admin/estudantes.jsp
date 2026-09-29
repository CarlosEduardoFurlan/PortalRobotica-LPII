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

<title><fmt:message key="admin.estudantes.titulo" /> - <fmt:message
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


			<%-- TÍTULO DA PÁGINA --%>

			<div class="titulo-pagina">

				<h1>
					<fmt:message key="admin.estudantes.titulo" />
				</h1>

			</div>



			<%-- MENSAGEM DE ERRO --%>

			<c:if test="${not empty erro}">

				<div class="alert alert-danger" role="alert">

					<strong> <fmt:message key="admin.erro" />:
					</strong> ${erro}

				</div>

			</c:if>



			<div class="row g-4">

				<c:if test="${param.sucesso == 'cadastro'}">
					<div class="alert alert-success" role="alert">
						<fmt:message key="admin.estudantes.sucessoCadastro" />
					</div>
				</c:if>

				<c:if test="${param.sucesso == 'exclusao'}">
					<div class="alert alert-danger" role="alert">
						<fmt:message key="admin.estudantes.sucessoExclusao" />
					</div>
				</c:if>


				<%-- FORMULÁRIO --%>

				<div class="col-lg-4">


					<div class="card card-formulario-admin shadow-sm">

						<div class="card-body p-4">


							<h2 class="h5 mb-4">

								<fmt:message key="admin.estudantes.cadastrar" />

							</h2>


							<form method="post"
								action="${pageContext.request.contextPath}/admin/estudantes"
								enctype="multipart/form-data">


								<%-- NOME --%>

								<div class="mb-3">

									<label for="nome" class="form-label"> <fmt:message
											key="admin.estudantes.nome" />

									</label> <input type="text" class="form-control" id="nome" name="nome"
										required>

								</div>



								<%-- MINIBIO --%>

								<div class="mb-3">

									<label for="minibio" class="form-label"> <fmt:message
											key="admin.estudantes.minibio" />

									</label>

									<textarea class="form-control" id="minibio" name="minibio"
										rows="4"></textarea>

								</div>



								<%-- FOTO --%>

								<div class="mb-4">

									<label for="foto" class="form-label"> <fmt:message
											key="admin.estudantes.foto" />

									</label> <input type="file" class="form-control" id="foto" name="foto"
										accept="image/png,image/jpeg,image/webp">

								</div>



								<%-- BOTÃO CADASTRAR --%>

								<button type="submit" class="btn btn-ifms w-100">

									<fmt:message key="admin.estudantes.botaoCadastrar" />

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

								<fmt:message key="admin.estudantes.lista" />

							</h2>



							<c:choose>


								<c:when test="${empty estudantes}">

									<div class="alert alert-light border mb-0" role="alert">

										<fmt:message key="admin.estudantes.vazio" />

									</div>

								</c:when>


								<c:otherwise>


									<div class="table-responsive">


										<table class="table tabela-admin align-middle mb-0">


											<thead>

												<tr>

													<th><fmt:message key="admin.id" /></th>

													<th><fmt:message key="admin.estudantes.nome" /></th>

													<th><fmt:message key="admin.estudantes.minibio" /></th>

													<th class="text-end"><fmt:message key="admin.acoes" />
													</th>

												</tr>

											</thead>


											<tbody>


												<c:forEach var="estudante" items="${estudantes}">


													<tr>


														<td>${estudante.id}</td>


														<td><strong> ${estudante.nome} </strong></td>


														<td class="text-secondary">${estudante.minibio}</td>


														<td class="text-end">


															<form method="post"
																action="${pageContext.request.contextPath}/admin/estudantes"
																class="d-inline">


																<input type="hidden" name="acao" value="excluir">


																<input type="hidden" name="id" value="${estudante.id}">


																<button type="submit"
																	class="btn btn-outline-danger btn-sm"
																	onclick="return confirm('<fmt:message key="admin.estudantes.confirmarExclusao" />');">

																	<fmt:message key="admin.estudantes.excluir" />

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