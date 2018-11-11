
function onSignIn(googleUser) {
	var googleAuth = gapi.auth2.getAuthInstance();
	var GoogleUser = googleAuth.currentUser.get();
	$.ajax({
		type: "POST",
		url: "LogInServlet",
		data: {
			'email': GoogleUser.getBasicProfile().getEmail(),
			'name': GoogleUser.getBasicProfile().getName(),
			'profpic': GoogleUser.getBasicProfile().getImageUrl()
		}, success: function(response) {
			if (response === "cp") {
				$.ajax({
					type: "POST",
					url: "cp-home.jsp",
					data: {
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'profpic': GoogleUser.getBasicProfile().getImageUrl()
					}, success: function() {
						window.location.replace("cp-home.jsp");
					}
				});
			} else if (response === "student") {
				$.ajax({
					type: "POST",
					url: "cp-home.jsp",
					data: {
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'profpic': GoogleUser.getBasicProfile().getImageUrl()
					}, success: function() {
						window.location.replace("student-home.jsp");
					}
				});
			} else if (response === "new") {
				$.ajax({
					type: "POST",
					url: "cp-home.jsp",
					data: {
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'profpic': GoogleUser.getBasicProfile().getImageUrl()
					}, success: function() {
						window.location.replace("profile.jsp");
					}
				});
			}
		}
	});
}
