<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty tour}"><jsp:useBean id="tour" class="by.vgulab.epam.domain.Tour"/></c:if>
<c:choose>
    <c:when test="${not empty tour.id}"><c:set var="title" value="Редактирование данных пользователя"/></c:when>
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
            <c:if test="${not empty tour.id}">
            <input name="id" value="${tour.id}" type="hidden">
            </c:if>
            <label for="type">Тип:</label>
            <select id="type" name="type">
                <c:forEach var="type" items="${types}">
                    <c:choose>
                        <c:when test="${type == tour.type}"><c:set var="selected" value="selected"/></c:when>
                        <c:otherwise><c:remove var="selected"/></c:otherwise>
                    </c:choose>
                    <option value="${type.id}" ${selected}>${type.name}</option>
                </c:forEach>
            </select>
            <label for="country">Страна:</label>
            <input id="country" name="country" value="${tour.country}">
            <label for="town">Город:</label>
            <input id="town" name="town" value="${tour.town}">
            <label for="date">Дата заезда:</label>
            <input id="date" name="date" value="${tour.date}">
            <label for="day">Дней:</label>
            <input id="day" name="day" value="${tour.day}">
            <label for="food">Питание:</label>
            <select id="food" name="food">
                <c:forEach var="food" items="${foods}">
                    <c:choose>
                        <c:when test="${food == tour.food}"><c:set var="selected" value="selected"/></c:when>
                        <c:otherwise><c:remove var="selected"/></c:otherwise>
                    </c:choose>
                    <option value="${food.id}" ${selected}>${food.name}</option>
                </c:forEach>
            </select>
            <label for="price">Цена:</label>
            <input id="price" name="price" value="${tour.price}">
            <button class="save">Сохранить</button>
            <c:if test="${not empty tour.id}">
                <c:if test="${not tourCanBeDeleted}"><c:set var="disabled" value="disabled"/></c:if>
            <button class="delete" formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
            <button class="password-reset" formaction="password/reset.html" formmethod="post">Сбросить пароль</button>
            </c:if>
            <a class="back" href="list.html">Отменить</a>
        </form>
    </body>
</html>