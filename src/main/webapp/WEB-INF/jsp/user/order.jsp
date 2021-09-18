<%--
  Created by IntelliJ IDEA.
  User: sergeypodkolzin
  Date: 9/16/21
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:useBean id="order" class="by.vgulab.epam.domain.Order"/>
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
<form action=" " method="post">

    <label for="order.userId">Пользователь id:</label><br>
    <input type="text" name="order.userId" id="order.userId">
    <button type="submit"> Сохранить </button><br>
</form>
</body>
</html>
