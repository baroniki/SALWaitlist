<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>SAL Waitlist - Profile Page</title>

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.slim.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" href="lib/styles/profile.css">
    </head>
    <body>
    	<script>
    		function profileInfo(){
    			
    		}
    	
    	</script>
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
					</div>
					<div id ="profileName" align = "center">
						<h1>Glory Kanes</h1>
					</div>
					<label for="phoneNumber">Phone Number </label>
				  	<input id = "phoneNumber" type="text" name="event">	
				  	<br>
				  	<div class="dropdown">
				  		<label for="choose">Choose your classes: </label>
				  		 <!-- <p style="text-indent :5em;" > </p> -->
						<button onclick="myFunction()" id = "choose"class="dropbtn">Classes</button>
  						<div id="myDropdown" class="dropdown-content">
    						<input type="text" placeholder="Search.." id="myInput" onkeyup="filterFunction()">
							    <a href="#about">CS 103</a>
							    <a href="#base">CS 109</a>
							    <a href="#blog">CS 104</a>
							    <a href="#contact">CS 170</a>
							    <a href="#custom">CS 201</a>
							    <a href="#support">CS 270</a>
							    <a href="#tools">CS 310</a>
  						</div>
					</div>

					<script>
					/* When the user clicks on the button,
					toggle between hiding and showing the dropdown content */
					function myFunction() {
					    document.getElementById("myDropdown").classList.toggle("show");
					}
					
					function filterFunction() {
					    var input, filter, ul, li, a, i;
					    input = document.getElementById("myInput");
					    filter = input.value.toUpperCase();
					    div = document.getElementById("myDropdown");
					    a = div.getElementsByTagName("a");
					    for (i = 0; i < a.length; i++) {
					        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
					            a[i].style.display = "";
					        } else {
					            a[i].style.display = "none";
					        }
					    }
					}
					</script>
				  <div id = "submit">
				  	<input id = "submitButton"  type="button" value="Add Changes" onclick = "profileInfo();">
				  </div>
				</div>
				<div class = "col-md-3"></div>
			</div>
        </div>
    </body>
</html>