<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pyvov
  Date: 13.09.2016
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Please, enter the following contact info:</h1>
<form class="form-inline" role="form" action="/sign_up" method="post">
    <input type="text" class="form-control" name="username" placeholder="Username or email"><br/>
    <input type="text" class="form-control" name="password1" placeholder="Password"><br/>
    <input type="text" class="form-control" name="password2" placeholder="Enter your password again"><br/><c:out value="${param}"/>
    <input type="text" class="form-control" name="email" placeholder="Email"><br/>
    <input type="text" class="form-control" name="phone" placeholder="Phone number"><br/>
    <input type="submit" class="btn btn-default" value="Submit">
</form>
</body>
</html>
