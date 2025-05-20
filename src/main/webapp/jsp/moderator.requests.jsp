<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Управление запросами</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
    <style>
        /* Стили для кнопок галочки и крестика */
        .action-button {
            padding: 8px 12px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin: 0 5px;
            transition: background-color 0.3s ease;
        }

        .approve-button {
            background-color: #4CAF50; /* Зелёный */
            color: white;
        }

        .reject-button {
            background-color: #f44336; /* Красный */
            color: white;
        }

        .approve-button:hover {
            background-color: #45a049;
        }

        .reject-button:hover {
            background-color: #d32f2f;
        }

        /* Заблокированные кнопки */
        .action-button:disabled {
            background-color: #cccccc; /* Серый цвет */
            color: #666666;
            cursor: not-allowed;
        }
    </style>

    <script>
        // Set the minimum date for the input field to be today
        document.addEventListener('DOMContentLoaded', function() {
            const today = new Date().toISOString().split('T')[0];

            document.querySelectorAll('input[type="date"]').forEach(input => {
                input.setAttribute('min', today);
            });
        });

        function checkFields(requestId) {
            console.log("Проверка поля для запроса: ", requestId);

            // Находим элементы даты и шага
            const dateInput = document.getElementById("endDate");
            const stepInput = document.getElementById('priceStep');

            console.log("Элемент даты:", dateInput);
            console.log("Элемент шага:", stepInput);

            if (dateInput && stepInput) { // Проверяем существование элементов
                const dateInputValue = dateInput.value;
                const stepInputValue = stepInput.value;

                // Кнопки действий
                const approveButton = document.getElementById("approve");
                const rejectButton = document.getElementById("reject");

                const hiddenDateInput = document.getElementById("hiddenEndDate");
                const hiddenStepInput = document.getElementById("hiddenPriceStep");

                if (dateInputValue !== '' && stepInputValue !== '') {
                    console.log("Оба поля заполнены.");
                    approveButton.disabled = false;
                    rejectButton.disabled = false;

                    hiddenDateInput.value = dateInput.value;
                    hiddenStepInput.value = stepInput.value;
                } else {
                    console.log("Одно или оба поля пусты.");
                    approveButton.disabled = true;
                    rejectButton.disabled = true;
                }
            } else {
                console.error("Не удалось найти элементы ввода по ID.");
            }
        }

    </script>
</head>
<body>
<div id="lotsTableContainer">

    <c:choose>
        <c:when test="${not empty activeRequests}">
            <table class="data-table">
                <thead>
                <tr>
                    <th>Номер запроса</th>
                    <th>Название лота</th>
                    <th>Описание лота</th>
                    <th>Начальная цена</th>
                    <th>Срок продажи</th>
                    <th>Шаг повышения</th>
                    <th>ID пользователя</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${activeRequests}">
                    <tr>
                        <td>${request.requestId}</td>
                        <td>${request.title}</td>
                        <td>${request.description}</td>
                        <td>${request.startPrice}</td>
                        <td>
                            <input type="date" id="endDate" oninput="checkFields('${request.requestId}')">
                        </td>
                        <td>
                            <input type="number" id="priceStep" oninput="checkFields('${request.requestId}')">
                        </td>
                        <td>${request.userId}</td>
                        <td>
                            <form action="/controller" method="post" style="display: inline;">
                                <input type="hidden" name="command" value="SETSTATUSFORLOT">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <input type="hidden" name="status" value="Approved">
                                <input type="hidden" name="endDate" id="hiddenEndDate">
                                <input type="hidden" name="priceStep" id="hiddenPriceStep">
                                <button type="submit" class="action-button approve-button" id="approve" disabled>✔</button>
                            </form>
                            <form action="/controller" method="post" style="display: inline;">
                                <input type="hidden" name="command" value="SETSTATUSFORLOT">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <input type="hidden" name="status" value="Rejected">
                                <button type="submit" class="action-button reject-button" id="reject">✖</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p style="text-align: center;">Пока запросов нет, может быть, появятся немного позже :)</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
