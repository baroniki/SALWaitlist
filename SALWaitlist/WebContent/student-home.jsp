<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>SAL Waitlist - Home</title>
        
        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.min.js"></script>
        
        <script type="text/JavaScript" src="lib/js/student-home.js"></script>

        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" 
        content="626626550106-ltfsre641scr41vef9gc0rfuo1pthdjv.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
        <script type="text/JavaScript" src="lib/js/signout.js"></script>

        
        
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
            <div class="classinfo-cont mb-4">
                <h1 id="classinfo-name">Class</h1>
                <h3 id="classinfo-cp">Current CP:</h3>
            </div>

            <div class="waittime-cont mb-5">
                <div class="waittime-time">There are currently <span id="people" class="time">5</span> people waiting.</div>
                <h5 id="waittime-msg">Estimated wait time: <span id="time" class="time">00:00</span> min</h5>
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

    </body>
</html>