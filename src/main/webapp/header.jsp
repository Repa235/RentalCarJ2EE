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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
</head>
<body>

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
<c:url var="Homepage" value="Homepage" />
<nav class="navbar navbar-expand-lg" style="background-color: #C5D1EB;">
    <div class="container-fluid">
        <a class="navbar-brand" href="${Homepage}">SI2001 Autopark</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link"  href="${Homepage}">Home</a>
                <a class="nav-link" href="#">Parco auto</a>

                <c:if test="${utente.tipo == 'superuser'}">

                    <c:url var="visualizzaAllPrenotazioni" value="PrenotazioneServlet">
                        <c:param name="comando" value="visualizzaAllPrenotazioni"/>
                    </c:url>
                    <a class="nav-link" href="${visualizzaAllPrenotazioni}">Lista prenotazioni</a>
                </c:if>
                <c:if test="${show}">

                    <c:url var="profiloUtente" value="UtenteServlet">
                        <c:param name="comando" value="profiloUtente"/>
                    </c:url>
                    <a class="nav-link" href="${profiloUtente}"> Profilo utente </a>

                    <c:url var="logout" value="UtenteServlet">
                        <c:param name="comando" value="logout"/>
                    </c:url>
                    <a class="nav-link" href="${logout}"> Logout </a>
                </c:if>


            </div>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
