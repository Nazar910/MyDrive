<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pyvov
  Date: 13.09.2016
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserAccount</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="user" value="${username}"/>
    <h1>Hello, <c:out value="${user}"/></h1>

    <form class="form-inline" role="form" action="/add_file_page" method="post">
        <input type="submit" class="btn btn-default" value="Add file"/>
        <input type="hidden" name="username" value="${user}"/>
    </form>

    <table border="1">
        <c:forEach items="${files}" var="file">
            <tr>
                <td>${file.name}</td>
                <td><a href="/delete_file?id=${file.id}&username=${user}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
