<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>


        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

        <form id="addFile" method="POST" enctype="multipart/form-data" action="${contextPath}/add_file?${_csrf.parameterName}=${_csrf.token}">
            <div class="form-group input-group">
                <label class="input-group-btn">
                    <span class="btn btn-primary">
                        Browse&hellip; <input type="file" name="body" style="display: none;width:100px;" multiple>
                    </span>
                </label>
                <input type="text" class="form-control" readonly style="width: 100px;">
            </div>
            <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block" style="width: 100px" type="submit">Add File</button>
            </div>
        </form>

        <table class="table">
            <% int i=0;%>
            <c:forEach items="${files}" var="file">
                <tr>
                    <td><%=  ++i%>. </td>
                    <td><img src="${contextPath}/resources/images/file.png" width="30" height="30"></td>
                    <td>${file.name}</td>
                    <td><a href="delete?id=<%= i%>">Delete</a></td>
                    <td><a href="download?id=<%= i%>">Download</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>