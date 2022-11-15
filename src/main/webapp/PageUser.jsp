<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.logic.finalproject.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 15.11.2022
  Time: 14:36
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
    User user = (User) session.getAttribute("entityUser");
    ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
%>
<head>
    <title><%=bundle.getString("Title - User's page")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <h2><%=bundle.getString("Hello") + user.getName()%></h2>
        <p><%=bundle.getString("Money on balance") + ": " + user.getBalance()%></p>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
