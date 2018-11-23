<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>User Profile</title>
<link href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css" rel="stylesheet" media="screen">  
<link href="css/user_profile.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="scripts/user_profile.js"></script>
</head>
<body>

<div class="container">
    <div class="row user-menu-container square">
        <div class="col-md-12 user-details">
            <div class="row coralbg white">
                <div class="col-md-7 no-pad">
                    <div class="user-pad">
                        <h3>Welcome back, <strong>${user}</strong></h3>
                        <h4 class="white"><i class="fa fa-check-circle-o"></i> First Name</h4>
						<h4 class="white"><i class="fa fa-check-circle-o"></i> Last Name</h4>
                        <h5 class="white"><i class="fa fa-twitter"></i> Country</h5>
                    </div>
                </div>
                <div class="col-md-5 no-pad">
                    <div class="user-image">
                        <img src="https://farm7.staticflickr.com/6163/6195546981_200e87ddaf_b.jpg" class="img-responsive thumbnail">
                    </div>
                </div>
            </div>
            <div class="row overview">
                <div class="col-md-3 user-pad text-center">
                    <h3>GAMES PLAYED</h3>
                    <h4>2,784</h4>
                </div>
                <div class="col-md-3 user-pad text-center">
                    <h3>GAMES WON</h3>
                    <h4>4,901</h4>
                </div>
                <div class="col-md-3 user-pad text-center">
                    <h3>GAMES LOST</h3>
                    <h4>456</h4>
                </div>
                <div class="col-md-3 user-pad text-center">
                    <h3>GAMES ABANDONED</h3>
                    <h4>3</h4>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>