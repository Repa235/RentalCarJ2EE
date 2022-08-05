<%@ page import="com.example.auto_park.hibernate.entity.Utente" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<html>

<head>
    <title>Header</title>
</head>
<body>

<c:url var="Homepage" value="Homepage" />
<a href="${Homepage}"> Homepage </a>
<a href="#"> Parco auto </a>

<%
HttpSession session1 = request.getSession();
    Utente u = (Utente) session1.getAttribute("utente");
    boolean show = false;
    if(u!=null) {
        show = true;
        request.setAttribute("utente", u);
    }
    request.setAttribute("show", show);
%>
<c:if test="${utente.tipo == 'superuser'}">

    <c:url var="visualizzaAllPrenotazioni" value="PrenotazioneServlet">
        <c:param name="comando" value="visualizzaAllPrenotazioni"/>
    </c:url>
    <a href="${visualizzaAllPrenotazioni}">Lista prenotazioni</a>

</c:if>
<c:if test="${show}">

    <c:url var="profiloUtente" value="UtenteServlet">
        <c:param name="comando" value="profiloUtente"/>
    </c:url>
    <a href="${profiloUtente}"> Profilo utente </a>

    <c:url var="logout" value="UtenteServlet">
        <c:param name="comando" value="logout"/>
    </c:url>
    <a href="${logout}"> Logout </a>
</c:if>

</body>
</html>
