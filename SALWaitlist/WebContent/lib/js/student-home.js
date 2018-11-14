const CP = 
{
    'CSCI 103' : 'Alex Baronikian',
    'CSCI 104' : 'Daniel Kim',
    'CSCI 109' : 'Brighton Balfrey',
    'CSCI 170' : 'Aahaad Patel',
    'CSCI 201' : 'Glory Kanes',
    'CSCI 270' : 'Alex Baronikian',
    'CSCI 310' : 'Daniel Kim',
    'CSCI 350' : 'Brighton Balfrey',
    'CSCI 353' : 'Aahaad Patel',
    'CSCI 356' : 'Glory Kanes',
    'CSCI 360' : 'Alex Baronikian'
};
var classes;
var userEmail = "";

$(document).ready(() => {
    $.post('UserSession', 
    {
        action: "get"
    }, (data) => {
        userEmail = data;
        initPage();
    });
});
function initPage() {
	$(".classbox-dropdown").click(() => {
	    $(".classbox-unselected").slideToggle();
	});
	$("#signin-redirect").click(() => {
	    window.location.replace("login.jsp");
	});
	$("#add-button").click(() => {
	    //Send post request to add to waitlist
		$.post('AddToWaitlist', 
		{
			email : userEmail,
			classid : classes[0]
		});
	    console.log("added");
	});
	
    
    $(document).on('click', '.classbox-entry', (el) => {
        const classToSelect = $(el.target).html();
        if(classToSelect !== classes[0]) {
            selectClass(classes, getClassIndex(classes, classToSelect));
            clearInterval(counter);
            counter = updateCounters(classes);
        }
    });

    const status = checkGuestOrStudent();
    
    classes = getClasses(); //element 0 is always the selected class

    if(status !== 'guest') 
        classes = getStudentClasses();
        

    updateClassDropdown(classes);
    var counter = updateCounters(classes);
}

function updateCounters(classes) {
    let peopleAhead = getWaitTime(classes[0])
    let time = minsToTimeObject(6*peopleAhead);

    updatePeopleAheadSpan(peopleAhead);
    updateTimeSpan(time);
    //Countdown for waittime every minute
    return turnOnTimeCounter(time);
}

function turnOnTimeCounter(time) {
    let counter = setInterval(() => {
        if(time.min != 0) {
            time.min--;
        }
        else if(time.hr != 0) {
            time.hr--;
            time.min = 59;
        }
        updateTimeSpan(time);
    }, 5000);

    return counter;
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

function minsToTimeObject(mins) {
    let hrs = 0;
    while(mins >= 60) {
        mins -= 60;
        hrs += 1;
    }

    let time = 
    {
        hr : hrs,
        min: mins
    };

    return time;
}

function updatePeopleAheadSpan(peopleAhead) {
    $("#people").html(peopleAhead);
}

//Calculates wait time for given class
function getWaitTime(className) {
    let waitTime = 0;
    
    $.ajax({
        type : 'POST',
        url : 'Calculate_Wait_Time', 
        data : 
        {
            classid : className
        }, 
        success : 
        (response) => {
            waitTime = response;
        },
        async : false
    });

    return waitTime;
}

//Get all classes for guest to see
function getClasses() {
    const classes = ['CSCI 103', 'CSCI 104', 'CSCI 109', 'CSCI 170', 'CSCI 201', 'CSCI 270', 'CSCI 310', 'CSCI 350', 'CSCI 353', 'CSCI 360'];

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
            email : userEmail
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

    $(".classinfo-name").html(classes[0]);
    $(".classinfo-cp").html(CP[classes[0]]);

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

    if(status !== "guest") {
        $("#guest-message").hide();
        $("#add-message").show();
    }
    else {
        $("#guest-message").show();
        $("#add-message").hide();
    }

    return status;
}