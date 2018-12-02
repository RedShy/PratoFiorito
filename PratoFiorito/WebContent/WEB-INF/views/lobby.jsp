<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
				<div class="card-body">
					<h2>${lobby.host}</h2>
					ha creato la lobby
				</div>
				<img style="height: 100px;" src="resources/images/angryMine.png"
					alt="Logo">
				<table class="table table-sm table-dark">
					<tbody>
						<tr>
							<td valign="middle" align="center">
								<div id="lobbyTitle">${ lobby.title }</div> <c:if
									test="${ playerType eq 'host'}">
									<form class="match" action="startGame">
										<div id="difficulty">
											<input type="radio" name="difficulty" value="beginner"
												checked>Facile <input type="radio" name="difficulty"
												value="intermediate"> Medio <input type="radio"
												name="difficulty" value="advanced"> Difficile
										</div>
										<input id="startGameButton"
											class="btn btn-outline-danger  login_btn" type="submit"
											value="Avvia Partita"
											<c:if test="${empty lobby.guest}">disabled</c:if> />
										<div id="attendi">
											<c:if test="${empty lobby.guest}">
												<div>
													<img src="resources/images/caricamento.gif" alt="attendi..">
												</div>
												<div>Attendi compagno</div>
											</c:if>
										</div>
									</form>
									<c:if test="${ not empty noGuest}">
										<div>
											<div class="alert-warning" role="alert">
												<strong>Warning!</strong> You cannot start without a guest!
											</div>
										</div>
									</c:if>
									<c:if test="${ playerType eq 'host'}">
										<div class="card-body" id="guestName">
											<c:if test="${not empty lobby.guest}">Gioco con: ${lobby.guest}</c:if>
										</div>
									</c:if>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<form action="exitLobby">
				<button type="submit" class="btn btn-default btn-lg  btn-danger">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					Esci Dalla Lobby
				</button>
			</form>
		</div>
	</div>



</body>
</html>