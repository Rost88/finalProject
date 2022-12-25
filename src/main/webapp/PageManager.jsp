<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${managerTitle} <c:out value="${sessionScope['entityManager'].getName()}"/></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <table width = "70%"><tr><td>
            <img src = "/images/<c:out value="${sessionScope['entityManager'].getPhoto()}"/>" align = "right" >
            <h2>${hello} <c:out value="${sessionScope['entityManager'].getName()}"/></h2>

            <h3>e-mail: <c:out value="${sessionScope['entityManager'].getEmail()}"/></h3>
            <p>ID: <c:out value="${sessionScope['entityManager'].getId()}"/> </p>
            <p>${password}: <c:out value="${sessionScope['entityManager'].getPassword()}"/> </p>
        </td></tr>
        </table><br>
        <hr>
        <form action="<c:url value="/mymanagers" />" method="get">
            <table>
                <tr>
                    <td width="50%"> ${сhooseStatus}</td>
                    <td> ${сhooseCraftsman}</td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="status" value="PENDING PAYMENT"> PENDING PAYMENT
                        <input type="radio" name="status" value="CANCELED"> CANCELED
                        <input type="radio" name="status" value="PAID"> PAID
                        <input type="radio" name="status" value="IN PROGRESS"> IN PROGRESS
                        <input type="radio" name="status" value="COMPLETED"> COMPLETED
                    </td>
                    <td>
                        <input type="radio" name="craftsman" value="unknown"> unknown
                        <input type="radio" name="craftsman" value="Mario Mario"> Mario Mario
                        <input type="radio" name="craftsman" value="Gadget Hackwrench"> Gadget Hackwrench
                        <input type="radio" name="craftsman" value="Hubert J. Farnsworth"> Hubert J. Farnsworth
                        <br><input type="radio" name="craftsman" value="Samodelkin"> Samodelkin
                        <input type="radio" name="craftsman" value="Handy Beather"> Handy Beather
                        <input type="radio" name="craftsman" value="Luigi Mario"> Luigi Mario
                        <input type="radio" name="craftsman" value="Ketta Duck"> Ketta Duck
                        </td>
                </tr>
                <tr><td>
                    <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
