<%--
  Created by IntelliJ IDEA.
  User: THINKPAD
  Date: 3/22/2024
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form href="/user" method="post">
    <table class="table table-striped table-inverse table-responsive">
        <thead class="thead-inverse">
        <tr>
            <th>User Name</th>
            <td>
                <input type="text" name="name" id="name">
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>User Email</th>
            <td>
                <input type="text" name="email" id="email">
            </td>
        </tr>
        <tr>
            <th>User Country</th>
            <td>
                <input type="text" name="country" id="country">
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Create">
</form>
</body>
</html>
