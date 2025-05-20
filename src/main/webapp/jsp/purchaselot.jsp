<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Go ahead for new purchases, ${user}!</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">

    <script>
        function openModal() {
            document.getElementById("lotFormModal").style.display = "flex";
        }

        function closeModal() {
            document.getElementById("lotFormModal").style.display = "none";
        }

        function openBetModal(lotId, currentBid, priceStep) {
            // Преобразуем значения currentBid и priceStep в числа
            const numericCurrentBid = parseFloat(currentBid);
            const numericPriceStep = parseFloat(priceStep);

            // Устанавливаем id лота
            document.getElementById('betLotId').value = lotId;

            // Вычисляем минимальное значение для ставки
            const minBid = numericCurrentBid + numericPriceStep;

            // Устанавливаем минимальное значение для ставки
            document.getElementById('betAmount').min = minBid;

            // Устанавливаем шаг ставки
            document.getElementById('betAmount').step = numericPriceStep;

            // Устанавливаем начальную сумму ставки
            document.getElementById('betAmount').value = minBid;

            // Открываем модальное окно для ставки
            document.getElementById('betFormModal').style.display = 'flex';
        }

        function closeBetModal() {
            document.getElementById('betFormModal').style.display = 'none';
        }
    </script>

</head>
<body>

<div id="lotFormModal" class="modal" style="display: none;">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Детали лота</h2>
        <form>
            <label for="viewTitle">Название лота:</label>
            <input type="text" id="viewTitle" value="${sessionScope.lotInfo.title}" readonly />

            <label for="viewDescription">Описание:</label>
            <textarea id="viewDescription" readonly>${sessionScope.lotInfo.description}</textarea>

            <label for="viewLotEndTime">Время окончания:</label>
            <input type="text" id="viewLotEndTime" value="${sessionScope.lotInfo.lotEndTime}" readonly />

            <label for="viewCurrentPrice">Текущая цена:</label>
            <input type="text" id="viewCurrentPrice" value="${sessionScope.lotInfo.lotCurrentPrice}" readonly />
        </form>
    </div>
</div>

<c:if test="${sessionScope.showLotModal}">
    <script>
        // Показываем модальное окно, если флаг установлен
        openModal();
    </script>
    <c:remove var="showLotModal" scope="session"/>
</c:if>

<div id="betFormModal" class="modal" style="display: none;">
    <div class="modal-content">
        <span class="close" onclick="closeBetModal()">&times;</span>
        <h2>Сделать ставку</h2>
        <form action="/controller" method="POST">
            <input type="hidden" name="command" value="BET">
            <input type="hidden" id="betLotId" name="lotId">

            <label for="betAmount">Сумма ставки:</label>
            <input
                    type="number"
                    id="betAmount"
                    name="betAmount"
                    required=""
                    min="0"
            />

            <button type="submit" class="button">Подтвердить ставку</button>
        </form>
    </div>
</div>


<h1 style="text-align: center;">Available Bids</h1>
<table>
    <tr>
        <th>ID ставки</th>
        <th>Цена лота</th>
        <th>Время окончания</th>
        <th>Аукционист</th>
        <th colspan="2">Действия</th>
    </tr>
    <c:forEach var="bid" items="${bids}">
        <tr>
            <td>${bid.bidId}</td>
            <td>${bid.bidAmount}</td>
            <td>${bid.bidTime}</td>
            <td>
                <c:choose>
                    <c:when test="${bid.auctionistId == 0}">
                        На этот лот пока никто не делал ставку
                    </c:when>
                    <c:when test="${bid.auctionistId == sessionScope.userId}">
                        Это вы
                    </c:when>
                    <c:otherwise>
                        Другой аукционист
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="/controller" method="get" style="display:inline;">
                    <input type="hidden" name="command" value="SHOWLOTINFO">
                    <input type="hidden" name="lotId" value="${bid.lotId}">
                    <button type="submit" class="button">Посмотреть информацию о лоте</button>
                </form>
            </td>
            <td>
                <button type="button" class="button" onclick="openBetModal('${bid.lotId}', '${bid.bidAmount}', '${bid.bidPriceStep}')">
                    Сделать ставку
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
