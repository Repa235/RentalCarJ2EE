<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.auto_park.hibernate.entity.Veicolo" %>

<html>
<head>
    <title>Visualizza veicoli</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h2>Lista dei veicoli</h2>
<table border="1">
    <tr>
        <th>Casa costruttrice</th>
        <th>Modello</th>
        <th>Anno</th>
        <th>Tipologia veicolo</th>
        <th>Prenota</th>
    </tr>
    <c:forEach var="ve" items="${listaveicoli}">
        <tr>
            <td>${ve.casaCostruttrice}</td>
            <td>${ve.modello}</td>
            <td>${ve.annoImmatricolazione}</td>
            <td>${ve.tipo}</td>
            <td>Prenota</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
