<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 12.09.2022
  Time: 12:15
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
    <title>Task for my Final Project</title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<jsp:include page="resources/header.jsp"/>

<hr>
<%=bundle.getString("task")%>
<hr>
<jsp:include page="resources/footer.jsp"/>
</body>
</html>
