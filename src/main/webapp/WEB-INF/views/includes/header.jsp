<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.idioma}" />
<fmt:setBundle basename="messages" />

<%-- URLs PARA TROCA DE IDIOMA --%>

<c:url var="urlPt" value="">
    <c:param name="lang" value="pt_BR" />
    <c:if test="${not empty param.id}">
        <c:param name="id" value="${param.id}" />
    </c:if>
</c:url>

<c:url var="urlEn" value="">
    <c:param name="lang" value="en_US" />
    <c:if test="${not empty param.id}">
        <c:param name="id" value="${param.id}" />
    </c:if>
</c:url>

<c:url var="urlEs" value="">
    <c:param name="lang" value="es_ES" />
    <c:if test="${not empty param.id}">
        <c:param name="id" value="${param.id}" />
    </c:if>
</c:url>

<c:url var="urlFr" value="">
    <c:param name="lang" value="fr_FR" />
    <c:if test="${not empty param.id}">
        <c:param name="id" value="${param.id}" />
    </c:if>
</c:url>


<header class="cabecalho-site">

    <%-- BARRA SUPERIOR --%>

    <div class="barra-superior">

        <div class="container">

            <span>
                Instituto Federal de Mato Grosso do Sul
            </span>


            <div class="idiomas">

                <span>
                    <fmt:message key="admin.idioma" />:
                </span>

                <a href="${urlPt}">PT</a>

                <a href="${urlEn}">EN</a>

                <a href="${urlEs}">ES</a>

                <a href="${urlFr}">FR</a>

            </div>

        </div>

    </div>


    <%-- IDENTIDADE DO SITE --%>

    <div class="container cabecalho-principal">

        <div class="identidade-site">


            <%-- MARCA OFICIAL DO IFMS --%>

            <div class="marca-ifms">

                <a
                    href="${pageContext.request.contextPath}/"
                    aria-label="IFMS - Campus Campo Grande">

                    <img
                        src="${pageContext.request.contextPath}/img/logo/ifms-campus-campo-grande.png"
                        alt="IFMS - Campus Campo Grande"
                        class="logo-ifms">

                </a>

            </div>


            <%-- TÍTULO DO PORTAL --%>

            <div class="titulo-site">

                <span>
                    Laboratório de Robótica
                </span>

                <strong>
                    <fmt:message key="titulo.portal" />
                </strong>

            </div>


        </div>

    </div>


    <%-- MENU PRINCIPAL --%>

    <nav class="navbar navbar-expand-lg navegacao-principal">

        <div class="container">


            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#menuPrincipal"
                aria-controls="menuPrincipal"
                aria-expanded="false"
                aria-label="Abrir menu">

                <span class="navbar-toggler-icon"></span>

            </button>


            <div
                class="collapse navbar-collapse"
                id="menuPrincipal">


                <ul class="navbar-nav">


                    <li class="nav-item">

                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/">

                            <fmt:message key="menu.inicio" />

                        </a>

                    </li>


                    <li class="nav-item">

                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/sobre">

                            <fmt:message key="menu.sobre" />

                        </a>

                    </li>


                    <li class="nav-item">

                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/estudantes">

                            <fmt:message key="menu.estudantes" />

                        </a>

                    </li>


                    <li class="nav-item">

                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/atividades">

                            <fmt:message key="menu.atividades" />

                        </a>

                    </li>


                </ul>


            </div>

        </div>

    </nav>

</header>