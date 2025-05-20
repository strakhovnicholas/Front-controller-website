<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your posted lots, ${user}!</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">

    <script>
        function showCreateLotForm() {
            var formContainer = document.getElementById("createLotForm");
            var myLotsButton = document.getElementById("myLotsButton");
            if (formContainer.style.display === "block") {
                formContainer.style.display = "none";
                myLotsButton.style.display = "block";
                resetFormFields();
            } else {
                formContainer.style.display = "block";
                myLotsButton.style.display = "none";
            }
        }

        function showEditLotForm(lotId, title, description) {
            document.getElementById("editLotFormModal").style.display = "flex";

            document.getElementById("editLotId").value = lotId;
            document.getElementById("editTitle").value = title;
            document.getElementById("editDescription").value = description;
        }

        function closeModal() {
            document.getElementById("editLotFormModal").style.display = "none";
        }

    </script>

</head>

<body>
<div id="editLotFormModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Редактировать лот</h2>
        <form action="/controller" method="post" onsubmit="closeModal();">
            <input type="hidden" name="command" value="EDITLOT" />
            <input type="hidden" name="lotId" id="editLotId" />

            <label for="editTitle">Название лота:</label>
            <input type="text" id="editTitle" name="editTitle" required />

            <label for="editDescription">Описание:</label>
            <textarea id="editDescription" name="editDescription" required></textarea>

            <button type="submit" class="button">Сохранить изменения </button>
        </form>
    </div>
</div>


<h1 style="text-align: center;">Your posted lots</h1>
<div id="lotsTableContainer">
    <c:choose>
        <c:when test="${not empty userLots}">
            <table>
                <thead>
                <tr>
                    <th>ID Лота</th>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Начальная цена</th>
                    <th>Текущая цена</th>
                    <th>Время окончания</th>
                    <th colspan="2">Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lot" items="${userLots}">
                    <tr>
                        <td>${lot.lotId}</td>
                        <td>${lot.title}</td>
                        <td>${lot.description}</td>
                        <td>${lot.lotStartPrice}</td>
                        <td>${lot.lotCurrentPrice}</td>
                        <td>${lot.lotEndTime}</td>

                        <td>
                            <form action="/controller" method="post" style="display:inline;" onsubmit="return confirm('Вы точно хотите удалить лот?');">
                                <input type="hidden" name="command" value="REMOVELOT">
                                <input type="hidden" name="lotId" value="${lot.lotId}">
                                <input type="hidden" name="redirectCommand" value="SHOWUSERPOSTEDLOTS">
                                <button type="submit" class="button">Снять с продажи</button>
                            </form>
                        </td>

                        <td>
                            <form action="/controller" method="post" style="display:inline;" onsubmit="return confirm('Вы точно хотите изменить лот?');">
                                <!-- Используем скрытое поле для передачи ID лота -->
                                <input type="hidden" name="command" value="EDITLOT">
                                <input type="hidden" name="lotId" value="${lot.lotId}">
                                <!-- Кнопка отправки формы -->
                                <button type="button" class="button" onclick="showEditLotForm('${lot.lotId}', '${lot.title}', '${lot.description}')">
                                    Изменить информацию
                                </button>
                            </form>
                        </td>

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
