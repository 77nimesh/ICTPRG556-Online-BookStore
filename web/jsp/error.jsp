<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <c:url var="styleUrl" value="/css/style.css" />
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <c:choose>
            <c:when test="${not empty requestScope.result}">
                <h3><c:out value="${requestScope.result}" /></h3>
            </c:when>
            <c:otherwise>
                <h3>An error occurred while processing your request.</h3>
            </c:otherwise>
        </c:choose>

        <c:remove var="cart" scope="session" />
        <c:remove var="books" scope="session" />

        <jsp:include page="footer.jsp" />
    </body>
</html>
