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

<title><fmt:message key="sobre.titulo" /> - <fmt:message
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

		<section class="py-5">

			<div class="container">

				<div class="row">

					<div class="col-lg-8">

						<div class="titulo-pagina">

							<h1>
								<fmt:message key="sobre.titulo" />
							</h1>

						</div>

						<p class="lead">
							<fmt:message key="sobre.texto1" />
						</p>

						<p class="text-secondary">
							<fmt:message key="sobre.texto2" />
						</p>

					</div>

				</div>

			</div>

		</section>
		
		<section class="py-5 secao-robotica-ifms">

		    <div class="container">
		
		        <div class="row align-items-center g-5">
		
		            <div class="col-lg-5">
		
		                <div class="titulo-pagina">
		
		                    <h2>
		                        <fmt:message key="sobre.roboticaTitulo" />
		                    </h2>
		
		                </div>
		
		            </div>
		
		            <div class="col-lg-7">
		
		                <p class="lead">
		                    <fmt:message key="sobre.roboticaTexto1" />
		                </p>
		
		                <p class="text-secondary mb-0">
		                    <fmt:message key="sobre.roboticaTexto2" />
		                </p>
		
		            </div>
		
		        </div>
		
		    </div>
		
		</section>


		

		<section class="py-5">

			<div class="container">

				<div class="card border-0 bg-light">

					<div class="card-body p-4 p-lg-5">

						<div class="row align-items-center">

							<div class="col-lg-8">

								<h2 class="h4">
									<fmt:message key="inicio.conhecerAtividades" />
								</h2>

								<p class="text-secondary mb-lg-0">
									<fmt:message key="inicio.descricao" />
								</p>

							</div>

							<div class="col-lg-4 text-lg-end">

								<a href="${pageContext.request.contextPath}/atividades"
									class="btn btn-ifms"> <fmt:message key="menu.atividades" />

								</a>

							</div>

						</div>

					</div>

				</div>

			</div>

		</section>

	</main>


	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>