<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="lobby.js"></script>

<title>Insert title here</title>
</head>
<body>

	
	<h2>HOST: ${lobby.host }</h2>
	<h2>
		GUEST:
		<c:choose>
			<c:when test="${empty lobby.guest}">
				EMPTY
			</c:when>
			<c:otherwise>
				${lobby.guest}
			</c:otherwise>
		</c:choose>
	</h2>
	<c:if test="${ playerType eq 'host'}">
		<h2>Dimensione prato</h2>
		<form action="startGame"><!-- LobbyController -->
			<input type="hidden" name="lobbyTitle" value="${ lobbyTitle }">
			<input type="radio" name="size" value="5" checked> piccolo<br>
			<input type="radio" name="size" value="10"> medio<br> <input
				type="radio" name="size" value="20"> grande
			<h2>Numero bombe</h2>
			<input type="radio" name="bombs" value="5" checked> 5<br>
			<input type="radio" name="bombs" value="10"> 10<br> <input
				type="radio" name="bombs" value="20"> 20 <br>
			<h2>Colore bandierine (Creatore: Rosso - Ospite: Verde)</h2>
			<input type="radio" name="color" value="red" checked> Rosso<br>
			<input type="radio" name="color" value="green"> Verde<br>
			<input type="submit" value="Start Game">
		</form>

		<c:if test="${ not empty noGuest}">
			<div class="alert alert-warning" role="alert">
				<strong>Warning!</strong> You cannot start without a guest!
			</div>
		</c:if>
	</c:if>

	<form action="exitLobby">
		<input type="submit" value="Esci dalla Lobby">
	</form>
</body>
</html>