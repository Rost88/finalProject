<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 20.09.2022
  Time: 12:37
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
    <title>User is creating new order</title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
<p>

</p>
<p>
    <% Boolean isUser = false;
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
        %>

</p>
<form action="/new-order" method="post">


            <p><%=bundle.getString("Order's short description")%></p>

            <p><input style="width: 500px" type="text" name="short"
                      placeholder="<%=bundle.getString("Order's short description")%> 10 - 100 sim" required minlength="10" maxlength="100"/>
                <span class="form__error">This field contains text from 10 to 100 simbols</span></p>

            <p><%=bundle.getString("Order's full description")%></p>
         <%--  <p><input style="height: 200px; weigh: 400px" rows = "5"  type="textarea"  name="full" /></p> --%>
    <p><textarea  style="height: 200px; weigh: 400px" rows = "5"  name="full"
                  placeholder="<%=bundle.getString("Order's full description")%> 10 - 500 sim" required minlength="10" maxlength="500"></textarea>
        <span class="form__error">This field contains text from 10 to 100 simbols</span></p>
       <p>

    <input type="submit" value="<%=bundle.getString("Create new order")%>" /></p>
</form>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
