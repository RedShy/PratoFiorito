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

<body background=”resources/images/minesweeper.png”>
	<link rel="stylesheet" href="resources/css/login.css">
	<div class="container">
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
				<span> <img src="resources/images/minesweeper.png"
					class="w-75" alt="Logo">
				</span><br />
				<!--            <h1>-->
				<?php //echo $message?>
				<!--</h1>-->

			</div>
			<div class="card-body">
				<form action="createLobby">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-bomb"></i></span>
						</div>
						<input type="text" name="lobbyTitle" class="form-control"
							placeholder="Username" required>
					</div>



					<div class="form-group">
						<input type="submit" value="Crea lobby"
							class="btn btn-outline-danger float-right login_btn">
						<c:if test="${ not empty titleTaken}">
							<div class="alert alert-warning" role="alert">
								<strong>Warning!</strong> This title is already taken!
							</div>
						</c:if>
				</form>



			</div>

			<form action="exitMainPage">

				<div class="form-group">
					<input type="submit" value="Esci"
						class="btn btn-outline-danger float-left login_btn">
			</form>
			<form action="userProfile">

				<div class="form-group">
					<input type="submit" value="Profilo"
						class="btn btn-outline-danger float-center login_btn">
			</form>
		</div>
		
		
		
		
	<div id="lobbies">
		<c:forEach items="${lobbies}" var="lobby">
			<h3 style="cursor: pointer" onclick="joinLobby($(this).attr('title'))" id="${lobby.titleWithoutSpaces}" title="${lobby.title}">${lobby.title}
				(${lobby.getNumberPlayersInside()}/${lobby.capacity})</h3>
		</c:forEach>
	</div>
		
		
		
	</div>
	</div>