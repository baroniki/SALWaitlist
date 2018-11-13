<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.slim.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" type="text/css" href="lib/styles/cp-home.css" />
    </head>
    <body>
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
		<h3>You are currently helping Glory.</h3>
		<div class="container">
            <table class="table table-striped">
	            <thead>
					<tr>
						<th>Student Name</th>
						<th>Email</th>
						<th>Average Time</th>
						<th>Started</th>
						<th>Finished</th>
					</tr>
	            </thead>
	            <tbody>
					<tr>
						<td>Daniel Kim</td>
						<td>kim321@usc.edu</td>
						<td>00:15</td>
						<td>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="tableDefaultCheck1">
								<label class="custom-control-label" for="tableDefaultCheck1"><span class="text-hide">Check</span></label>
							</div>
						</td>
						<td>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="tableDefaultCheck2">
								<label class="custom-control-label" for="tableDefaultCheck2"><span class="text-hide">Check</span></label>
							</div>
						</td>
					</tr>
					<tr>
						<td>Glory Kanes</td>
						<td>gkanes@usc.edu</td>
						<td>00:10</td>
						<td>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="tableDefaultCheck3">
								<label class="custom-control-label" for="tableDefaultCheck3"><span class="text-hide">Check</span></label>
							</div>
						</td>
						<td>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="tableDefaultCheck4">
								<label class="custom-control-label" for="tableDefaultCheck4"><span class="text-hide">Check</span></label>
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
    </body>
</html>