<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
 <head>
    <jsp:include page="index.jsp" />
    <script src="<c:url value="/resources/js/main.js" />"></script>
 </head>
 <body>
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
                <th onClick="continue">
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