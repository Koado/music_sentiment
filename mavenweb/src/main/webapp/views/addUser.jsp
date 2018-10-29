<%--
  Created by IntelliJ IDEA.
  User: kkoado
  Date: 2018/9/20
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addUser</title>
    <link rel="stylesheet" type="text/css" href="../statics/css/index.css">
</head>
<body>
    <h2 style="color: aqua">Adding User</h2>
    <form action="/zkf/home/saveUser" method="post">
        <tr>
            <td>Id:</td>
            <td><input name="id"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input name="name"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="add"></td>
        </tr>
    </form>
</body>
</html>
