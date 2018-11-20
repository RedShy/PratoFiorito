<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>MAIN PAGE</h1>

<form action="mainPage/updateLobbies">
  <input type="submit" value="Aggiona lobby">
</form> 

<form action="createLobby">
	<input type="text" name="lobbyTitle" value="lobby provoletta" >
  <input type="submit" value="Crea Lobby">
</form>

<form action="joinLobby">
	<input type="text" name="lobbyTitle" value="lobby provoletta" >
  <input type="submit" value="Entra nella Lobby">
</form>

<c:forEach items="${lobbies}" var="lobby">
	<h2>${lobby.title}</h2>
</c:forEach>
</body>
</html>