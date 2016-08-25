<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Robot</title>
	<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />' type='text/css' media='all' />


        <!-- Custom styles for this template -->
        <link href='<c:url value="/resources/css/justified-nav.css" rel="stylesheet">
    </head>
</head>
<body>
	<h2>Hello World, Spring MVC</h2>

	<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">

    <div class="tableContainer">
        <div class="container">
            <div class="masthead buffer">
                <h3 class="text-muted">Design projects</h3>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Picture</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="bookInfo" items="${title}">
                    <tr>
                        <td>${bookInfo.getTitle()}</td>
                        <td><"noting yet"></td>
                        <td><"noting yet"></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

    </div>
</body>
</html>