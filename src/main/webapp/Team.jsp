<%@ page import="com.example.finalproject.ShowTeam" %>
<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 01.10.2022
  Time: 17:46
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
    <title><%=bundle.getString("Our team")%></title>
  <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<jsp:include page="resources/header.jsp"/>
<h2>
  <%=bundle.getString("Our managers")%>:
</h2>
<table align="center">
  <tr align="center">
    <td><h3><%=bundle.getString("Beavis")%></h3></td>
    <td><h3><%=bundle.getString("Butt-head")%></h3></td>
  </tr>
  <tr>
    <td><img src="/images/beavis.png"> </td>
    <td><img src="/images/butt-head.png"></td>
  </tr>
</table>

<h2 align="center">
  <%=bundle.getString("Our craftsmen")%>:
</h2>
<%=ShowTeam.tableCraftsmen()%>
<jsp:include page="resources/footer.jsp"/>
</body>
</html>
