<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>LOBBY PAGE ${ lobbyTitle } sono ${ playerType }</h1>

	<h2>Dimensione prato</h2>
	<form action="startGame">
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

	<form action="exitLobby">
		<input type="hidden" name="playerType" value="${ playerType }">
		<input type="hidden" name="lobbyTitle" value="${ lobbyTitle }">
		<input type="submit" value="Esci dalla Lobby">
	</form>
</body>
</html>