<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 28.09.2022
  Time: 12:24
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
    <title><%=bundle.getString("Top up users balance")%>.</title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <br>
<p><%=bundle.getString("Top up users balance")%>. </p>
<p></p>
<%
    String userName = request.getParameter("userName");
    String userID = request.getParameter("userID");
    String pagination = request.getParameter("pagination");
    String sort = request.getParameter("sort");
    String status = request.getParameter("status");
    String craftsman = request.getParameter("craftsman");
    String currentPage = request.getParameter("currentPage");
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
%>
<p>
    <%=bundle.getString("User")%>:   <%= userName%>, ID: <%=userID%>
</p>
<form action="/top-on-servlet" method="post">
    <input type="hidden" name="userID" value="<%=userID%>"/>
    <input type = "hidden" name = "pagination" value = "<%=pagination%>"/>
    <input type = "hidden" name = "sort" value ="<%=sort%>"/>
    <input type = "hidden" name = "status" value = "<%=status%>"/>
    <input type = "hidden" name = "craftsman" value = "<%=craftsman%>"/>
    <input type = "hidden" name = "currentPage" value = "<%=currentPage%>"/>
    <input type="number" name="sum" placeholder="<%=bundle.getString("Top up")%>" required min="1">
    <span class="form__error">Please, enter a number more than 1 </span>
    <input type="submit" value="<%=bundle.getString("Top up")%>">
</form>
</div>
<div class="footer">
    <jsp:include page="resources/footer.jsp"/>
</div>
</div>
</body>
</html>
