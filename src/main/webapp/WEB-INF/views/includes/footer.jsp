<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.idioma}" />
<fmt:setBundle basename="messages" />

<footer class="rodape-site">

    <div class="container">

        <div class="row">

            <div class="col-md-7">

                <strong>
                    <fmt:message key="admin.header.instituto" />
                </strong>

                <p>
                    <fmt:message key="footer.campus" />
                </p>

                <p>
                    <fmt:message key="footer.laboratorio" />
                </p>

            </div>

            <div class="col-md-5">

                <strong>
                    <fmt:message key="titulo.portal" />
                </strong>

                <p>
                    <fmt:message key="footer.projeto" />
                </p>

            </div>

        </div>

        <hr>

        <p class="rodape-final">
            <fmt:message key="footer.ifmsCampus" />
        </p>

    </div>

</footer>