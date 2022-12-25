<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="languages.ChooseLanguage" %>
<%@ page import="static languages.ChooseLanguage.chooseBundle" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    final Logger logger = LoggerFactory.getLogger("index.jsp");

   String lang = (String) session.getAttribute("langv");
   if(lang==null)
       lang="en";
    if(request.getParameter("lang")!=null)
        lang = request.getParameter("lang");
//   ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);
   ResourceBundle bundle = chooseBundle(lang);

%>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/style.css" />">
    <title><%=bundle.getString("indexTitle")%></title>
    <%
        logger.info("bundle1 from title index.jsp {}", bundle.getString("indexTitle"));
    %>
</head>
<body>
<jsp:include page="resources/header.jsp"/>

<h1><%=bundle.getString("index Welcome to my Final Project")%>
</h1>

<%logger.info("after start bundel : Welcome to my Final Project");%>
<br/>
<h1><%=bundle.getString("index Repair agency \"Hands from that place!\"")%> </h1>
<br/>
<h2><%=bundle.getString("index welcome2")%></h2>

 <p>
    <img src="<c:url value="/images/PQ3a.gif" />">
</p>
<p>

</p>
<p>
 <h3> <%= bundle.getString("index Call us")%> </h3>
</p>
<p>
    <img src="<c:url value="/images/Vwhr.gif" />">
</p>

<jsp:include page="resources/footer.jsp"/>
</body>
</html>