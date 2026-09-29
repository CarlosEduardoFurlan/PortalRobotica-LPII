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

<title>${atividade.titulo}-<fmt:message key="titulo.portal" />
</title>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>


<body>


	<jsp:include page="/WEB-INF/views/includes/header.jsp" />


	<main>


		<%-- CABEÇALHO DA ATIVIDADE --%>

		<section class="py-5 bg-light">

			<div class="container">


				<a href="${pageContext.request.contextPath}/atividades"
					class="link-voltar"> &larr; <fmt:message key="detalhe.voltar" />

				</a>


				<div class="row mt-4">

					<div class="col-lg-9">


						<p class="text-uppercase fw-semibold text-success mb-2">
							<fmt:message key="atividade.tipo.${atividade.tipo}" />
						</p>


						<div class="titulo-pagina mb-0">

							<h1>${atividade.titulo}</h1>

						</div>


					</div>

				</div>


			</div>

		</section>



		<%-- INFORMAÇÕES DA ATIVIDADE --%>

		<section class="py-5">

			<div class="container">

				<div class="row g-5">


					<div class="col-lg-8">


						<h2 class="h4 mb-4">

							<fmt:message key="atividades.descricao" />

						</h2>


						<p class="lead text-secondary">${atividade.descricao}</p>


					</div>



					<div class="col-lg-4">


						<aside class="card shadow-sm">

							<div class="card-body p-4">


								<div class="informacoes-atividade">


									<div>

										<strong> <fmt:message
												key="atividade.tipo.${atividade.tipo}" />
										</strong>

									</div>


									<div>

										<span> <fmt:message key="atividades.dataInicio" />
										</span> <strong><fmt:formatDate value="${atividade.dataInicio}" pattern="dd/MM/yyyy" /></strong>

									</div>


									<c:if test="${not empty atividade.dataFim}">

										<div>

											<span> <fmt:message key="atividades.dataFim" />
											</span> <strong><fmt:formatDate value="${atividade.dataFim}" pattern="dd/MM/yyyy" /></strong>

										</div>

									</c:if>

									<div>
										<span> <fmt:message key="atividades.periodos" />
										</span> <strong> <c:choose>
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
										</strong>
									</div>

									<div>

										<strong> <fmt:message
												key="atividade.status.${atividade.status}" />
										</strong>

									</div>


								</div>


							</div>

						</aside>


					</div>


				</div>

			</div>

		</section>



		<%-- PARTICIPANTES --%>

		<section class="py-5 bg-light">

			<div class="container">


				<div class="titulo-pagina">

					<h2>
						<fmt:message key="detalhe.participantes" />
					</h2>

				</div>


				<c:choose>


					<c:when test="${empty participacoes}">

						<div class="alert alert-light border" role="alert">

							<fmt:message key="detalhe.semParticipantes" />

						</div>

					</c:when>


					<c:otherwise>


						<div class="row g-4">


							<c:forEach var="participacao" items="${participacoes}">


								<div class="col-md-6 col-lg-4">


									<article class="card h-100 card-participante">


										<div class="card-body p-4">


											<h3 class="h5">${participacao.nomeEstudante}</h3>


											<c:if test="${not empty participacao.funcao}">

												<p class="funcao-participante">

													<strong> <fmt:message key="detalhe.funcao" />:

													</strong> ${participacao.funcao}

												</p>

											</c:if>


											<c:if test="${not empty participacao.descricaoContribuicao}">

												<p class="text-secondary mb-0">

													<strong> <fmt:message key="detalhe.contribuicao" />:

													</strong> ${participacao.descricaoContribuicao}

												</p>

											</c:if>


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