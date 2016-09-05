<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="index.jsp"/>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/search.css"/>">
</head>
<body>
<div class="container-fluid">
    <div class="masthead buffer">

        <h3 class="text-muted">Books from ${books.iterator().next().getLibrary()}</h3>
    </div>
    <div class="table-responsive">
        <div class="form-group pull-right">
            <input type="text" class="search form-control" placeholder="What you looking for?">
        </div>
        <span class="counter pull-right"></span>
        <table class="table table-striped table-hover results">
            <thead>
            <tr>
                <th>
                    <a>Title<b class="caret"></b></a>
                </th>
                <th>
                    <a>Author<b class="caret"></b></a>
                </th>
                <th>
                    <a>Price<b class="caret"></b></a>
                </th>
                <th>
                    Reference to book
                </th>
                <th>
                    <a>Category<b class="caret"></b></a>
                </th>
                <th>
                    Description
                </th>
            </tr>
            <tr class="warning no-result">
                <td colspan="4"><i class="fa fa-warning"></i> No result</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="books" type="java.util.List"--%>
            <c:forEach var="bookInfo" items="${books}">
                <tr>
                    <td>${bookInfo.getTitle()}</td>
                    <td>${bookInfo.getAuthor()}</td>
                    <td>${bookInfo.getPrice()}</td>
                    <td><a href='${bookInfo.getUrl()}'>More...</a></td>
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