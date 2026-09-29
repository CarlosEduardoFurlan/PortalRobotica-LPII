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

<title><fmt:message key="estudantes.titulo" /> - <fmt:message
		key="titulo.portal" /></title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<jsp:include page="/WEB-INF/views/includes/header.jsp" />


	<main>

		<!-- TÍTULO DA PÁGINA -->

		<section class="py-5 bg-light">

			<div class="container">

				<div class="titulo-pagina mb-0">

					<h1>
						<fmt:message key="estudantes.titulo" />
					</h1>

					<p class="lead text-secondary mt-3 mb-0">
						<fmt:message key="inicio.conhecerEstudantes" />
					</p>

				</div>

			</div>

		</section>


		<!-- ESTUDANTES -->

		<section class="py-5">

			<div class="container">

				<c:choose>

					<c:when test="${empty estudantes}">

						<div class="alert alert-light border" role="alert">

							<fmt:message key="estudantes.vazio" />

						</div>

					</c:when>

					<c:otherwise>

						<div class="row g-4">

							<c:forEach var="estudante" items="${estudantes}">

								<div class="col-sm-6 col-lg-4">

									<article class="card card-estudante h-100 shadow-sm">

										<!-- FOTO -->

										<c:choose>

											<c:when test="${not empty estudante.foto}">

												<img
													src="${pageContext.request.contextPath}/foto-estudante?arquivo=${estudante.foto}"
													class="card-img-top foto-estudante" alt="${estudante.nome}">

											</c:when>

											<c:otherwise>

												<div class="foto-estudante-placeholder">

													<span> IFMS </span>

												</div>

											</c:otherwise>

										</c:choose>


										<!-- INFORMAÇÕES -->

										<div class="card-body p-4">

											<h2 class="h5 card-title mb-3">${estudante.nome}</h2>

											<c:if test="${not empty estudante.minibio}">

												<p class="card-text text-secondary mb-0">
													${estudante.minibio}</p>

											</c:if>

											<div class="mt-4">
												<h3 class="h6 fw-bold">
													<fmt:message key="estudantes.atividades" />
												</h3>

												<c:choose>
													<c:when test="${empty estudante.atividades}">
														<p class="text-muted mb-0">
															<fmt:message key="estudantes.semAtividades" />
														</p>
													</c:when>

													<c:otherwise>
														<ul class="list-group list-group-flush">
															<c:forEach var="atividade"
																items="${estudante.atividades}">
																<li class="list-group-item px-0">${atividade}</li>
															</c:forEach>
														</ul>
													</c:otherwise>
												</c:choose>
											</div>

										</div>

									</article>

								</div>

							</c:forEach>

						</div>



					</c:otherwise>

				</c:choose>

			</div>

		</section>

	</main>


	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>