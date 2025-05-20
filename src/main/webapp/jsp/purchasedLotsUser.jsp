<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your posted lots</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
</head>
<body>

<h1 style="text-align: center;">Your purchased lots</h1>

<div id="lotsTableContainer">
    <c:choose>
        <c:when test="${not empty purchasedLots}">
            <table>
                <thead>
                <tr>
                    <th>ID Лота</th>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Начальная цена</th>
                    <th>Окончательная цена</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lot" items="${purchasedLots}">
                    <tr>
                        <td>${lot.lotId}</td>
                        <td>${lot.title}</td>
                        <td>${lot.description}</td>
                        <td>${lot.lotStartPrice}</td>
                        <td>${lot.lotCurrentPrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p style="text-align: center; margin-top: 20px;">No lots available at the moment.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
