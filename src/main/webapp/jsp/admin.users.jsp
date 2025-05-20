<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Все пользователи</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">

    <script>
        function showEditUserForm(userId, userLogin, userPassword, userRoleId) {
            document.getElementById("editUserFormModal").style.display = "flex";

            document.getElementById("userId").value = userId; // Установить ID пользователя
            document.getElementById("editUserLogin").value = userLogin;
            document.getElementById("editUserPassword").value = userPassword;
            document.getElementById("editUserRoleId").value = userRoleId;
        }

        function closeModal() {
            document.getElementById("editUserFormModal").style.display = "none";
        }
    </script>

    <style>
        .back-button {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px; /* Отступ между текстом и иконкой */
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 8px;
            padding: 10px 20px;
            font-size: 16px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .back-button i {
            font-size: 18px; /* Размер стрелки */
        }

        /* При наведении мыши */
        .back-button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
        .back-button:active {
            background-color: #004085;
        }
    </style>

</head>
<body>

<a class="back-button" href="${pageContext.request.contextPath}/jsp/admin.jsp">
    <i class="fas fa-arrow-left"></i> Назад
</a>

<div id="editUserFormModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Редактировать пользователя</h2>
        <form action="/controller" method="post" onsubmit="closeModal();">

            <input type="hidden" name="command" value="EDITUSER" />
            <input type="hidden" name="userId" id="userId" />

            <label for="editUserLogin">Логин:</label>
            <input type="text" id="editUserLogin" name="editUserLogin" required />

            <label for="editUserPassword">Пароль:</label>
            <input type="text" id="editUserPassword" name="editUserPassword" required />

            <label for="editUserRoleId">Роль:</label>
            <select id="editUserRoleId" name="editUserRoleId" required>
                <option value="1">1 (Админ)</option>
                <option value="2">2 (Модератор)</option>
                <option value="3">3 (Пользователь)</option>
            </select>

            <button type="submit" class="button">Сохранить изменения </button>
        </form>
    </div>
</div>

<c:if test="${not empty sessionScope.errorMessage}">
    <script>
        alert("${sessionScope.errorMessage}");
    </script>
    <c:remove var="errorMessage" scope="session"/>
</c:if>

<!-- Таблица для отображения всех пользователей -->
<div id="usersTableContainer">
    <c:choose>
        <c:when test="${not empty allUsers}">
            <table>
                <thead>
                <tr>
                    <th>Логин</th>
                    <th>Пароль</th>
                    <th>Онлайн-статус</th>
                    <th>Роль</th>
                    <th colspan="2">Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${allUsers}">
                    <tr>
                        <td>${user.userLogin}</td>
                        <td>${user.userPassword}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.userAuthorizedStatus}">Авторизован</c:when>
                                <c:otherwise>Не авторизован</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${user.userRoleId == 1}">Админ</c:when>
                                <c:when test="${user.userRoleId == 2}">Модератор</c:when>
                                <c:when test="${user.userRoleId == 3}">Пользователь</c:when>
                                <c:otherwise>Неизвестная роль</c:otherwise>
                            </c:choose>
                        </td>


                        <td>
                            <form action="/controller" method="post" style="display:inline;" onsubmit="return confirm('Вы точно хотите изменить лот?');">
                                <input type="hidden" name="command" value="EDITUSER">
                                <input type="hidden" name="userId" value="${user.userId}">
                                <button type="button" class="button" onclick="showEditUserForm('${user.userId}','${user.userLogin}', '${user.userPassword}', '${user.userRoleId}')">
                                    Изменить информацию
                                </button>
                            </form>
                        </td>

                        <td>
                            <form action="/controller" method="post" style="display:inline;">
                                <input type="hidden" name="command" value="DELETEUSER" />
                                <input type="hidden" name="userId" value="${user.userId}" />
                                <button type="submit" class="button"
                                        onclick="return confirm('Вы действительно хотите удалить пользователя? Это действие необратимо.')">
                                    Удалить пользователя
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p style="text-align: center;">Нет данных для отображения.</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
