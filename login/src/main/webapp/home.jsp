<%--
  Created by IntelliJ IDEA.
  User: zamok
  Date: 03.11.2020
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home Page</title>
    <style>
        h1{
            color:red;
        }
    </style>
</head>
<body>
<form action="home" method="post">
   Enter Username: <input type="text" name="username"><br>
   Enter Password: <input type="password" name="password"><br>
    <input type="submit"><br>
</form>

<h1>${param.message}</h1>
</body>
</html>
