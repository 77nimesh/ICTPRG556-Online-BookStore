<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thank You</title>
        <c:url var="styleUrl" value="/css/style.css" />
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <h2>Online Bookstore</h2>
        <hr>
        <h3>Thank you for shopping with us.</h3>

        <c:if test="${not empty requestScope.result}">
            <table>
                <tr>
                    <td><c:out value="${requestScope.result}" /></td>
                </tr>
            </table>
        </c:if>

        <c:remove var="cart" scope="session" />

        <jsp:include page="footer.jsp" />
    </body>
</html>
