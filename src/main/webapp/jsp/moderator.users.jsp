<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Пользователи для модератора</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
  <script>
    function toggleBlockUser(userId, currentStatus) {
      const action = currentStatus ===1?'разблокировать' : 'заблокировать';
      if (confirm(`Вы уверены, что хотите заблокировать/разблокировать пользователя?`)) {
        console.log('Submitting form...');
        return true; // Разрешить отправку формы
      } else {
        console.log('Submission canceled.');
        return false; // Блокировать отправку формы
      }
    }
  </script>
</head>
<body>
<div id="usersTableContainer">
  <c:choose>
    <c:when test="${not empty allUsers}">
      <table class="data-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Логин</th>
          <th>Статус блокировки</th>
          <th>Действие</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${allUsers}">
          <tr>
            <td>${user.userId}</td>
            <td>${user.userLogin}</td>
            <td>
              <c:choose>
                <c:when test="${user.userBlockedStatus}">
                  Заблокирован
                </c:when>
                <c:otherwise>
                  Активен
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <form id="blockUserForm-${user.userId}" action="/controller" method="post" style="display:inline;" onsubmit="return toggleBlockUser('${user.userId}', '${user.userBlockedStatus}')">
                <input type="hidden" name="command" value="BLOCKUSER" />
                <input type="hidden" name="userId" value="${user.userId}" />
                <input type="hidden" name="currentStatus" value="${user.userBlockedStatus}" />
                <button type="submit" class="button">
                  <c:choose>
                    <c:when test="${user.userBlockedStatus}">Разблокировать</c:when>
                    <c:otherwise>Заблокировать</c:otherwise>
                  </c:choose>
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
