<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="SALWaitlist.Student" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
		<title>SAL Waitlist - CP Home</title>
		
		<meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" 
        content="626626550106-ltfsre641scr41vef9gc0rfuo1pthdjv.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
        <script type="text/JavaScript" src="lib/js/signout.js"></script>

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

		<script type="text/javascript" src="script.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" type="text/css" href="lib/styles/cp-home.css" />
    </head>
    <body onload="loadTable()">
        <div class="container-fluid">
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
		
		<!-- Page Content Goes Here -->
		<h3 id="current" style="height:33px; visibility:hidden"></h3>
		<div class="container">
			<table id="table" class="table table-striped">
				<thead>
					<tr>
						<th>Student Name</th>
						<th>Email</th>
						<th>Phone Number</th>
						<th>Started</th>
						<th>Finished</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
    </body>
</html>
