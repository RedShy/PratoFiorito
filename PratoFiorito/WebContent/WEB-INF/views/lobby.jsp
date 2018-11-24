<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 
Bisogna capire quando l'host ha deciso di avviare il gioco quindi facco un pool di  1 secondo
il server controller� l'Oggetto game se � null o meno se non � null vorr� dire che posso iniziare
quindi faccio un redirect verso la pagina game
-->

<script>
	function canStart() {

		$.ajax({
			url : "canStart",
			success : function(result) {
				if(result.trim()=="canStart"){
					alert("Can start");
					//window.location.replace("/game");
					  location.href = 'game';
				}else{
					console.log("notYet");
				}
				setTimeout(function() {
					canStart();
					
				}, 1000);
			},
			error : function() {
				//call events again after some time
				setTimeout(function() {
					canStart();
				}, 1000);
			}
		});

	}

	$(document).ready(canStart());
</script>




<title>Game</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	function getEventsFromServer() {
		console.log("DOCUMENTO PRONTO!");
		$.ajax({
			url : "getEvents",
			success : function(result) {
				// 				window.location.href = 'game';
				location.reload(true);
				getEventsFromServer();
			},
			error : function(xhr, status, error) {
				console.log("ERRORE");
				console.log(xhr.responseText);
				console.log(JSON.parse(xhr.responseText));
				//call events again after some time
				setTimeout(function() {
					getEventsFromServer();
				}, 500);
			}
		});
	}
	$(document).ready(getEventsFromServer());
</script>

<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	function getEventsFromServer() {
		console.log("DOCUMENTO PRONTO!");
		$.ajax({
			url : "getEvents",
			success : function(result) {
				// 				window.location.href = 'game';
				location.reload(true);
				getEventsFromServer();
			},
			error : function(xhr, status, error) {
				console.log("ERRORE");
				console.log(xhr.responseText);
				console.log(JSON.parse(xhr.responseText));
				//call events again after some time
				setTimeout(function() {
					getEventsFromServer();
				}, 500);
			}
		});
	}
	$(document).ready(getEventsFromServer());
</script>

<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	function getEventsFromServer() {
		console.log("DOCUMENTO PRONTO!");
		$.ajax({
			url : "getEvents",
			success : function(result) {
				// 				window.location.href = 'game';
				location.reload(true);
				getEventsFromServer();
			},
			error : function(xhr, status, error) {
				console.log("ERRORE");
				console.log(xhr.responseText);
				console.log(JSON.parse(xhr.responseText));
				//call events again after some time
				setTimeout(function() {
					getEventsFromServer();
				}, 500);
			}
		});
	}
	$(document).ready(getEventsFromServer());
</script>

<title>Insert title here</title>
</head>
<body>
	<h1>Room ${ lobby.title } 
		<c:choose>
		  <c:when test="${ lobby.host eq user}">
		    Ho creato la stanza..
		  </c:when>
		  <c:otherwise>
		    Sono un ospite
		  </c:otherwise>
		</c:choose>
	</h1>
	
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