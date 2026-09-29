<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estudantes</title>
</head>

<body>

    <h1>Estudantes</h1>

    <c:choose>

        <c:when test="${empty estudantes}">
            <p>Nenhum estudante cadastrado.</p>
        </c:when>

        <c:otherwise>

            <table border="1">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Minibio</th>
                        <th>Foto</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="estudante" items="${estudantes}">

                        <tr>
                            <td>${estudante.id}</td>
                            <td>${estudante.nome}</td>
                            <td>${estudante.minibio}</td>
                            <td>${estudante.foto}</td>
                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</body>
</html>