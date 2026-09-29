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

<title><fmt:message key="titulo.portal" /></title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- CSS do projeto -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- Cabeçalho -->
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />


	<main>

		<!-- =====================================
             APRESENTAÇÃO
             ===================================== -->

		<section class="py-5 bg-light hero-robotica">
		
			<div class="container py-4 hero-conteudo">

				<div class="row align-items-center g-5">

					<div class="col-lg-7">

						<h1 class="display-5 fw-bold mb-3">
							<fmt:message key="inicio.titulo" />
						</h1>

						<p class="lead text-secondary mb-4">
							<fmt:message key="inicio.descricao" />
						</p>

						<div class="d-flex flex-wrap gap-3">

							<a href="${pageContext.request.contextPath}/atividades"
								class="btn btn-ifms btn-lg"> <fmt:message
									key="inicio.conhecerAtividades" />

							</a>

						</div>

					</div>


					<div class="col-lg-5">

						<div class="card hero-card shadow-sm border-0">

							<div class="card-body p-4 p-lg-5">

								<div class="hero-card-marca mb-4">
									<span class="hero-card-sigla"> IFMS </span>
								</div>


								<p class="text-secondary mb-4">
									<fmt:message key="sobre.portalTexto" />
								</p>

								<div class="hero-card-detalhe">

									<strong> <fmt:message key="titulo.portal" />
									</strong> <span> <fmt:message key="footer.campus" />
									</span>

								</div>

							</div>

						</div>

					</div>

				</div>

			</div>

		</section>


		<!-- =====================================
             ACESSOS PRINCIPAIS
             ===================================== -->

		<section class="py-5">

			<div class="container">

				<div class="titulo-pagina">

					<h2>
						<fmt:message key="inicio.explorar" />
					</h2>

				</div>


				<div class="row g-4">

					<!-- SOBRE -->

					<div class="col-md-4">

						<div class="card h-100 shadow-sm card-acesso-home">

							<div class="card-body p-4">

								<h3 class="h5">
									<fmt:message key="menu.sobre" />
								</h3>

								<p class="text-secondary">
									<fmt:message key="sobre.texto1" />
								</p>

								<a href="${pageContext.request.contextPath}/sobre"
									class="btn btn-outline-success"> <fmt:message
										key="menu.sobre" />

								</a>

							</div>

						</div>

					</div>


					<!-- ESTUDANTES -->

					<div class="col-md-4">

						<div class="card h-100 shadow-sm card-acesso-home">

							<div class="card-body p-4">

								<h3 class="h5">
									<fmt:message key="menu.estudantes" />
								</h3>

								<p class="text-secondary">
									<fmt:message key="inicio.conhecerEstudantes" />
								</p>

								<a href="${pageContext.request.contextPath}/estudantes"
									class="btn btn-outline-success"> <fmt:message
										key="inicio.conhecerEstudantes" />

								</a>

							</div>

						</div>

					</div>


					<!-- ATIVIDADES -->

					<div class="col-md-4">

						<div class="card h-100 shadow-sm card-acesso-home">

							<div class="card-body p-4">

								<h3 class="h5">
									<fmt:message key="menu.atividades" />
								</h3>

								<p class="text-secondary">
									<fmt:message key="inicio.conhecerAtividades" />
								</p>

								<a href="${pageContext.request.contextPath}/atividades"
									class="btn btn-outline-success"> <fmt:message
										key="inicio.conhecerAtividades" />

								</a>

							</div>

						</div>

					</div>

				</div>

			</div>

		</section>


		<!-- =====================================
             IDENTIDADE DO PROJETO
             ===================================== -->

		<section class="py-5 bg-light">

			<div class="container">

				<div class="row align-items-center g-4">

					<div class="col-lg-8">

						<div class="titulo-pagina">
							<h2>
								<fmt:message key="inicio.instagramTitulo" />
							</h2>
						</div>

						<p class="lead text-secondary mb-0">
							<fmt:message key="inicio.instagramTexto" />
						</p>

					</div>

					<div class="col-lg-4 text-lg-end">

						<a href="https://www.instagram.com/robotican.cg/" target="_blank"
							rel="noopener noreferrer" class="btn btn-ifms btn-lg">

							@robotican.cg </a>

					</div>

				</div>

			</div>

		</section>

	</main>


	<!-- Rodapé -->
	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />


	<!-- Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>