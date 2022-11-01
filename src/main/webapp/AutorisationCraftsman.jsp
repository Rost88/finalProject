<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 16.09.2022
  Time: 21:17
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
    <title><%=bundle.getString("Page for autorisation craftsmans")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
<br><br>
<form action="/check-enter-craftsman" method="post">
    <center>
    <table style="width: 30%">
        <tr>
            <td><%=bundle.getString("Craftsman's E-mail")%> </td>
            <td><input type="email" name="login" placeholder="e-mail" required minlength="5"/>
                <span class="form__error">This field contains e-mail like as example@site.com</span></td>
        </tr>
        <tr>
            <td><%=bundle.getString("Enter your password")%> </td>
            <td><input type="password" name="password" placeholder="password 3-10 simb" required minlength="3" maxlength="10"/>
                <span class="form__error">This field contains password from 3 to 10 simbols</span></td>
        </tr>
    </table>
    </center>
    <br>
    <input type="submit" value="<%=bundle.getString("Enter")%>" />
</form>
    </div>
<div class="footer">
    <jsp:include page="resources/footer.jsp"/>
</div>
</div>
</body>
</html>
