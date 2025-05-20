<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Управление лотами</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">
</head>
<body>
<div id="lotsTableContainer">


  <c:if test="${not empty activeLots}">
    <div style="text-align: center; margin-bottom: 20px;">
      <button type="button" class="button" onclick="showSetEndDateModal()">Установить срок продажи для всех лотов</button>
    </div>
  </c:if>

  <!-- Существующий код таблицы -->
  <c:choose>
    <c:when test="${not empty activeLots}">
      <table class="data-table">
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
        <c:forEach var="lot" items="${activeLots}">
          <tr>
            <td>${lot.lotId}</td>
            <td>${lot.title}</td>
            <td>${lot.description}</td>
            <td>${lot.lotStartPrice}</td>
            <td>${lot.lotCurrentPrice}</td>
            <td>${lot.lotEndTime}</td>
            <td>
              <form action="/controller" method="post" style="display:inline;" onsubmit="return confirm('Вы точно хотите удалить лот?');">
                <input type="hidden" name="command" value="REMOVELOTMODER">
                <input type="hidden" name="lotId" value="${lot.lotId}">
                <input type="hidden" name="redirectCommand" value="SHOWLOTSMODER">
                <button type="submit" class="button">Снять с продажи</button>
              </form>
            </td>

            <td>
              <button type="button" class="button" onclick="showSetEndDateModalLot('${lot.lotId}', '${lot.lotEndTime}')">
                Изменить время окончания лота
              </button>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <p style="text-align: center;">Пока лотов нет, может быть, появятся немного позже :)</p>
    </c:otherwise>
  </c:choose>
</div>

<!-- Модальное окно для установки даты -->
<div id="modalOverlay" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.5); z-index:9999;"></div>

<!-- Модальное окно -->
<div id="setEndDateModal" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background:white; border:1px solid #ccc; padding:20px; box-shadow:0px 4px 6px rgba(0,0,0,0.1); z-index:10000;">
  <h2>Установить срок продажи</h2>
  <form action="/controller" method="post">
    <input type="hidden" name="command" value="SETENDDATEFORALLLOTS">
    <label for="endDate">Выберите дату окончания:</label>
    <input type="date" id="endDate" name="endDate" required>
    <br><br>
    <button type="submit" class="button">Установить</button>
    <button type="button" class="button" onclick="closeSetEndDateModal()">Отмена</button>
  </form>
</div>

<div id="setEndDateModal2" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background:white; border:1px solid #ccc; padding:20px; box-shadow:0px 4px 6px rgba(0,0,0,0.1); z-index:10000;">
  <h2>Установить срок продажи конкретного лота</h2>
  <form action="/controller" method="post">
    <input type="hidden" name="command" value="SETENDDATEFORCERTAINLOT">
    <input type="hidden" id="lotId" name="lotId">
    <label for="endDateLot">Выберите дату окончания:</label>
    <input type="date" id="endDateLot" name="endDateLot" required>
    <br><br>
    <button type="submit" class="button">Установить</button>
    <button type="button" class="button" onclick="closeSetEndDateModal()">Отмена</button>
  </form>
</div>

<script>
  // Показываем модальное окно и фон-затемнение

  function showSetEndDateModalLot(lotId = null, lotEndTime = null) {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('setEndDateModal2').style.display = 'block';

    if (lotId) {
      document.getElementById('lotId').value = lotId;  // Устанавливаем ID лота
      document.getElementById('endDateLot').value = lotEndTime;  // Устанавливаем дату окончания
    }
  }

  function showSetEndDateModal() {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('setEndDateModal').style.display = 'block';
  }

  // Закрываем модальное окно и фон-затемнение
  function closeSetEndDateModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('setEndDateModal').style.display = 'none';
    document.getElementById('setEndDateModalLot').style.display = 'none';
  }

  // Опционально: Закрытие окна при клике на фон-затемнение
  document.getElementById('modalOverlay').addEventListener('click', closeSetEndDateModal);
</script>
</body>
</html>
