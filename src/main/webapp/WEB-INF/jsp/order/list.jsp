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
                <th>user_id</th>
                <th>tour_id</th>
                <th>price</th>

                <td>&nbsp;</td>

            </tr>
            <c:forEach var="order" items="${order}">
            <tr>
                <td class="content">${order.userId}</td>
                <td class="content">${order.tourId}</td>
                <td class="content">${order.price}</td>

            </tr>
            </c:forEach>
        </table>
        <a href="edit.html" class="add-button">Добавить</a>
    </body>
</html>