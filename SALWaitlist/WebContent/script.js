
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
				window.location.replace("cp-home.jsp");
			} else if (response === "student") {

				window.location.replace("student-home.jsp");
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

function loadProfile() {
	setTimeout(function() {
		var img = document.createElement("img"); 
		img.src = gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getImageUrl();
		var src = document.getElementById("profilePic"); 
		src.appendChild(img);
		document.getElementById("profileName").innerHTML = gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getName();
	}, 100);
}

function loadTable() {
	setTimeout(function() {
		$.ajax({
			type: "POST",
			url: "WaitlistServlet",
			data: {
				'email': gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail()
			},
			success: function(result) {
				console.log(result);
			}
		});
	}, 100);
}

function updateProfile() {	
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
				'email': gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail(),
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
					'email': gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail(),
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

function helpStudent(name, index) {
	var current = document.getElementById("current");
	
	if (document.getElementById("startCheck" + index).checked === true) {
		current.innerHTML = "You are currently helping " + name + ".";
		current.style.visibility = "visible";
		document.getElementById("finishCheck" + index).disabled = false;
	} else {
		current.style.visibility = "hidden";
		document.getElementById("finishCheck" + index).disabled = true;
	}
}

function removeStudent(email, name, index) {
	var confirmation = confirm("Are you done helping " + name + "?");
	if (confirmation) {
		$.ajax({
			type: "POST",
			url: "RemoveFromWaitlist",
			data: {
				'email': email
			},
			success: function() {
				document.getElementById("current").style.visibility = "hidden";
				document.getElementById("startCheck" + index).disabled = true;
				document.getElementById("finishCheck" + index).disabled = true;
			}
		});
	} else {
		document.getElementById("finishCheck" + index).checked = false;
	}
}
