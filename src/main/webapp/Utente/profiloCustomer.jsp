<%@ page import="com.example.auto_park.hibernate.entity.Prenotazione" %><%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Customer page</title>
</head>
<body>
<%@include file="../header.jsp" %>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Benvenuto ${utente.nome} ${utente.cognome}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item" style="margin: 1px">
                    <c:url var="visualizzaVeicoli" value="VeicoloServlet">
                        <c:param name="comando" value="visualizzaVeicoli"/>
                    </c:url>
                    <a class="btn btn-secondary" href="${visualizzaVeicoli}"> Visualizza veicoli </a>
                </li>
                <li class="nav-item" style="margin: 1px">
                    <c:url var="richiediPrenotazioneByDates" value="PrenotazioneServlet">
                        <c:param name="comando" value="richiediPrenotazioneByDates"/>
                    </c:url>
                    <a class="btn btn-secondary" href="${richiediPrenotazioneByDates}"> Prenota veicolo </a>
                </li>
                <li class="nav-item" style="margin: 1px">
                    <form action="UtenteServlet" method="post">
                        <input type="hidden" name="comando" value="richiediModificaUtente">
                        <input type="hidden" name="id" value="${utente.id}">
                        <input class="btn btn-secondary" type="submit" value="Modifica i tuoi dati ">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>




<br>
<table class="table table-bordered">
    <tr>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Veicolo</th>
        <th>Approvata</th>
        <th>Modifica</th>
        <th>Elimina</th>
    </tr>
    <c:forEach var="prenotazione" items="${utente.prenotazioni}">
        <tr>
            <td>${prenotazione.dataInizio}</td>
            <td>${prenotazione.dataFine}</td>
            <td>${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}</td>
            <td>
                <c:choose>
                    <c:when test="${prenotazione.approvato}">SI</c:when>
                    <c:otherwise>NO</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${now gt prenotazione.dataFine}">
                        <p>Scaduta</p>
                    </c:when>
                    <c:otherwise>
                        <form action="PrenotazioneServlet" method="post">
                            <input type="hidden" name="id" value="${prenotazione.id}">
                            <input type="hidden" name="comando" value="richiediModificaPrenotazione">
                            <input type="submit" value="Modifica">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
            </td>
            <td>
                <c:choose>
                    <c:when test="${now gt prenotazione.dataFine}">
                        <p>Scaduta</p>
                    </c:when>
                    <c:otherwise>
                        <form action="PrenotazioneServlet" method="post">
                            <input type="hidden" name="id" value="${prenotazione.id}">
                            <input type="hidden" name="comando" value="eliminaPrenotazione">
                            <input type="submit" value="Elimina">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
