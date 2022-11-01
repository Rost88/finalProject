<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 27.09.2022
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String lang = (String) session.getAttribute("langv");
    if(lang==null)
        lang="en";
    if(request.getParameter("lang")!=null)
        lang = request.getParameter("lang");
    ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
%>
<head>
    <title><%=bundle.getString("Set Craftsman")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <br>

<%
    Cookie[] cookies = request.getCookies();
    Boolean isManager = false;
    if(cookies!=null) {
        for (Cookie cook : cookies) {
            if (cook.getName().equals("entity") && cook.getValue().equals("managers")) {
                isManager = true;
            }
        }
    }
    if(isManager == false) {
        response.sendRedirect("/");
    }
    String orderID = request.getParameter("orderID");
    String pagination = request.getParameter("pagination");
    String sort = request.getParameter("sort");
    String status = request.getParameter("status");
    String craftsman = request.getParameter("craftsman");
    String currentPage = request.getParameter("currentPage");

%>
        <p><%=bundle.getString("Set Craftsman for") + orderID %></p>
        <p><%=currentPage + " " + pagination + " " + craftsman + " " + status + " " + sort%></p>
<form action="/set-craftsman-servlet" method="get">
    <input type="hidden" name="ordid" value="<%= orderID %>"/>
    <input type = "hidden" name = "pagination" value = "<%=pagination%>"/>
    <input type = "hidden" name = "sort" value = "<%=sort%>"/>"
    <input type = "hidden" name = "status" value = "<%=status%>"/>
    <input type = "hidden" name = "craftsman" value = "<%=craftsman%>"/>
    <input type = "hidden" name = "currentPage" value = "<%=currentPage%>"/>
    <table>
        <tr>
            <td>
                <input type="radio" name="set_ord_craft" value="1"> Mario Mario
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="2"> Gadget Hackwrench
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="3"> Hubert J. Farnsworth
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="4"> Samodelkin
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="5"> Handy Beather
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="7"> Ketta Duck
            </td>
            <td>
                <input type="radio" name="set_ord_craft" value="8"> Luigi Mario
            </td>
        </tr>
        <tr>
            <td> <th colspan="4">
    <input type="submit" value="<%=bundle.getString("Set Craftsman")%>">
        </th>
            </td>
        </tr>
    </table>
</form>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
