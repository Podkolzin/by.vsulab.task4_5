<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}"><jsp:useBean id="user" class="by.vgulab.epam.domain.User"/></c:if>
<c:choose>
    <c:when test="${not empty user.id}"><c:set var="title" value="Редактирование данных пользователя"/></c:when>
    <c:otherwise><c:set var="title" value="Добавление нового пользователя"/></c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link href="../main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Турфирма лучшего отдыха</h1>
        <h2>${title}</h2>
        <form action="save.html" method="post">
            <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
            </c:if>
            <label for="login">Логин:</label>
            <input id="login" name="login" value="${user.login}">
            <label for="password">Пароль:</label>
            <input id="password" name="password" value="${user.password}">
            <label for="name">name:</label>
            <input id="name" name="name" value="${user.name}">
            <label for="surname">surname:</label>
            <input id="surname" name="surname" value="${user.surname}">
            <label for="email">email:</label>
            <input id="email" name="email" value="${user.email}">

            <label for="role">Роль:</label>
            <select id="role" name="role">
                <c:forEach var="role" items="${roles}">
                    <c:choose>
                        <c:when test="${role == user.role}"><c:set var="selected" value="selected"/></c:when>
                        <c:otherwise><c:remove var="selected"/></c:otherwise>
                    </c:choose>
                    <option value="${role.id}" ${selected}>${role.name}</option>
                </c:forEach>
            </select>
            <button class="save">Сохранить</button>
            <c:if test="${not empty user.id}">
                <c:if test="${not userCanBeDeleted}"><c:set var="disabled" value="disabled"/></c:if>
            <button class="delete" formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
            <button class="password-reset" formaction="password/reset.html" formmethod="post">Сбросить пароль</button>
            </c:if>
            <a class="back" href="list.html">Отменить</a>
        </form>
    </body>
</html>