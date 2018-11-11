$(".classbox-dropdown").click(() => {
    $(".classbox-unselected").slideToggle();
});
$("#signin-redirect").click(() => {
    window.location.replace("login.jsp");
});
$("#add-button").click(() => {
    //Send post request to add to waitlist
    $("#waittime-msg").html("Your current wait time:");
});

$(document).ready(() => {
    $(document).on('click', '.classbox-entry', (el) => {
        const classToSelect = $(el.target).html();
        if(classToSelect !== classes[0]) {
            selectClass(classes, getClassIndex(classes, classToSelect));
        }
    });

    const status = checkGuestOrStudent();
    
    var classes = getClasses(); //element 0 is always the selected class

    //if(status === 'student') 
        classes = getStudentClasses()

    updateClassDropdown(classes);

    let time = getWaitTime(classes[0]);
    updateTimeSpan(time);
    //Countdown for waittime every minute
    var timeCounter = turnOnTimeCounter(time);
    
    $.post('Calculate_Wait_Time', 		
    {
    	email : 'baroniki@usc.edu',
    	classid : classes[0]
    }, (response) => {
    	console.log(response);
    });
});

function turnOnTimeCounter(time) {
    setInterval(() => {
        if(time.min != 0) {
            time.min--;
        }
        else if(time.hr != 0) {
            time.hr--;
            time.min = 59;
        }
        updateTimeSpan(time);
    }, 5000);
}

function updateClassDropdown(classes) {
    let selectedClass = $('<div class=\"classbox-entry classbox-selected\"></div>');
    selectedClass.html(classes[0]);

    $(".classbox").html(selectedClass);
    
    const unselectedClasses = $('<div class=\"classbox-unselected\"></div>');
    for(let i = 1; i < classes.length; i++) {
        let classElement = $('<div class=\"classbox-entry\"></div>');
        classElement.html(classes[i]);
        unselectedClasses.append(classElement);
    }

    $(".classbox").append(unselectedClasses);
}

function updateTimeSpan(time) {
    let timeSpan = $("#time");
    
    let timeHour = time.hr, timeMin = time.min;
    if(time.hr < 10)
        timeHour = "0" + time.hr;
    if(time.min < 10)
        timeMin = "0" + time.min;

    timeSpan.html(timeHour + ":" + timeMin);
}

//Calculates wait time for given class
function getWaitTime(className) {
    const waitTime = 
    {
        hr : 1,
        min : 16
    };

    return waitTime;
}

//Get all classes for guest to see
function getClasses() {
    const classes = ["CSCI 270", "CSCI 104", "CSCI 170", "CSCI 360"];

    return classes;
}

//Get all of student's classes
function getStudentClasses() {
    let classes;
    
    $.ajax({
        type : 'POST',
        url : 'Student_Classes', 
        data : 
        {
            email : 'baroniki@usc.edu'
        }, 
        success : 
        (response) => {
            classes = response.split('&');
        },
        async : false
    });

    return classes;
}

function selectClass(classes, classIndex) {
    const temp = classes[0];
    classes[0] = classes[classIndex];
    classes[classIndex] = temp;

    updateClassDropdown(classes);
}

function getClassIndex(classes, className) {
    for(let i = 0; i < classes.length; i++) {
        if(classes[i] === className) return i;
    }

    return -1;
}

function checkGuestOrStudent() {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');

    if(status === "student") {
        $("#guest-message").hide();
        $("#add-message").show();
    }
    else {
        $("#guest-message").show();
        $("#add-message").hide();
    }

    return status;
}