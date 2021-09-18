<%--
  Created by IntelliJ IDEA.
  User: sergeypodkolzin
  Date: 9/19/21
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>добро пожаловать в Грузинскую мечту</title>
    <link href="../../main.css" rel="stylesheet">
</head>
<body>


<h>Добро пожаловать в Грузинскую мечту</h><br>

<br>
<br>
<br>

<c:url var="orderUser" value="/user/order.html"/>
<p><a href="${orderUser}">Войти</a></p><br>

<br>
<br>
<br>
<c:url var="addUser" value="/user/edit.html"/>
<p><a href="${addUser}">Создать</a></p><br>

</body>
</html>
