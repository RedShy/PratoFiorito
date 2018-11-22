<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>

<script>
	function getEventsFromServer() {
		console.log("DOCUMENTO PRONTO!");
		$.ajax({
			url : "getEvents",
			success : function(result) 
			{
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

<body>
	<h1>
		GAME PAGE
		<c:choose>
			<c:when test="${gameEnded eq 'lose'}">
				<p style="color: Red;">HAI PERSO!</p>
			</c:when>
			<c:when test="${gameEnded eq 'win'}">
				<p style="color: Green;">HAI VINTO!</p>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</h1>
	<h2>Stato interno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ game.getSize() - 1 }">
					<td><c:choose>
							<c:when test="${game.getCell(i,j)=='1'}">
								<img src="resources/bomb.JPG">
							</c:when>
							<c:otherwise>
								<img src="resources/${game.getCell(i,j) }.JPG">
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<h2>Stato esterno gioco</h2>
	<table style="width: 20%">
		<c:forEach var="i" begin="0" end="${ game.getSize() - 1}">
			<tr>
				<c:forEach var="j" begin="0" end="${ game.getSize() - 1 }">
					<td><img src="resources/${game.getDisplayCell(i,j) }.JPG"></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${game.turn eq playerType and empty gameEnded}">
		<table style="width: 20%">
			<tr>
				<td><form action="clickLeft" accept-charset=utf-8>
						<input type="text" name="x" value="1"> <input type="text"
							name="y" value="1"> <input type="submit"
							value="Clicca su cella">
					</form></td>
			</tr>
		</table>

		<table style="width: 20%">
			<tr>
				<td><form action="clickRight" accept-charset=utf-8>
						<input type="text" name="x" value="1"> <input type="text"
							name="y" value="1"> <input type="submit"
							value="Piazza bandierina su cella">
					</form></td>
			</tr>
		</table>
	</c:if>

	<form action="exitGame">
		<input type="submit" value="Esci dal gioco">
	</form>
</body>
</html>