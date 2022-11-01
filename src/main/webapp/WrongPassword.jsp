<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 25.10.2022
  Time: 12:44
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
    <title><%=bundle.getString("You entered a wrong password")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <%
            String login = request.getParameter("login");
            String password = request.getParameter("password");
        %>
        <p>
            <%=login + ". " + bundle.getString("You entered a wrong password") + ": " + password%>
        </p>
        <p>
            <a href="/"><%=bundle.getString("back to Main page")%></a>
        </p>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
