<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 05.10.2022
  Time: 14:01
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
    String rm = bundle.getString("Road map");
%>
<head>
    <title><%=rm%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<jsp:include page="resources/header.jsp"/>
<h2><%=rm%></h2>
<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d7396.155176011495!2d30.528966568533676!3d50.45053420885529!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2sua!4v1664967520839!5m2!1sru!2sua" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
<jsp:include page="resources/footer.jsp"/>
</body>
</html>
