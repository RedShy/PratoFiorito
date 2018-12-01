<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/registration.css">
<script src="resources/scripts/registration.js"></script>
</head>
<body>
	<form action="register">
		<div class="container">
			<h1>Registrazione utente</h1>

			<hr />

			<label for="email"> <b>Username: </b>
			</label> <input style="margin-bottom: 0px;" id="username" type="text"
				placeholder="Inserisci username" name="username" required />
			<div id="useralreadyIn"
				style="color: red; margin-top: 0px; display: none;">Attenzione
				l'username è stato già utilizzato</div>
			<hr />
			<label for="psw"> <b>Password</b>
			</label> <input type="password" placeholder="inserisci password"
				name="password" required> <label for="psw-repeat"><b>Ripeti
					Password</b></label> <input type="password" placeholder="Repeti Password"
				name="psw-repeat" required>
			<hr>

			<button id="submit" type="submit" class="registerbtn">Registrazione</button>

			Hai già un account ?<a href="javascript:history.go(-1)"
				onMouseOver="self.status=document.referrer;return true">Torna al Login</a>
		
			
		</div>

		<div class="container signin"></div>


	</form>

</body>
</html>