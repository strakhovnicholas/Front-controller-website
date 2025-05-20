<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome, dear ${user}</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
    <script>
        function showCreateLotForm() {
            var formContainer = document.getElementById('createLotForm');
            var myLotsButton = document.getElementById('myLotsButton');

            if (formContainer.style.display === 'block') {
                formContainer.style.display = 'none';
                myLotsButton.style.display = 'block';
                resetFormFields();
            } else {
                formContainer.style.display = 'block';
                myLotsButton.style.display = 'none';
                // Подтверждаем очистку полей перед открытием новой формы
                resetFormFields();
            }
        }

        function resetFormFields() {
            document.getElementById("title").value = "";
            document.getElementById("description").value = "";
            document.getElementById("lotStartPrice").value = "";
        }
    </script>

    <style>
        #formAndLotsContainer {
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
        </style>
</head>
<body>


<div id="formAndLotsContainer">
    <div class="form-container" id="createLotForm" style="display: none;">
        <form action="/controller" method="post">
            <input type="hidden" name="command" value="ADDLOT" />
            <label for="title">Название лота:</label>
            <input type="text" id="title" name="title" required="" />
            <label for="description">Описание:</label>
            <textarea id="description" name="description" required=""></textarea>
            <label for="lotStartPrice">Начальная цена:</label>
            <input type="number" id="lotStartPrice" name="lotStartPrice" step="100" required="" />

            <button class="button" type="submit">Выставить лот</button>
        </form>
    </div>
</div>

<div class="vertical-button-panel">
    <button class="button" type="button" onclick="showCreateLotForm()">Создать новый лот</button>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="SHOWLOTSFORPURCHASE" />
        <button type="submit" class="button">Приобрести новый лот</button>
    </form>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="SHOWUSERPOSTEDLOTS" />
        <button type="submit" class="button">Выложенные лоты</button>
    </form>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="SHOWPURCHASEDLOTSUSER" />
        <button type="submit" class="button">Купленные лоты</button>
    </form>
</div>

</body>
</html>
