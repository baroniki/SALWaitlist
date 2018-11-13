
function onSignIn(googleUser) {
	var googleAuth = gapi.auth2.getAuthInstance();
	var GoogleUser = googleAuth.currentUser.get();
	$.ajax({
		type: "POST",
		url: "LogInServlet",
		data: {
			'action': "authenticate",
			'email': GoogleUser.getBasicProfile().getEmail(),
			'name': GoogleUser.getBasicProfile().getName(),
			'imgURL': GoogleUser.getBasicProfile().getImageUrl()
		},
		success: function(response) {
			if (response === "cp") {
				$.ajax({
					type: "POST",
					url: "LogInServlet",
					data: {
						'action': "redirect",
						'nextPage': "/cp-home.jsp",
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'imgURL': GoogleUser.getBasicProfile().getImageUrl()
					},
					success: function() {
						window.location.replace("cp-home.jsp");
					}
				});
			} else if (response === "student") {
				$.ajax({
					type: "POST",
					url: "LogInServlet",
					data: {
						'action': "redirect",
						'nextPage': "/student-home.jsp",
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'imgURL': GoogleUser.getBasicProfile().getImageUrl()
					},
					success: function() {
						window.location.replace("student-home.jsp");
					}
				});
			} else if (response === "new") {
				$.ajax({
					type: "POST",
					url: "LogInServlet",
					data: {
						'action': "redirect",
						'nextPage': "/profile.jsp",
						'email': GoogleUser.getBasicProfile().getEmail(),
						'name': GoogleUser.getBasicProfile().getName(),
						'imgURL': GoogleUser.getBasicProfile().getImageUrl()
					},
					success: function() {
						window.location.replace("profile.jsp");
					}
				});
			}
		}
	});
}

function updateProfile(email) {	
	var phoneNumber = document.getElementById("phoneNumber").value;
	
	if (phoneNumber === "") {
		var checked = [];
		$("input:checkbox:checked").each(function(){
		    checked.push($(this).val());
		});
		
		$.ajax({
			type: "POST",
			url: "ProfileServlet",
			data: {
				'email': email,
				'phoneNumber': phoneNumber,
				'classes': JSON.stringify(checked)
			},
			success: function() {
				alert("Classes successfully updated!");
			}
		});
	} else {
		var format = /^\d{10}$/;
		if (phoneNumber.match(format)) {
			var checked = [];
			$("input:checkbox:checked").each(function(){
			    checked.push($(this).val());
			});
			
			$.ajax({
				type: "POST",
				url: "ProfileServlet",
				data: {
					'email': email,
					'phoneNumber': phoneNumber,
					'classes': JSON.stringify(checked)
				},
				success: function() {
					alert("Phone number and classes successfully updated!");
				}
			});
		} else {
			alert("Invalid phone number. Please enter 10 digits.");
		}
	}
}
