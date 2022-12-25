<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${userTitle} <c:out value="${sessionScope['entityUser'].getName()}"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/style.css" />">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>

        <h2>${hello} <c:out value="${sessionScope['entityUser'].getName()}"/></h2>
        <h3>e-mail: <c:out value="${sessionScope['entityUser'].getEmail()}"/></h3>
        <p>ID: <c:out value="${sessionScope['entityUser'].getId()}"/> </p>
        <p>${password}: <c:out value="${sessionScope['entityUser'].getPassword()}"/> </p>
        <p>${momeyOnBalance}: <c:out value="${sessionScope['entityUser'].getBalance()}"/> $</p>

        <h2.l>${myOrders}: </h2.l>
        <table border=1>
            <tr>
                <td> ${ordersID}</td>
                <td> ${orderName}</td>
                <td> ${ordersFull}</td>
                <td colspan = "3"> ${ordersStatus}</td>
                <td> ${ordersPrice}</td>
                <td> ${ordersCraftsman}</td>
            </tr>
            <c:forEach var="order" items="${sessionScope.usersOrders}">
            <tr>
                <td> ${order.getId()} </td>
                <td> ${order.getName()} </td>
                <td> ${order.getDescription()} </td>

                    <c:if test="${order.getStatus() eq 'PENDING PAYMENT'}">
                        <c:if test="${order.getPrice() gt 0}">
                            <c:if test="${order.getPrice() le order.getUser().getBalance()}">
                                <td> ${order.getStatus()} </td>
                                <td> <a href='<c:url value="/pay-order?orderID=${order.getId()}&orderPrice=${order.getPrice()}" />'> ${payOrder} </a> </td>
                                <td> <a href='<c:url value="/cancel-order?orderID=${order.getId()}" />'> ${cancelOrder} </a> </td>
                            </c:if>
                            <c:if test="${order.getPrice() gt order.getUser().getBalance()}">
                                <td colspan="2">   ${order.getStatus()} </td>
                                <td>  <a href='<c:url value="/cancel-order?orderID=${order.getId()}" />'> ${cancelOrder} </a> </td>
                            </c:if>
                        </c:if>
                        <c:if test="${order.getPrice() eq 0}">
                            <td colspan="2">     ${order.getStatus()} </td>
                            <td>  <a href='<c:url value="/cancel-order?orderID=${order.getId()}" />'> ${cancelOrder} </a> </td>
                        </c:if>
                    </c:if>
                    <c:if test="${order.getStatus() ne 'PENDING PAYMENT'}">
                <td colspan = "3">   ${order.getStatus()} </td>
                    </c:if>

                <td> ${order.getPrice()} $$</td>
                <td> ${order.getCraftsman()}
                <c:if test="${order.getFeedback() eq null}">
                    <c:if test="${order.getStatus() eq 'COMPLETED'}">
                        <form action="<c:url value="/leave-feedback" />" method="post">
                            <input type="hidden" name="orderID" value="${order.getId()}"/>
                            <input type="submit" value="leave a feedback"/>
                        </form>
                    </c:if>
                </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
        <hr>
        <form action="<c:url value="/create-new-order" />" method="get">
            <input type="submit" value="${createOrder}">
        </form>
        <hr>
    </div>
    <div class="footer">
        <jsp:include page="/resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
