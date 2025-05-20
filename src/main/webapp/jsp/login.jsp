<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Auction Avenue & Co. welcomes you!</title>

    <style>
        body {
            background-color: #2C2C2C;
            color: white;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #444444;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 400px;
            font-size: 1.2em;
            margin: 0;
            margin-left: 33%;
            margin-right: 33%;
        }

        .form-container label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        .form-container input[type="text"], .form-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin-top: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: none;
            font-size: 1.2em;
        }

        .form-container input[type="submit"] {
            width: 100%;
            padding: 15px;
            background-color: #6A6A6A;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.2em;
        }

        .form-container input[type="submit"]:hover {
            background-color: #5A5A5A;
        }

        .form-container hr {
            border: 1px solid #5A5A5A;
        }

        .message-container {
            margin-bottom: 20px;
            text-align: center;
            color: #FF0000;
        }
    </style>
</head>
<body>

<div class="form-container">
    <form name="LoginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="Login" />

        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" value=""/>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" value=""/>

        <!-- Error messages will be displayed here -->
        <div class="message-container">
            ${errorLoginPass}
            ${errorBlock}
            ${wrongAction}
            ${nullPage}
        </div>

        <input type="submit" value="Войти"/>
    </form>
    <hr/>
</div>

</body>
</html>
