<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle" %>
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
            <a href="<c:url value="/" />"><%=bundle.getString("back to Main page")%></a>
        </p>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
