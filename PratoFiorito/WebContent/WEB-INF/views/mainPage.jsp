<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="resources/scripts/mainPage.js"></script>

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

	<form action="exitMainPage">
		<input type="submit" value="Esci">
	</form>

	<h2>LOBBY PRESENTI NEL SERVER:</h2>
	<div id="lobbies">
		<c:forEach items="${lobbies}" var="lobby">
			<h3 style="cursor: pointer" onclick="joinLobby($(this).attr('title'))" id="${lobby.titleWithoutSpaces}" title="${lobby.title}">${lobby.title}
				(${lobby.getNumberPlayersInside()}/${lobby.capacity})</h3>
		</c:forEach>
	</div>
</body>
</html>