<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Панель модератора</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
</head>
<body>

<!-- Вертикальная панель модератора -->
<div class="vertical-button-panel">
  <form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="SHOWLOTSMODER" />
    <button type="submit" class="button">Показать все лоты</button>
  </form>

  <form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="SHOWUSERSMODER" />
    <button type="submit" class="button">Показать всех пользователей</button>
  </form>

  <form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="SHOWUSERREQUEST" />
    <button type="submit" class="button">Запросы пользователей</button>
  </form>

  <form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="SHOWEXPIREDLOTS" />
    <button type="submit" class="button">Истекшие ставки</button>
  </form>

</div>

</body>
</html>
