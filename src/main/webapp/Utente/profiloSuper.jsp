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
<%@ page import="java.util.*" %>
<%@ page import="com.example.auto_park.hibernate.entity.Utente" %>

<h1>Profilo superuser</h1>
<h2>Benvenuto ${utente.nome} ${utente.cognome}</h2>
<a href="VeicoloServlet?comando=richiediAggiungiVeicolo">Aggiungi veicolo</a>
<a href="UtenteServlet?comando=richiediAggiungiUtente">Inserisci customer</a>
<a href="PrenotazioneServlet?comando=visualizzaAllPrenotazioni">Lista prenotazioni</a>
<br>
<form action="UtenteServlet" method="post">
    <input type="hidden" name="comando" value="filtraUtenti">
    Filtra per:<select name="filtraPer">
    <option value="Nome"> Nome</option>
    <option value="Cognome"> Cognome</option>
</select>
    Testo da cercare: <input type="text" name="text">
    <input type="submit" name="Cerca">
</form>
<br>
<table border="1">
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
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${cliente.dataNascita}"/></td>
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
