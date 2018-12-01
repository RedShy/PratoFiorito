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
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">

<link rel="stylesheet" href="resources/css/lobby.css">

</head>
<body>

	

	<div class="container">
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
			
			<table class="table table-dark">
			  <tbody>
			    <tr>
			      <td valign="middle" align="center">
			      
			      	<div id="lobbyTitle">${ lobby.title } </div>
			      	<c:if test="${ playerType eq 'host'}">
				      	<div id="difficulty">
					      	<form action="startGame">
								<input type="radio" name="difficulty" value="beginner" checked>
								 Facile <input type="radio" name="difficulty"
									value="intermediate"> Medio <input type="radio"
									name="difficulty" value="advanced"> Difficile
									<input class="btn btn-outline-danger float-left login_btn" type="submit" value="Start Game"> 
							</form>
							<c:if test="${ not empty noGuest}">
								<div class="alert alert-warning" role="alert">
									<strong>Warning!</strong> You cannot start without a guest!
								</div>
							</c:if>
				      	</div>
			      	</c:if>
			      </td>
			      <td><img src="resources/images/minesweeper.png" class="w-50" alt="Logo"></td>

			    </tr>
			  </tbody>
			</table>
	
			</div>
			<div class="card-body">
				HOST: ${lobby.host } GUEST:
				<c:choose>
					<c:when test="${empty lobby.guest}">
					EMPTY
				</c:when>
					<c:otherwise>
					${lobby.guest}
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>



</body>
</html>