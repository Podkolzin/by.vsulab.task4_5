<%--
  Created by IntelliJ IDEA.
  User: sergeypodkolzin
  Date: 9/16/21
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" class="by.vgulab.epam.domain.User"/>
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

<c:url var="signinUrl" value="/login.html"/>
<form action="${signinUrl}" method="post">

    <label for="login">Логин пользователя:</label><br>
    <input type="text" name="login" id="login">

    <label for="password">Пароль:</label><br>
    <input type="text" name="password" id="password">
    <button type="submit"> Сохранить</button>
    <br>
</form>
</body>
</html>
