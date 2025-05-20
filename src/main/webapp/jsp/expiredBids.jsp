<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inactive Bids Management</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
</head>
<body>

<h1 style="text-align: center;">Inactive Bids</h1>

<!-- Проверка на пустоту списка inactiveBids -->
<c:if test="${empty inactiveBids}">
    <p style="text-align: center; font-size: 18px;">Пока все ставки активны, ждите :)</p>
</c:if>

<!-- Если список не пуст, отображаем таблицу -->
<c:if test="${not empty inactiveBids}">
    <table>
        <tr>
            <th>ID ставки</th>
            <th>Цена лота</th>
            <th>Время окончания</th>
            <th>Пользователь</th>
            <th colspan="2">Действия</th>
        </tr>
        <c:forEach var="bid" items="${inactiveBids}">
            <tr>
                <td>${bid.bidId}</td>
                <td>${bid.bidAmount}</td>
                <td>${bid.bidTime}</td>
                <td>${bid.auctionistId}</td>
                <td>
                    <form action="/controller" method="post" style="display:inline;">
                        <input type="hidden" name="command" value="CLOSEBID">
                        <input type="hidden" name="lotId" value="${bid.lotId}">
                        <input type="hidden" name="bidId" value="${bid.bidId}">
                        <input type="hidden" name="auctionistId" value="${bid.auctionistId}">
                        <button type="submit" class="button">Завершить ставку</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
