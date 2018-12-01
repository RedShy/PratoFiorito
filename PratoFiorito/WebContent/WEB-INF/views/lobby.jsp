<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="resources/scripts/lobby.js"></script>


<title>Insert title here</title>
</head>
<body>
	<h1>LOBBY PAGE ${ lobby.title } sono ${ playerType }</h1>
	<h2>HOST: ${lobby.host }</h2>
	<h2 id="guestName">
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
		<h2>Difficoltà</h2>
		<form action="startGame">
			<input type="radio" name="difficulty" value="beginner" checked> facile<br>
			<input type="radio" name="difficulty" value="intermediate"> medio<br> <input
				type="radio" name="difficulty" value="expert"> avanzato
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