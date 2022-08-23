<%--
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
    <title>Superuser page</title>
</head>
<body>
<%@include file="../header.jsp" %>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Benvenuto ${utente.nome} ${utente.cognome}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item" style="margin: 1px">
                    <c:url var="richiediAggiungiVeicolo" value="VeicoloServlet">
                        <c:param name="comando" value="richiediAggiungiVeicolo"/>
                    </c:url>
                    <a class="btn btn-secondary" href="${richiediAggiungiVeicolo}"> Aggiungi un veicolo </a>
                </li>
                <li class="nav-item" style="margin: 1px">
                    <c:url var="richiediAggiungiUtente" value="UtenteServlet">
                        <c:param name="comando" value="richiediAggiungiUtente"/>
                    </c:url>
                    <a class="btn btn-secondary" href="${richiediAggiungiUtente}"> Aggiungi un utente </a>
                </li>
                <li class="nav-item" style="margin: 1px">
                    <c:url var="visualizzaAllPrenotazioni" value="PrenotazioneServlet">
                        <c:param name="comando" value="visualizzaAllPrenotazioni"/>
                    </c:url>
                    <a class="btn btn-secondary" href="${visualizzaAllPrenotazioni}"> Visualizza tutte le
                        prenotazioni </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<form action="UtenteServlet" method="post">
    <div class="input-group">
        <span class="input-group-text">Cerca utente per: </span>
        <select class="form-select" name="filtraPer">
            <option value="nome"> Nome</option>
            <option value="cognome"> Cognome</option>
        </select>
        <span class="input-group-text">Testo da cercare: </span>
        <input type="text" name="text" class="form-control">
        <input type="hidden" name="comando" value="filtraUtenti">
        <input type="submit" name="Cerca">
    </div>
</form>
<br>
<table class="table table-bordered">
    <tr>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Data di nascita</th>
        <th>Prenotazioni</th>
        <th>Modifica</th>
        <th>Elimina</th>
    </tr>
    <c:forEach var="cliente" items="${clienti}">
        <tr>
            <td>${cliente.nome}</td>
            <td>${cliente.cognome}</td>
            <td>${cliente.dataNascita}</td>
            <td>
                <form action="PrenotazioneServlet" method="post">
                    <input type="hidden" name="comando" value="visualizzaPrenotazioni">
                    <input type="hidden" name="id" value="${cliente.id}">
                    <input type="submit" value="Visualizza">
                </form>
            </td>
            <td>
                <form action="UtenteServlet" method="post">
                    <input type="hidden" name="comando" value="richiediModificaUtenteBySuperUser">
                    <input type="hidden" name="id" value="${cliente.id}">
                    <input type="submit" value="Modifica">
                </form>
            </td>
            </td>
            <td>
                <form action="UtenteServlet" method="post">
                    <input type="hidden" name="comando" value="elimina">
                    <input type="hidden" name="id" value="${cliente.id}">
                    <input type="submit" value="Elimina">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
