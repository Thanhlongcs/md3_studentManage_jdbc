<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: THINKPAD
  Date: 9/28/2022
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">Back menu</a>
<c:if test='${requestScope["message"]!= null}'>
    <span style="color:blue">${requestScope["message"]}</span>
</c:if>
<form method="post">
    <label>name</label><br>
    <input type="text" name="name" value="${requestScope["students"].getName()}">
    <label>age</label><br>
    <input type="text" name="age" value="${requestScope["students"].getAge()}">
    <button>Edit</button>
</form>
</body>
</html>
