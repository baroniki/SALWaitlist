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
                <div class="col-md-2 header-img">
                    <a href="student-home.jsp"><img id="logo" src="lib/images/201logo.png"></a>
                </div>
                <div class="col-md-1 offset-7 header-el">
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
                <div class="classbox-entry classbox-selected">
                    CSCI 270
                </div>
                <div class="classbox-unselected">
	                <div class="classbox-entry">
	                    CSCI 104
	                </div>
	                <div class="classbox-entry">
	                    CSCI 170
	                </div>
                </div>
            </div>
        </div>
        <div class="container">
            
        </div>

        <script type="text/JavaScript">
            $(".classbox-dropdown").click(() => {
                $(".classbox-unselected").slideToggle();
            });
        </script>
    </body>
</html>