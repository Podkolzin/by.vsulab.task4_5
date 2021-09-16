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
                <td class="content">${order.price}</td>
                <td class="content">${order.tour.type.name}</td>
                <td class="content">${order.tour.country}</td>
                <td class="content">${order.tour.town}</td>
                <td class="content">${order.tour.date}</td>
                <td class="content">${order.tour.day}</td>
                <td class="content">${order.tour.food.name}</td>



            </tr>
            </c:forEach>
        </table>
        <a href="edit.html" class="add-button">Добавить</a>
    </body>
</html>