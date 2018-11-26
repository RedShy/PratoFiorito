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
	
	<script type="text/javascript">
	function getEventsFromServer() {
		$.ajax({
			url : "getLobbies",
			success : function(result) {
				console.log("aggiorna lobby");
				if(result == "updateLobby")
					location.reload(true);
			},
			error : function() {
				//call events again after some time
				setTimeout(function() {
					getEventsFromServer();
				}, 5000);
			}
		});
	}
	$(document).ready(getEventsFromServer());
	</script>
<title>Insert title here</title>
</head>
<body>
	<h1>MAIN PAGE</h1>

	<form action="mainPage/updateLobbies">
		<input type="submit" value="Aggiorna lobby">
	</form>
	
	<form action="userProfile">
		<input type="submit" value="Profile">
	</form>
	
		
	<form action="createLobby">
		<input type="text" name="lobbyTitle" value="lobby provoletta">
		<input type="submit" value="Crea Lobby">
	</form>
	<c:if test="${ not empty titleTaken}">
		<div class="alert alert-warning" role="alert">
			<strong>Warning!</strong> This title is already taken!
		</div>
	</c:if>

	<form action="joinLobby">
		<input type="text" name="lobbyTitle" value="lobby provoletta">
		<input type="submit" value="Entra nella Lobby">
	</form>
	
		<c:if test="${ not empty noLobby}">
		<div class="alert alert-warning" role="alert">
			<strong>Warning!</strong> This lobby is not in the server!
		</div>
	</c:if>
	<c:if test="${ not empty fullLobby}">
		<div class="alert alert-warning" role="alert">
			<strong>Warning!</strong> This lobby is full!
		</div>
	</c:if>
	
	<form action="exitMainPage">
		<input type="submit" value="Esci">
	</form>

	<c:forEach items="${lobbies}" var="lobby">
		<h2>${lobby.title} (${lobby.getNumberPlayersInside()}/${lobby.capacity})</h2>
	</c:forEach>
	
	<div id="lobbies"></div>
</body>
</html>