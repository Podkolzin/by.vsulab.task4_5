<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<form action=" " method="post">

    <c:if test="${not empty tour.id}">
        <input name="id" value="${tour.id}" type="hidden">
    </c:if>

    <label for="name"> Уважаймый клиент:</label><br>
    <input type="text" name="name" id="name" value="${name}">

    <label for="tour.tourId">Номер тура:</label><br>
    <input type="text" name="tour.tourId" id="tour.tourId" value="${tour.id}">

    <label for="tour.price">ЦЕНА id:</label><br>
    <input type="text" name="tour.price" id="tour.price" value="${tour.price}">

    <button type="submit"> Сохранить</button>
    <br>

</form>

</body>
</html>
