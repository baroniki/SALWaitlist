<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>SAL Waitlist - Home</title>

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" type="text/css" href="lib/styles/student-home.css" />
    </head>
    <body>
        <div class="container-fluid mb-3">
            <header class="row">
                <div class="col-md-1 header-img">
                    <a href="student-home.jsp"><img id="logo" src="lib/images/201logo.png"></a>
                </div>
                <div class="col-md-3 header-el">
                	<a href="student-home.jsp"><h3>SAL Waitlist</h3></a>
                </div>
                
                <div class="col-md-1 offset-5 header-el">
                    <a href="profile.jsp"><h3>Profile</h3></a>
                </div>
                <div id="header-signout" class="col-md-2 header-el">
                    <a id="sign-out" href="javascript:signOut()"><h3>Sign Out</h3></a>
                </div>
            </header>
        </div>
        <div class="classbox-container">
            <div class="classbox-dropdown">
                <h3>My Classes</h3>
            </div>
            
            <div class="classbox">
                
            </div>
        </div>
        <div class="container">
            <div class="classinfo-cont mb-5">
                <h1 id="classinfo-name">CSCI 270</h1>
                <h3 id="classinfo-cp">Current CP: Alex Baronikian</h3>
            </div>

            <div class="waittime-cont">
                <h5 id="waittime-msg">Current class wait time:</h5>
                <div class="waittime-time"><span id="time">30</span> min</div>
            </div>

            <div id="guest-message">
                <h4>Sign in to add yourself to the waitlist.</h4>
                <button type="button" id="signin-redirect" class="btn btn-lg guest-btn">Sign in</button>
            </div>
            
            <div id="add-message">
            	<h4>Would you like to add yourself to the waitlist?</h4>
            	<button type="button" id="add-button" class="btn btn-lg guest-btn">Add</button>
            </div>
        </div>

        <script type="text/JavaScript" src="lib/js/student-home.js"></script>
    </body>
</html>