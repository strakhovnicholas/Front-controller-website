<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Сеанс истек</title>
    <style>
        /* Стили для затемненного фона */
        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        /* Стили для модального окна */
        .modal-content {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 400px;
            width: 90%;
        }

        .modal-content h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .modal-content p {
            margin-bottom: 30px;
            color: #666;
        }

        .modal-content a {
            display: inline-block;
            padding: 10px 20px;
            background: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .modal-content a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="modal-overlay">
    <div class="modal-content">
        <h2>Сеанс истек</h2>
        <p>Ваш аккаунт заблокирован, свяжитесь с администрацией сайта</p>
        <a href="${pageContext.request.contextPath}/">Вернуться на страницу входа</a>
    </div>
</div>
</body>
</html>