<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.idioma}" />
<fmt:setBundle basename="messages" />

<header class="cabecalho-admin">

    <div class="barra-superior">
        <div class="container">

            <span>
                <fmt:message key="admin.header.instituto" />
            </span>

            <div class="idiomas">

                <span>
                    <fmt:message key="admin.idioma" />:
                </span>

                <a href="?lang=pt_BR">PT</a>
                <a href="?lang=en_US">EN</a>
                <a href="?lang=es_ES">ES</a>
                <a href="?lang=fr_FR">FR</a>

            </div>

        </div>
    </div>


    <div class="container cabecalho-principal">

        <div class="identidade-site">

            <div class="marca-ifms">

                <a href="${pageContext.request.contextPath}/">

                    <img
                        src="${pageContext.request.contextPath}/img/logo/ifms-campus-campo-grande.png"
                        alt="IFMS - Campus Campo Grande"
                        class="logo-ifms">

                </a>

            </div>

            <div class="titulo-site">

                <span>
                    <fmt:message key="admin.header.laboratorio" />
                </span>

                <strong>
                    <fmt:message key="admin.header.administracao" />
                </strong>

            </div>

        </div>

    </div>


    <nav class="navbar navbar-expand-lg navegacao-admin">

        <div class="container">

            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#menuAdmin"
                aria-controls="menuAdmin"
                aria-expanded="false">

                <span class="navbar-toggler-icon"></span>

            </button>


            <div
                class="collapse navbar-collapse"
                id="menuAdmin">

                <ul class="navbar-nav">

                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/admin/estudantes">

                            <fmt:message key="menu.estudantes" />

                        </a>
                    </li>


                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/admin/coordenadores">

                            <fmt:message key="admin.header.coordenadores" />

                        </a>
                    </li>


                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/admin/atividades">

                            <fmt:message key="menu.atividades" />

                        </a>
                    </li>


                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/admin/periodos">

                            <fmt:message key="admin.header.periodos" />

                        </a>
                    </li>


                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/admin/participacoes">

                            <fmt:message key="admin.header.participacoes" />

                        </a>
                    </li>

                </ul>


                <div class="ms-lg-auto">

                    <a
                        href="${pageContext.request.contextPath}/"
                        class="nav-link">

                        <fmt:message key="admin.header.verPortal" />

                    </a>

                </div>

            </div>

        </div>

    </nav>

</header>