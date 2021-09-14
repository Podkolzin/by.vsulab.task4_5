<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Список туров</title>
        <link href="../main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Турфирма "Грузинская мечта!"</h1>
        <h2>Список туров</h2>
        <table>
            <tr>
                <th>Тип</th>
                <th>Страна</th>
                <th>Город</th>
                <th>Дата</th>
                <th>Дней</th>
                <th>Питание</th>
                <th>Цена</th>

                <td>&nbsp;</td>
            </tr>
            <c:forEach var="tour" items="${tour}">
            <tr>
                <td class="content">${tour.type.name}</td>
                <td class="content">${tour.country}</td>
                <td class="content">${tour.town}</td>
                <td class="content">${tour.date}</td>
                <td class="content">${tour.day}</td>
                <td class="content">${tour.food.name}</td>
                <td class="content">${tour.price}</td>
                <td class="empty"><a href="edit.html?id=${tour.id}" class="edit"></a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="edit.html" class="add-button">Добавить</a>
    </body>
</html>