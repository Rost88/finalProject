<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 23.10.2022
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main role="main"></main>
<%
    String lang = (String) session.getAttribute("langv");
    if(lang==null)
        lang="en";
    if(request.getParameter("lang")!=null)
        lang = request.getParameter("lang");
    ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
%>
<hr>
<footer style="background: gainsboro">
    <table><tr><td align="left">
        <p><a href="/autorisation-users" style="text-decoration: none; color: #B22522"><%=bundle.getString("Page for autorisation users")%> </a></p>
        <p><a href="/registration-users" style="text-decoration: none; color: #B22522"><%=bundle.getString("Page for registration new users")%> </a> </p>
    </td>
        <td><p><a href="/autorisation-managers" style="text-decoration: none; color: #B22522"><%=bundle.getString("Page for autorisation managers")%> </a></p><p></p></td>
        <td align="right"><p><a href="/autorisation-craftsmen" style="text-decoration: none; color: #B22522"><%=bundle.getString("Page for autorisation craftsmans")%> </a> </p><p></p></td>
    </tr>
    </table>
</footer>
