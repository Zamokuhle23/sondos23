<%@ page import="java.util.List" %>
<%@ page import="com.sondos.models.User" %><%--
  Created by IntelliJ IDEA.
  User: zamok
  Date: 25.10.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <div>
        <h1 style="color: ${cookie.get("color").value}">USERS</h1>
        <form action="/users" method="post">
            <select name="color">
                <option value="red">RED</option>
                <option value="green">GREEN</option>
                <option value="blue">BLUE</option>
            </select>
            <input type="submit" value="ok">
        </form>

    </div>
    <div>
   <tr>
       <th>First Name</th>
       <th>Last Name</th>
   </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("usersForJsp");
        for(User user: users){
    %>
    <tr>
        <td><%=user.getFirstName()%></>
        <td><%=user.getLastName()%></td>
    </tr>
    <%}%>
</table>
</div>
</body>
</html>
