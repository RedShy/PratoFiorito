<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>User Profile</title>
<link
	href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/user_profile.css" />" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/scripts/user_profile.js"/>"></script>
</head>
<body>

	<div class="container">
		<div class="row user-menu-container square">
			<div class="col-md-12 user-details">
				<div class="row coralbg white">
					<div class="col-md-7 no-pad">
						<div class="user-pad">
							<h3>
								Welcome back, <strong>${user}</strong>
							</h3>
							<h4 class="white">
								<i class="fa fa-check-circle-o"></i> ${first_name}
							</h4>
							<h4 class="white">
								<i class="fa fa-check-circle-o"></i> ${last_name}
							</h4>
							<h5 class="white">
								<i class="fa fa-twitter"></i> ${country}
							</h5>
						</div>
					</div>
					<div class="col-md-5">
						<div class="user-image">
							<img
								src="https://www.dacia.co.uk/etc/designs/dacia_v3/18.13.1.RENAULT-7/common-assets/img/avatar/avatar.png"
								class="img-responsive thumbnail">
						</div>
					</div>
				</div>
				<div class="row overview">
					<div class="col-md-3 user-pad text-center">
						<h3>GAMES PLAYED</h3>
						<h4>${games_played}</h4>
					</div>
					<div class="col-md-3 user-pad text-center">
						<h3>GAMES WON</h3>
						<h4>${games_won}</h4>
					</div>
					<div class="col-md-3 user-pad text-center">
						<h3>GAMES LOST</h3>
						<h4>${games_lost}</h4>
					</div>
					<div class="col-md-3 user-pad text-center">
						<h3>GAMES ABANDONED</h3>
						<h4>${games_abandoned}</h4>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row user-menu-container square">
			<div class="col-md-12 user-details">
				<div class="no-pad text-center"><h3 id="leaderboard"><strong>LEADERBOARD</strong></h3></div>
				<div class="btn-pref btn-group btn-group-justified btn-group-lg"
					role="group" aria-label="...">
					<div class="btn-group" role="group">
						<button type="button" id="beginner" class="btn btn-success"
							href="#tab1" data-toggle="tab">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
							<div class="hidden-xs">Beginner</div>
						</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" id="intermediate"
							class="btn btn-default btn-int" href="#tab2" data-toggle="tab">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span><span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							<div class="hidden-xs">Intermediate</div>
						</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" id="advanced"
							class="btn btn-default btn-adv" href="#tab3" data-toggle="tab">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span><span
								class="glyphicon glyphicon-star" aria-hidden="true"></span><span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							<div class="hidden-xs">Advanced</div>
						</button>
					</div>
				</div>

				<div class="well">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="tab1">
							<table class="table table-hover-beg table-bordered">
								<thead class="thead-beg">
									<tr>
										<th scope="col">Date</th>
										<th scope="col">Played with</th>
										<th scope="col">Game time</th>
										<th scope="col">Result</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty matches}">
										<div>MATCH VUOTI ${matches }</div>
									</c:if>
									<c:forEach var="match" items="${matches}" varStatus="status">
										<c:if test="${match.difficulty == 1}">
											<tr>
												<td>${match.date}</td>
												<td>
														
														<c:if test="${match.owner.username != user}">
      														${match.owner.username}
      													</c:if>
      													<c:if test="${match.teammate != user}">
      														${match.teammate}
      													</c:if>
													</td>
												<td>${match.matchTime}</td>
												<td>${match.result}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane fade in" id="tab2">
							<table class="table table-hover-int table-bordered">
								<thead class="thead-int">
									<tr>
										<th scope="col">Date</th>
										<th scope="col">Played with</th>
										<th scope="col">Game time</th>
										<th scope="col">Result</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty matches}">
										<div>MATCH VUOTI ${matches }</div>
									</c:if>
									<c:forEach var="match" items="${matches}" varStatus="status">
										<c:if test="${match.difficulty == 2}">
											<tr>
												<td>${match.date}</td>
												<td>
														<c:if test="${match.owner.username != user}">
    														${match.owner.username}
    													</c:if>
    													<c:if test="${match.teammate != user}">
    														${match.teammate}
    													</c:if>
												
												</td>
												<td>${match.matchTime}</td>
												<td>${match.result}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane fade in" id="tab3">
							<table class="table table-hover-adv table-bordered">
								<thead class="thead-adv">
									<tr>
										<th scope="col">Date</th>
										<th scope="col">Played with</th>
										<th scope="col">Game time</th>
										<th scope="col">Result</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty matches}">
										<div>MATCH VUOTI ${matches }</div>
									</c:if>
									<c:forEach var="match" items="${matches}" varStatus="status">
										<c:if test="${match.difficulty == 3}">
											<tr>
												<td>${match.date}</td>
												<td>
														<c:if test="${match.owner.username != user}">
      														${match.owner.username}
      													</c:if>
      													<c:if test="${match.teammate != user}">
      														${match.teammate}
      													</c:if>
      											</td>
												<td>${match.matchTime}</td>
												<td>${match.result}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>