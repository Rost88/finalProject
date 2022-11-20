<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${craftsmanTitle} <c:out value="${sessionScope['entityCraftsman'].getName()}"/></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <table width = "70%"><tr><td>
            <img src = "/images/<c:out value="${sessionScope['entityCraftsman'].getPhoto()}"/>" align = "right" >
        <h2>${hello} <c:out value="${sessionScope['entityCraftsman'].getName()}"/></h2>
        <h3>e-mail: <c:out value="${sessionScope['entityCraftsman'].getEmail()}"/></h3>
        <p>ID: <c:out value="${sessionScope['entityCraftsman'].getId()}"/> </p>
        <p>${password}: <c:out value="${sessionScope['entityCraftsman'].getPassword()}"/> </p>
        </td></tr>
        </table><br><hr>

        <h2.l>${myOrders}: </h2.l>
        <table border=1>
            <tr>
                <td> ${ordersID}</td>
                <td> ${orderName}</td>
                <td> ${ordersFull}</td>
                <td> ${ordersStatus}</td>
            </tr>
<c:forEach var="order" items="${sessionScope.craftsmanOrders}">
    <tr>
    <td> ${order.getId()} </td>
    <td> ${order.getName()} </td>
    <td> ${order.getDescription()} </td>
    <td> ${order.getStatus()}
        <c:if test="${order.getStatus() eq 'PAID'}">
            <a href='<c:url value="/change-status-to-in-progress?orderID=${order.getId()}" />'> ${changeStatus} "IN PROGRESS"</a>
        </c:if>
        <c:if test="${order.getStatus() eq 'IN PROGRESS'}">
            <a href='<c:url value="/change-status-to-completed?orderID=${order.getId()}" />'> ${changeStatus} "COMPLETED"</a>
        </c:if>
    </td>
    </tr>
            </c:forEach>
            </table>
    </div>
    <div class="footer">
        <jsp:include page="/resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
