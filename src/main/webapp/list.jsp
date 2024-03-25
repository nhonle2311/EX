<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: THINKPAD
  Date: 3/22/2024
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<h1>User Management Application</h1>
<tr>
    <td>
        <a href="/user?action=create">
            thêm
        </a>
    </td>
</tr>
<form href="/user" method="post">
    <table class="table table-striped table-inverse table-responsive" border="1px">
        <thead class="thead-inverse">
        <tr>
            <td>id</td>
            <td>name</td>
            <td>mail</td>
            <td>address</td>
            <td>edit</td>
            <td>delete</td>

        </tr>
        </thead>
        <tbody>

        <c:forEach items="${userList}" var="userList">
            <tr>
                <td>${userList.id}</td>
                <td>${userList.name}</td>
                <td>${userList.email}</td>
                <td>${userList.country}</td>
                <td>
                    <a href="/user?action=edit&id=${userList.id}">edit</a>
                </td>
                <td>
                    <a href="/user?action=delete&id=${userList.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
