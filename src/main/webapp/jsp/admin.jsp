<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Панель администратора</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        /* Уменьшаем шрифт для кнопок в панели администратора */
        .button-container .button {
            font-size: 10px; /* Уменьшаем шрифт до 10px */
            padding: 8px 12px; /* Уменьшаем отступы */
        }

        /* Уменьшаем шрифт для кнопок при наведении */
        .button-container .button:hover {
            font-size: 10px; /* Поддерживаем уменьшенный шрифт при наведении */
        }

        #addUserForm {
            margin: 40px auto; /* Центрируем контейнер */
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 500px; /* Ограничиваем максимальную ширину */
            width: 100%; /* Контейнер адаптируется к ширине экрана */
        }

        .form-container {
            margin-bottom: 20px;
        }

        .form-container form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .form-container label {
            font-weight: bold;
            color: #333;
        }

        .form-container input[type='text'],
        .form-container input[type='number'],
        .form-container textarea,
        .form-container input[type='datetime-local'] {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
            box-sizing: border-box;
        }

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

    <script>
        // Функция для отображения модального окна
        function showAddUserModal() {

            var formContainer = document.getElementById('addUserForm');

            if (formContainer.style.display === 'block') {
                formContainer.style.display = 'none';
                resetFormFields();
            } else {
                formContainer.style.display = 'block';
                // Подтверждаем очистку полей перед открытием новой формы
                resetFormFields();
            }
        }

        // Функция для закрытия модального окна
        function closeAddUserModal() {
            document.getElementById("addUserForm").style.display = "none";
            resetFormFields();
        }

        function resetFormFields() {
            document.getElementById("loginNewUser").value = "";
            document.getElementById("passwordNewUser").value = "";
            document.getElementById("roleIdNewUser").value = "1";
        }

    </script>
</head>
<body>

<div class="vertical-button-panel">
    <a class="back-button" href="${pageContext.request.contextPath}/">
        <i class="fas fa-arrow-left"></i> Назад
    </a>
    <button class="button" type="button" onclick="showAddUserModal()">Добавить пользователя</button>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="SHOWUSERS" />
        <button type="submit" class="button">Показать всех пользователей</button>
    </form>
</div>

<div id="addUserForm" class="form-container" style="display: none;">
    <h2>Добавить пользователя</h2>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="ADDUSER" />
        <label for="loginNewUser">Логин:</label>
        <input type="text" id="loginNewUser" name="loginNewUser" required />

        <label for="passwordNewUser">Пароль:</label>
        <input type="text" id="passwordNewUser" name="passwordNewUser" required />

        <label for="roleIdNewUser">Роль:</label>
        <select id="roleIdNewUser" name="roleIdNewUser" required>
            <option value="1">Админ</option>
            <option value="2">Модератор</option>
            <option value="3">Пользователь</option>
        </select>

        <button type="submit" class="button">Добавить</button>
        <button type="button" class="button" onclick="closeAddUserModal()">Отменить</button>
    </form>
</div>

</body>
</html>