<%--&lt;%&ndash;                    <input type = "hidden" name = "sort" value = "sort"/>&ndash;%&gt;--%>
                    </td></tr>
                <tr>
                    <td colspan = "2"><input type="submit" value="${applyFilters}"></td>
                </tr>
            </table>
        </form>
        <hr>
        <br>
        <form action="<c:url value="/mymanagers" />" method="get">
            <table>
                <tr>
                    <td>${show}</td>
                </tr>
                <tr><td>
                <input type="radio" name="pagination" value="5"> 5
                <input type="radio" name="pagination" value="10"> 10
                <input type="radio" name="pagination" value="all"> all
                <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                <input type = "hidden" name = "sort" value = "${sessionScope.sort}"/>
                </td></tr>
                <tr><td><input type="submit" value="${showOnThePage}"></td>
                </tr>
            </table>
        </form>
        <br>
        <hr>
        <br>

        <table>
            <tr>
                <td width = "48%" align = "right">
                    <c:if test="${sessionScope.currentPage gt 1}">
                    <form action="<c:url value="/mymanagers" />" method="get">
                        <input type = "hidden" name = "currentPage" value = "${sessionScope.currentPage - 1}"/>
                        <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                        <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                        <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                        <input type = "hidden" name = "sort" value = "${sessionScope.sort}"/>
                        <input type="submit" value="&lt;&lt; ${prev}" />
                        </form>
                    </c:if>
                </td>
                <td width = "2%"> <c:out value="${sessionScope.currentPage}"/></td>
                <td width = "48%" align = "left">
                    <c:if test="${sessionScope.currentPage lt sessionScope.totalPage}">
                    <form action="<c:url value="/mymanagers" />" method="get">
                        <input type = "hidden" name = "currentPage" value = "${sessionScope.currentPage + 1}"/>
                        <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                        <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                        <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                        <input type = "hidden" name = "sort" value = "${sessionScope.sort}"/>
                        <input type="submit" value="${next} &gt;&gt;" />
                    </form>
                    </c:if>
                </td>
            </tr>
        </table>
        <br>
        <hr>

        <p><a href = "<c:url value="/mymanagers?pagination=5&status=&craftsman=&sort=" />">${resetAllFilters}</a></p> <br>

        <table border = 1 >
            <tr>
                <td> ${ordersID}</td>
                <td> ${orderName}</td>
                <td> ${user}</td>
                <td>
                    <form action="<c:url value="/mymanagers" />" method="get">
                        <input type = "hidden" name = "currentPage" value = "1"/>
                        <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                        <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                        <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                        <input type = "hidden" name = "sort" value = "byStatus"/>
                        <input type="submit" value="${orderStatus}" />
                    </form>
                </td>
                <td>${orderCraftsman}</td>
                <td>
                    <form action="<c:url value="/mymanagers" />" method="get">
                        <input type = "hidden" name = "currentPage" value = "1"/>
                        <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                        <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                        <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                        <input type = "hidden" name = "sort" value = "byDate"/>
                        <input type="submit" value="${orderData}" />
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/mymanagers" />" method="get">
                        <input type = "hidden" name = "currentPage" value = "1"/>
                        <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                        <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                        <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                        <input type = "hidden" name = "sort" value = "byPrice"/>
                        <input type="submit" value="${orderPrice}" />
                        </form>
                </td>
            </tr>
            <c:forEach var="order" items="${sessionScope.ordersOnPage}">
            <tr>
                <td>${order.getId()}</td>
                <td>${order.getName()}</td>
                <td>
                    <a href = "<c:url value="/top-on?userName=${order.getUser()}&userID=${order.getUser().getId()}&currentPage=${sessionScope.currentPage}&pagination=${sessionScope.pagination}&sort=${sessionScope.sort}&status=${sessionScope.checkStatus}&craftsman=${sessionScope.checkCraftsman}"/>"> top up </a>
                        ${order.getUser().getName()}
                </td>
                <td>${order.getStatus()}</td>
                <td>
                    <c:choose>
                    <c:when test="${(order.getCraftsman().getName() eq 'unknown') and (order.getStatus() ne 'CANCELED')}">
                        <a href='<c:url value="set-craftsman?orderID=${order.getId()}&currentPage=${sessionScope.currentPage}&pagination=${sessionScope.pagination}&sort=${sessionScope.sort}&status=${sessionScope.checkStatus}&craftsman=${sessionScope.checkCraftsman}" />'>${order.getCraftsman().getName()} ${setCraftsman}</a>
                        </c:when>
                        <c:otherwise>
                            ${order.getCraftsman().getName()}
                        </c:otherwise>
                    </c:choose>

                </td>
                <td>${order.getDate()}</td>
                <td>
                        ${order.getPrice()}
                    <c:if test="${(order.getPrice() eq 0) and (order.getStatus() ne 'CANCELED')}">
                            <form action = "<c:url value="/change-order-price" />" method = "get">
                                <input type = "hidden" name = "pagination" value = "${sessionScope.pagination}"/>
                                <input type = "hidden" name = "sort" value = "${sessionScope.sort}"/>
                                <input type = "hidden" name = "status" value = "${sessionScope.checkStatus}"/>
                                <input type = "hidden" name = "craftsman" value = "${sessionScope.checkCraftsman}"/>
                                <input type = "hidden" name = "currentPage" value = "${sessionScope.currentPage}"/>
                                <input type= "number" name = "newprice" placeholder= "new price" min= "1"><button type = "submit" name = "orID" value = "${order.getId()}">Изменить цену</button></form>
                             </form>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
        <p>Active filters: </p>
        <p>Status: <c:out value="${sessionScope.checkStatus}"/>;
            Craftsman: <c:out value="${sessionScope.checkCraftsman}"/>;
            Sort: <c:out value="${sessionScope.sort}"/>;
            Total Records: <c:out value="${sessionScope.totalRecord}"/>;
            Page size : <c:out value="${sessionScope.pageSize}"/>;
<%--            (pagination): <c:out value="${sessionScope.pagination}"/>;--%>
            Total Page: <c:out value="${sessionScope.totalPage}"/>;
            Current Page: <c:out value="${sessionScope.currentPage}"/></p>
    </div>
    <div class="footer">
        <jsp:include page="/resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
