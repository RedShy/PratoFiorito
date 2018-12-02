<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Press Start 2P"">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="resources/scripts/game.js"></script>

<link rel="stylesheet" href="resources/css/game.css">


</head>
<body>
	<div id="playing" player="${playerType}" style="display: none;"></div>

	<div class="container">
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
				<div id="status"></div>
				<c:choose>
					<c:when test="${playerType eq 'host'}">
						<c:choose>
							<c:when test="${lobby.game.turn eq 'host'}">
								<h3 id="namePlayer" class="glow">${lobby.host}</h3>
							</c:when>
							<c:otherwise>
								<h3 id="namePlayer" class="notGlow">${lobby.host}</h3>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${lobby.game.turn eq 'guest'}">
								<h3 id="namePlayer" class="glow">${lobby.guest}</h3>
							</c:when>
							<c:otherwise>
								<h3 id="namePlayer" class="notGlow">${lobby.guest}</h3>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>



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

	<div align='center'>

		<img style="height: 100px;" src="resources/images/angryMine.png"
			alt="Logo">
		<table class="table table-sm table-dark" style="width: 20%">
			<c:forEach var="i" begin="0" end="${ lobby.game.getRows() - 1}">
				<tr>
					<c:forEach var="j" begin="0" end="${ lobby.game.getColumns() - 1 }">
						<td class="matrix" id="${i}_${j}" x="${i}" y="${j}"><img
							src="resources/images/${lobby.game.getDisplayCell(i,j) }.JPG"></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>

		<form id="exitGame" action="exitGame">
			<button type="submit" class="btn btn-default btn-lg  btn-danger">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				Esci Dalla Lobby
			</button>

		</form>

		<button onclick="showInnerGame()"
			class="btn btn-default btn-lg  btn-danger">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			Debug mode
		</button>

		<div align='center' id="innerGame" style="display: none">

			<img style="height: 100px;" src="resources/images/angryMine.png"
				alt="Logo">
			<table class="table table-sm table-dark" style="width: 20%">
				<c:forEach var="i" begin="0" end="${ lobby.game.getRows() - 1}">
					<tr>
						<c:forEach var="j" begin="0"
							end="${ lobby.game.getColumns() - 1 }">
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


		</div>
</body>
</html>