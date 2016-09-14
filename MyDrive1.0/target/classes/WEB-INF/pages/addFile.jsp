<%--
  Created by IntelliJ IDEA.
  User: pyvov
  Date: 14.09.2016
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddFile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<form class="form-inline" role="form" enctype="multipart/form-data" action="/add_file" method="post">
    <div class="form-group"><input type="text" class="form-control" name="description" placeholder="Type description (optional)"></div>
    <div class="form-group">File: <input type="file" class="form-control" name="file"></div>
    <input type="hidden" name="username" value="${username}">
    <input type="submit" class="btn btn-default" value="Submit">
</form>
</body>
</html>
