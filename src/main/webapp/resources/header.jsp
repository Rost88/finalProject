<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="languages.ChooseLanguage" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="com.logic.finalproject.ServletCheckEnterManager" %><%--
  Created by IntelliJ IDEA.
  User: Rostislav
  Date: 22.10.2022
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%  final Logger logger = LoggerFactory.getLogger("header.jsp");
    String lang = "none";
    String lang1 = (String) session.getAttribute("langv");
    logger.info("log on header.jsp start");
    logger.info("show lang = {}", lang);
    logger.info("show lang1 = {}", lang1);
       if(request.getParameter("lang")!=null)
        lang = request.getParameter("lang");


    if(!lang.equals("none"))
    session.setAttribute("langv", lang);
    logger.info("log on header.jsp after get.request");
    logger.info("show lang = {}", lang);
    logger.info("show lang1 = {}", lang1);

    lang1 = (String) session.getAttribute("langv");
    if(lang1!=null)
        lang = (String) lang1;



    ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
 //   ResourceBundle bundleEN = ResourceBundle.getBundle("languages.resource", new Locale("en"));
//    bundle = bundleUA;

%>
<table width="100%"><tr><td width="300px">
    <a href="/"><img src="/images/logo.gif" align="left"></a> </td><td align="center"> <h3><%=bundle.getString("phone")%>: +380(88) 888 88 88</h3>
</td><td width="300px"><form method="get" ><input type="hidden" name="lang" value="en"/>
    <button ><img src="/images/en.jpg"> </button></form><form method="get" ><input type="hidden" name="lang" value="uk"/><button><img src="/images/uk.jpg"> </button></form></td>
</tr>
</table>
<p>lang <%=lang%></p>
<p>lang1 <%=lang1%></p>
<hr>
<ul class="main-menu">
    <li><a href="/"> <%=bundle.getString("Main page")%> </a> </li>
    <li><a href="/task"> <%=bundle.getString("My task for Final Project")%> </a> </li>
    <li><a href="/road-map"> <%=bundle.getString("Road map")%> </a> </li>
    <li><a href="/team"> <%=bundle.getString("Our team")%> </a> </li>

</ul>
<hr>
</html>
