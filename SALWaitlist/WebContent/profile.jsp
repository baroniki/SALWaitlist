<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
		<title>SAL Waitlist - Profile Page</title>
		
		<meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" 
        content="626626550106-ltfsre641scr41vef9gc0rfuo1pthdjv.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
        <script type="text/JavaScript" src="lib/js/signout.js"></script>

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

		<script type="text/javascript" src="script.js"></script>
		<script type="text/javascript" src="lib/styles/multiple-select.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" href="lib/styles/profile.css">
        <link rel="stylesheet" href="lib/styles/multiple-select.css" />
    </head>
    <body>
    	<%
    		String email = (String) request.getSession().getAttribute("email");
    		String name = (String) request.getSession().getAttribute("name");
    		String imgURL = (String) request.getSession().getAttribute("imgURL");	
    	%>
		
        <div class="container-fluid">
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

			<div class = "row">
				<div class = "col-md-3"></div>
				<div class = "col-md-6"  align = "center" id = "profile">
					<div id = "profilePic" align ="center">
						<span class="dot"></span>
						<img src="<%= imgURL %>">
					</div>
					<div id ="profileName" align = "center">
						<h1><%= name %></h1>
					</div>
					<label for="phoneNumber">Phone Number</label>
				  	<input id="phoneNumber" type="text" maxlength="10" pattern="\d{10}">
				  	<br>
					
					<label>Classes</label>
					<select id="classList" multiple="multiple">
						<option value="CSCI 103">CSCI 103</option>
						<option value="CSCI 104">CSCI 104</option>
						<option value="CSCI 109">CSCI 109</option>
						<option value="CSCI 170">CSCI 170</option>
						<option value="CSCI 201">CSCI 201</option>
						<option value="CSCI 270">CSCI 270</option>
						<option value="CSCI 310">CSCI 310</option>
						<option value="CSCI 350">CSCI 350</option>
						<option value="CSCI 353">CSCI 353</option>
						<option value="CSCI 356">CSCI 356</option>
						<option value="CSCI 360">CSCI 360</option>
					</select>
					<script>
						$('select').multipleSelect();
					</script>

					<div id="submit">
				  		<button type="button" id="submitButton" onclick="updateProfile('<%= email %>')">Add Changes</button>
					</div>
				</div>
				<div class = "col-md-3"></div>
			</div>
        </div>
    </body>
</html>
