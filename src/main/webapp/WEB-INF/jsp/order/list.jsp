<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Список заказов</title>
        <link href="../main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Турфирма "Грузинская мечта!"</h1>

        <c:if test="${not empty order}"><c:set var="title" value="Ваши текущие заказы"/>
            <label for="name"> Уважаймый клиент:</label><br>
            <input type="text" name="name" id="name" value="${name}">
            <h2>Список заказов</h2>

            <table>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Цена</th>
                <th>Тип путевки</th>
                <th>Страна</th>
                <th>Город</th>
                <th>Дата заезда</th>
                <th>Кол-во дней</th>
                <th>Питание</th>
                <td>&nbsp;</td>

            </tr>
            <c:forEach var="order" items="${order}">
            <tr>
                <td class="content">${order.user.name}</td>
                <td class="content">${order.user.surname}</td>
                <td class="content">${order.payment}</td>
                <td class="content">${order.tour.type.name}</td>
                <td class="content">${order.tour.country}</td>
                <td class="content">${order.tour.town}</td>
                <td class="content">${order.tour.date}</td>
                <td class="content">${order.tour.day}</td>
                <td class="content">${order.tour.food.name}</td>
            </tr>
            </c:forEach>
        </table>
            <a href="/tour/list.html" class="add-button">ЗАКАЗАТЬ</a>
        </c:if>

        <c:if test="${empty order}"><c:set var="title" value="У Вас еще нет заказов"/>
            <label for="name"> Уважаймый клиент:</label><br>
            <input type="text" name="name" id="name" value="${name}">
            <h2>У Вас еще нет заказов</h2>
        <a href="/tour/list.html" class="add-button">ЗАКАЗАТЬ</a>
        </c:if>

    </body>
</html>