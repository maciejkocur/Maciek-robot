<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html>
 <html>
 <head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-8">
    <title>Robot</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
      $('dropdown-toggle').dropdown()
        });
    </script>
 </head>

 <body>
  <%@ include file="index.jsp" %>

    <div class="tableContainer">
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
                    <td>${bookInfo.getTitle()}</td>
                    <td>${bookInfo.getAuthor()}</td>
                    <td>${bookInfo.getDescription()}</td>
                    <td>${bookInfo.getPrice()}</td>
                    <td>${bookInfo.getUrl()}</td>
                    <td>Some tag</td>
                 </tr>
              </c:forEach>
           </tbody>
        </table>
    </div>
   </body>
 </html>