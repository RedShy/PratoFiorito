<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="resources/scripts/game.js"></script>	

<title>Insert title here</title>
</head>

<body>
	<!-- TODO: c'è bisogno di un modo per inibire che il click venga effettuato, o 
	lo inibiamo lato client impedendo che javascript faccia la chiamata ajax se non è il proprio turno, 
	oppure bisogna comunicare chi sta giocando al server in modo che inibisca l'azione se non è il proprio turno  -->
	<div id="playing" player="${playerType}" style="display: none;"></div>
	<h1>
		GAME PAGE
		<c:choose>
			<c:when test="${gameEnded eq 'lose'}">
				<p style="color: Red;">HAI PERSO!</p>
			</c:when>
			<c:when test="${gameEnded eq 'win'}">
				<p style="color: Green;">HAI VINTO!</p>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</h1>
	<h2>Stato interno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ game.getSize() - 1 }">
					<td><c:choose>
							<c:when test="${game.getCell(i,j)=='1'}">
								<img src="resources/images/bomb.JPG">
							</c:when>
							<c:otherwise>
								<img src="resources/images/${game.getCell(i,j) }.JPG">
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<h2>Stato esterno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ game.getSize() - 1 }">
					<td class="matrix" x="${i}" y="${j}"><img
						src="resources/images/${game.getDisplayCell(i,j) }.JPG"></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	
	<form action="exitGame">
		<input type="submit" value="Esci dal gioco">
	</form>
</body>
</html>