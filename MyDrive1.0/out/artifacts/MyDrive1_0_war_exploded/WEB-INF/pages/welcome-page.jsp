<%--
  Created by IntelliJ IDEA.
  User: pyvov
  Date: 13.09.2016
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to MyDrive</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Welcome to MyDrive! Sign in if you`re already is our client</h1>
    <form class="form-inline" role="form" action="/sign_in" method="post">
        <input type="text" class="form-control" name="username" placeholder="Username or email"><br/>
        <input type="text" class="form-control" name="password" placeholder="Password"><br/>
        <input type="submit" class="btn btn-default" value="Sign in">
    </form>
    <h1>... Or sign up if  you`re not :D</h1>
    <form class="form-inline" role="form" action="/sign_up_page" method="post">
        <input type="submit" class="btn btn-default" value="Sign up">
    </form>
</div>
</body>
</html>
