<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/mainpage.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<!------ Include the above in your HEAD tag ---------->
<meta charset="UTF-8">

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="resources/scripts/mainPage.js"></script>

<body background="resources/images/minesweeper.png">

	<div class="container">
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
				<span> <img src="resources/images/minesweeper.png"
					class="w-75" alt="Logo">
				</span>
			</div>
			<div class="card-body">
				<form action="createLobby">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-bomb"></i></span>
						</div>
						<input type="text" name="lobbyTitle" class="form-control"
							placeholder="Nome lobby" required>
					</div>
					<input type="submit" value="Crea lobby"
						class="btn btn-outline-danger float-right login_btn">
				</form>
				<form action="exitMainPage">
					<input type="submit" value="Esci"
						class="btn btn-outline-danger float-left login_btn">
				</form>
				<form action="userProfile">
					<input type="submit" value="Profilo"
						class="btn btn-outline-danger float-center login_btn">
				</form>
			</div>
			<c:if test="${ not empty titleTaken}">
				<div class="alert alert-warning" role="alert">
					<strong>Attenzione!</strong> Esiste gi&agrave una lobby con questo
					nome!
				</div>
			</c:if>
			<div style="display: none" id="alertFull" class="alert alert-warning"
				role="alert">
				<strong>Attenzione!</strong> La lobby che hai selezionato &egrave
				piena.
			</div>
			<div style="display: none" id="alertError"
				class="alert alert-warning" role="alert">
				<strong>Attenzione!</strong> Si &egrave verificato un errore con la
				lobby selezionata.
			</div>
			<table class="table table-hover table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">Lobby</th>
						<th scope="col">Giocatori</th>
						<th scope="col">Capienza</th>
					</tr>
				</thead>
				<tbody id="lobbies">
					<c:forEach items="${lobbies}" var="lobby">
						<tr style="cursor: pointer"
							onclick="joinLobby($(this).attr('title'))"
							id="${lobby.titleWithoutSpaces}" title="${lobby.title}">
							<td>${lobby.title}</td>
							<td>${lobby.getNumberPlayersInside()}</td>
							<td>${lobby.capacity}</td>
						</tr>
					</c:forEach>
					<tr style="cursor: pointer">
						<td>Lobby Demo</td>
						<td>1</td>
						<td>2</td>
					</tr>
					<tr style="cursor: pointer">
						<td>Provoletta Pippo Demo</td>
						<td>1</td>
						<td>2</td>
					</tr>
					<tr style="cursor: pointer">
						<td>Provoletta Pluto Demo</td>
						<td>2</td>
						<td>2</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>