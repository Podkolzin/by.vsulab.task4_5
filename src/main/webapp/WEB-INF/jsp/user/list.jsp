<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Список пользователей</title>
        <link href="../main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Турфирма "Грузинская мечта!"</h1>
        <h2>Список пользователей</h2>
        <table>
            <tr>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Почта</th>
                <th>Роль</th>
                <td>&nbsp;</td>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td class="content">${user.login}</td>
                <td class="content">${user.password}</td>
                <td class="content">${user.name}</td>
                <td class="content">${user.surname}</td>
                <td class="content">${user.email}</td>
                <td class="content">${user.role.name}</td>
                <td class="empty"><a href="edit.html?id=${user.id}" class="edit"></a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="edit.html" class="add-button">Добавить</a>
    </body>
</html>