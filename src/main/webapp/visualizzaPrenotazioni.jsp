<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.auto_park.hibernate.entity.Prenotazione" %>
<%
List<Prenotazione> listaPrenotazioni = new ArrayList<>();
listaPrenotazioni = (List<Prenotazione>) request.getAttribute("lp");
request.setAttribute("listaprenotazioni", listaPrenotazioni);
%>
<html>
<head>
    <title>Visualizza prenotazioni</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Lista delle prenotazioni da gestire</h2>
<table border="1">
    <tr>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Richiedente</th>
        <th>Veicolo</th>
    </tr>
    <c:forEach var="prenotazione" items="${listaprenotazioni}">
        <tr>
            <td>${prenotazione.dataInizio}</td>
            <td>${prenotazione.dataFine}</td>
            <td>${prenotazione.utente.nome} ${prenotazione.utente.cognome}</td>
            <td>${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
