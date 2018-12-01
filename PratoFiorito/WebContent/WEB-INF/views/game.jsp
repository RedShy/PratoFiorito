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
	<div id="playing" player="${playerType}" style="display: none;"></div>
	<h1>
		GAME PAGE
		<c:choose>
			<c:when test="${gameEnded eq 'lose'}">
				<p style="color: Red;">HAI PERSO!</p>
				<audio autoplay>
					<source src="resources/sounds/youLose.mp3" type="audio/mp3">
				</audio>
			</c:when>
			<c:when test="${gameEnded eq 'win'}">
				<p style="color: Green;">HAI VINTO!</p>
				<audio autoplay>
					<source src="resources/sounds/youWin.mp3" type="audio/mp3">
				</audio>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</h1>
	<div id="status"></div>
	<h2>TURNO PER GIOCARE:</h2>
	<h3>${lobby.host}</h3>
	<c:choose>
		<c:when test="${lobby.game.turn eq 'host'}">
			<img id="playingTurn" src="resources/images/lighton.png" />
			<h3>${lobby.guest}</h3>
			<img id="notPlayingTurn" src="resources/images/lightoff.png" />
		</c:when>
		<c:otherwise>
			<img id="notPlayingTurn" src="resources/images/lightoff.png" />
			<h3>${lobby.guest}</h3>
			<img id="playingTurn" src="resources/images/lighton.png" />
		</c:otherwise>
	</c:choose>


	
	<h2>Stato interno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ lobby.game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ lobby.game.getSize() - 1 }">
					<td><c:choose>
							<c:when test="${lobby.game.getCell(i,j)=='1'}">
								<img src="resources/images/bomb.JPG">
							</c:when>
							<c:otherwise>
								<img src="resources/images/${lobby.game.getCell(i,j) }.JPG">
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<h2>Stato esterno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ lobby.game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ lobby.game.getSize() - 1 }">
					<td class="matrix" id="${i}_${j}" x="${i}" y="${j}"><img
						src="resources/images/${lobby.game.getDisplayCell(i,j) }.JPG"></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<form id="exitGame" action="exitGame">
		<input type="submit" value="Esci dal gioco">
	</form>
</body>
</html>