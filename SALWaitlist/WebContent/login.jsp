<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>SAL Waitlist - Log In</title>

        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" 
        content="626626550106-ltfsre641scr41vef9gc0rfuo1pthdjv.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>

        <script type="text/JavaScript" src="lib/jquery/jquery-3.3.1.slim.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
        <script type="text/JavaScript" src="lib/bootstrap/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="lib/styles/header.css" />
        <link rel="stylesheet" type="text/css" href="lib/styles/login.css" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 center-block text-center mt-5">
                    <h1>SAL Waitlist</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 center-block text-center">
                    <img id="login-logo" src="lib/images/201logo.png">
                </div>
            </div>
            <div class="row mb-2">
                <div class="g-signin2" data-width="194" data-height="48" data-onsuccess="onSignIn" data-longtitle="true">button</div>
            </div>
            <div class="row">
                <div class="col-md-12 center-block text-center">
                    <button type="button" class="btn btn-lg guest-btn">Sign in as Guest</button>
                </div>
            </div>
        </div>
    </body>
</html>