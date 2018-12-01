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
				<form action="login">
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
						<input type="password" name="password" class="form-control"
							placeholder="Password" required>
					</div>

					<div class="form-group">
						<input type="submit" value="Accedi"
							class="btn btn-outline-danger float-right login_btn">
				</form>



			</div>

			<form action="registration">

				<div class="form-group">
					<input type="submit" value="Registrazione"
						class="btn btn-outline-danger float-left login_btn">
			</form>
		</div>
	</div>
	</div>