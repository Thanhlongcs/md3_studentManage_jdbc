<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: THINKPAD
  Date: 9/28/2022
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back menu</a>
<c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
</c:if>
<h1>form create student</h1>
<form action="" method="post">
    <label>name</label>
    <input type="text" name="name"><br>
    <label>age</label>
    <input type="text" name="age"><br>
    <button>create</button>
</form>
</body>
</html>
