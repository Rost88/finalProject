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
    <title><%=bundle.getString("Page for registration new users")%></title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <jsp:include page="resources/header.jsp"/>
<br><br>
<form action="<c:url value="/registration-users-s" />" method="post">
    <center>
    <table style="width: 30%">
        <tr>
            <td><%=bundle.getString("User's E-mail")%></td>
            <td><input type="email" name="login" placeholder="e-mail" required minlength="5" />
                <span class="form__error">This field contains e-mail like as example@site.com</span></td>
        </tr>
        <tr>
            <td><%=bundle.getString("Enter your name")%></td>
            <td><input type="text" name="name" placeholder="name" required minlength="2" maxlength="25"/>
                <span class="form__error">This field contains name from 2 to 25 simbols</span></td>
        </tr>
        <tr>
            <td><%=bundle.getString("Enter your password")%></td>
            <td><input type="password" name="password" placeholder="password 3-10 simb" required minlength="3" maxlength="10"/>
                <span class="form__error">This field contains password from 3 to 10 simbols</span></td>
        </tr>
    </table>
    </center>
    <input type="submit" value="Registration" />
</form>
    </div>
    <div class="footer">
        <jsp:include page="resources/footer.jsp"/>
    </div>
</div>
</body>
</html>
