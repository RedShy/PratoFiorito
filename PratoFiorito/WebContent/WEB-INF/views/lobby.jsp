<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/lobby.css">
<script src="resources/scripts/lobby.js"></script>
</head>
<body>
	<div class="container">
		<div
			class="card card-login mx-auto text-center bg-dark shadow-lg p-3 mb-5 rounded">
			<div class="card-header mx-auto bg-dark">
				<div class="card-body">
					<h1 style="color: red">${lobby.host}</h1>
					ha creato la lobby
				</div>
				<img style="height: 100px;" src="resources/images/angryMine.png"
					alt="Logo">
				<div id="lobbyTitle">${ lobby.title }</div>
				<c:if test="${ playerType eq 'host'}">
					<form class="match shadow rounded" action="startGame">
						<div id="difficulty">
							<input type="radio" name="difficulty" value="beginner" checked>Facile
							<input type="radio" name="difficulty" value="intermediate">
							Medio <input type="radio" name="difficulty" value="advanced">
							Difficile
						</div>
						<div id="attendi"
							<c:if test="${not empty lobby.guest}">style="display:none"</c:if>>
							<div>
								<img src="resources/images/caricamento.gif" alt="attendi...">
							</div>
							<div>Attendi compagno</div>
						</div>

						<input id="startGameButton"
							class="btn btn-outline-danger  login_btn" type="submit"
							value="Avvia Partita"
							<c:if test="${empty lobby.guest}">disabled</c:if> />

					</form>
					<c:if test="${ not empty noGuest}">
						<div>
							<div class="alert-warning" role="alert">
								<strong>Attenzione!</strong> Non puoi iniziare la partita da
								solo!
							</div>
						</div>
					</c:if>
					<c:if test="${ playerType eq 'host'}">
						<div class="card-body" id="guestName">
							<c:if test="${not empty lobby.guest}">Gioco con: ${lobby.guest}</c:if>
						</div>
					</c:if>
				</c:if>

			</div>

			<a href="exitLobby" class="btn btn-danger btn-lg" type="reset"> <i
				class="fas fa-bomb"></i> Ritorna alla lista delle Lobby
			</a>

		</div>
	</div>
</body>
</html>
