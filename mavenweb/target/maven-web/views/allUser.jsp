<%--
  Created by IntelliJ IDEA.
  User: kkoado
  Date: 2018/10/10
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>

    <h2>The chosen one</h2>
    <table>
        <tr>
            <td>${chosenOne.name}</td>
            <td>${chosenOne.password}</td>
        </tr>
    </table>
</body>
</html>
