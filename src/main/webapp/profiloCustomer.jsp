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
    <title>Customer page</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
    Utente ut = (Utente) request.getAttribute("customer");
    request.setAttribute("utente", ut);
%>
<h1>Profilo customer</h1>
<h2>Benvenuto ${utente.nome} ${utente.cognome}</h2>
<a href="VisualizzaVeicoliCustomer">Visualizza veicoli</a>
<a href="RichiediPrenotaVeicolo">Prenota veicolo</a>
<a href="formModificaUtente.jsp">Modifica profilo</a>
<br>
<table border="1">
    <tr>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Veicolo</th>
        <th>Modifica</th>
        <th>Elimina</th>
    </tr>
    <c:forEach var="prenotazione" items="${utente.prenotazioni}">
        <tr>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${prenotazione.dataInizio}"/></td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${prenotazione.dataFine}"/></td>
            <td>${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}</td>
            <td>
                <form action="RichiediModificaPrenotazione" method="post">
                    <input type="hidden" name="id" value="${prenotazione.id}">
                    <input type="submit" value="Modifica">
                </form>
            </td>
            </td>
            <td>
                <form action="EliminaPrenotazione" method="post">
                    <input type="hidden" name="id" value="${prenotazione.id}">
                    <input type="submit" value="Elimina">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
