<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<!------ Include the above in your HEAD tag ---------->
<meta charset="UTF-8">
<body background=”resources/images/minesweeper.png”>
	
	<div class="container">
		<div class="card card-login mx-auto text-center bg-dark">
			<div class="card-header mx-auto bg-dark">
				<span> <img src="resources/images/minesweeper.png"
					class="w-75" alt="Logo">
				</span>
			</div>
			<div class="card-body">
				<form action="register">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="name" class="form-control"
							placeholder="Nome" required>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="surname" class="form-control"
							placeholder="Cognome" required>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-city"></i></span>
						</div>
						<input type="text" name="city" class="form-control"
							placeholder="Citt&agrave" required>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="username" class="form-control"
							placeholder="Username" required>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="text" name="password" class="form-control"
							placeholder="Password" required>
					</div>
					<div class="form-group">
						<input type="submit" value="Registrazione"
							class="btn btn-outline-danger float-right login_btn">
					</div>
				</form>
			</div>
			<div style="display: none" id="alert" class="alert alert-warning"
				role="alert">
				<strong>Attenzione!</strong> Username non disponibile.


			</div>
			<span style = "color:white">Hai gi&agrave un account?      <a href="javascript:history.go(-1)"
				onMouseOver="self.status=document.referrer;return true"><br>Torna al
				Login</a></span>
		</div>

	</div>