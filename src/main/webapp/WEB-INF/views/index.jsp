<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html>
 <html>
 <head>
 	<title>Robot</title>
 	<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />' type='text/css' media='all' />
 </head>
 <body>
 	<h2>Hello World, Spring MVC</h2>

 	<c:forEach var="bookInfo" items="${books}">

    				<li>${bookInfo}</li>

    </c:forEach>
 </body>
 </html>