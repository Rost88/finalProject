<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 01.10.2022
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Boolean isUser = false;
    Cookie[] cookie = request.getCookies();
    if(cookie!=null) {
        for (Cookie c : cookie) {
            if (c.getName().equals("entity") && c.getValue().equals("user")) {
                isUser = true;
            }
        }
    }
    if(!isUser)
        response.sendRedirect("/");

    String lang = (String) session.getAttribute("langv");
    if(lang==null)
        lang="en";
    if(request.getParameter("lang")!=null)
        lang = request.getParameter("lang");
    ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
%>
<head>
    <title><%=bundle.getString("Leave feedback")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
        <br>
<%
    String orderID = request.getParameter("orderID");
%>
<p>
 <%=bundle.getString("Leave a feedback") + orderID%>
</p>
        <br>
<p>
    <form action="/servlet-leave-feedback" method="post">
    <input type="hidden" name="orderID" value="<%=orderID%>" />
        <textarea style="height: 200px; weigh: 500px" rows = "5" name="feedback"
                  placeholder="<%=bundle.getString("Leave feedback")%> 5 - 200 simbols" required minlength="5" maxlength="200"></textarea>
        <span class="form__error">This field contains text from 5 to 200 simbols</span><br><br>
    <input type="submit" value="<%=bundle.getString("Leave feedback")%>" />
</form>
</p>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
