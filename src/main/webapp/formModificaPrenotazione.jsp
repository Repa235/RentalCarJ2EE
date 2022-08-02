<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.auto_park.hibernate.entity.Veicolo" %>
<%@ page import="com.example.auto_park.hibernate.entity.Prenotazione" %>
<%@ page import="java.util.List" %>
<%
    Prenotazione p = (Prenotazione) request.getAttribute("prenotazione");
    request.setAttribute("prenotazione", p);
    List<Veicolo> veicoli = (List<Veicolo>) request.getAttribute("veicoli");
    request.setAttribute("veicoli", veicoli);
%>
<html>
<head>
    <title>Prenota veicolo</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Prenota un'auto</h1>
<form action="ModificaPrenotazione" method="post">
    <input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
    <br>
    Data inizio: <input name="dataInizio" type="date" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${prenotazione.dataInizio}" />">
    <br>
    Data fine: <input name="dataFine" type="date" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${prenotazione.dataFine}" />">
    <br>
    Veicolo:
    <select name="veicolo" >
    <c:forEach var="v" items="${veicoli}" >
        <option value="${v.id}" selected="${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}">${v.casaCostruttrice} ${v.modello}</option>
    </c:forEach>
    </select>
    <br>
    <input type="submit" value="Modifica prenotazione">
</form>

</body>
</html>
