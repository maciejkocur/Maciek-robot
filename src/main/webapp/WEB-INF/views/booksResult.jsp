<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Robot</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">Library to search books<b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="../fetch/Empik">Empik</a></li>
                        <li><a href="../fetch/Empik">Another site</a></li>
                        <li><a href="#">Yet another site</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Most popular site</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form action="login" method="post" class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" name="login" placeholder="login" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                    <a href="../../registration.html" class="btn btn-success">Sign up</a>
                </form>
            </ul>
        </div>
    </div><!-- /.navbar-collapse -->
</nav>
<div class="container-fluid">
    <div class="masthead buffer">
        <h3 class="text-muted">Books from Empik</h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>
                    <a> Title<b class="caret"></b></a>
                </th>
                <th>
                    <a> Author<b class="caret"></b></a>
                </th>
                <th>
                    <a> Price<b class="caret"></b></a>
                </th>
                <th>
                    <a>Reference to book</a>
                </th>
                <th>
                    <a>Category<b class="caret"></b></a>
                </th>
                <th>
                    <a>Description</a>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bookInfo" items="${books}">
                <tr>
                    <td>${bookInfo.getTitle()}</td>
                    <td>${bookInfo.getAuthor()}</td>
                    <td>${bookInfo.getPrice()}</td>
                    <td>${bookInfo.getUrl()}</td>
                    <td>Some_category</td>
                    <td>${bookInfo.getDescription()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>