<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8">
    <title>Robot</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
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
                <form class="navbar-form navbar-left">
                    <div class="dropdown">
                        <button class="btn btn-warning" type="button" id="dropdownMenu1" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="true">
                            Update information about promotions
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </div>
                </form>
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
                    <a href="../../register" class="btn btn-success">Sign up</a>
                </form>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>