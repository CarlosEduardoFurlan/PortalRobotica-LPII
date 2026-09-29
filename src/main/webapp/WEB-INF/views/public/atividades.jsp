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

<title><fmt:message key="atividades.titulo" /> - <fmt:message
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

		<!-- TÍTULO -->

		<section class="py-5 bg-light">

			<div class="container">

				<div class="titulo-pagina mb-0">

					<h1>
						<fmt:message key="atividades.titulo" />
					</h1>

					<p class="lead text-secondary mt-3 mb-0">
						<fmt:message key="inicio.conhecerAtividades" />
					</p>

				</div>

			</div>

		</section>


		<!-- LISTAGEM -->

		<section class="py-5">

			<div class="container">

				<c:choose>

					<c:when test="${empty atividades}">

						<div class="alert alert-light border" role="alert">

							<fmt:message key="atividades.vazio" />

						</div>

					</c:when>

					<c:otherwise>

						<div class="row g-4">

							<c:forEach var="atividade" items="${atividades}">

								<div class="col-md-6 col-lg-4">

									<article class="card card-atividade h-100 shadow-sm">

										<div class="card-body p-4 d-flex flex-column">

											<!-- TIPO -->

											<div class="mb-3">
												<fmt:message key="atividade.tipo.${atividade.tipo}" />
											</div>


											<!-- TÍTULO -->

											<h2 class="h5 card-title">${atividade.titulo}</h2>


											<!-- DESCRIÇÃO -->

											<p class="card-text text-secondary flex-grow-1">
												${atividade.descricao}</p>


											<!-- INFORMAÇÕES -->

											<div class="dados-atividade">

												<div>

													<strong> <fmt:message key="atividades.dataInicio" />:
													</strong> <fmt:formatDate value="${atividade.dataInicio}" pattern="dd/MM/yyyy" />

												</div>


												<c:if test="${not empty atividade.dataFim}">

													<div>

														<strong> <fmt:message key="atividades.dataFim" />:
														</strong> <fmt:formatDate value="${atividade.dataFim}" pattern="dd/MM/yyyy" />

													</div>

												</c:if>


												<div>

													<strong> <fmt:message key="atividades.situacao" />:
													</strong> <fmt:message key="atividade.status.${atividade.status}" />
												</div>

											</div>

											<div>
												<strong> <fmt:message key="atividades.periodos" />:
												</strong> <span> <c:choose>
														<c:when test="${empty atividade.periodos}">
															<fmt:message key="atividades.semPeriodos" />
														</c:when>

														<c:otherwise>
															<c:forEach var="periodo" items="${atividade.periodos}"
																varStatus="status">
                    											${periodo.ano}/${periodo.semestre}<c:if
																	test="${not status.last}">, </c:if>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</span>
											</div>


											<!-- DETALHES -->

											<div class="mt-4">

												<a
													href="${pageContext.request.contextPath}/atividade?id=${atividade.id}"
													class="btn btn-ifms"> <fmt:message
														key="atividades.verDetalhes" />

												</a>

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