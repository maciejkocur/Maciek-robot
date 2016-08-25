<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html>
 <html>
 <head>
   <meta charset="UTF-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">

 	<title>Robot</title>
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 </head>
 <body>
 	<h2>Book robot</h2>

    <div class="tableContainer">
        <div class="container">
            <div class="masthead buffer">
                <h3 class="text-muted">Books from Empik</h3>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Library</th>
                    <th>Tags</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bookInfo" items="${books}">
                    <tr>
                        <td>${bookInfo}</td>
                        <td>Someone</td>
                        <td>Something</td>
                        <td>....$</td>
                        <td>http://www.empik.com/ebooki</td>
                        <td>Some tag</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

 </body>
 </html>