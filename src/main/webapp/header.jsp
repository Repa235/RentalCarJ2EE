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
<a href="Homepage"> Homepage </a>
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
    <a href="PrenotazioneServlet?comando=visualizzaAllPrenotazioni">Lista prenotazioni</a>
</c:if>
<c:if test="${show}">
    <a href="UtenteServlet?comando=profiloUtente"> Profilo utente </a>
    <a href="UtenteServlet?comando=logout"> Logout </a>
</c:if>

</body>
</html>
