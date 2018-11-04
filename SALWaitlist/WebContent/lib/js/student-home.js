$(".classbox-dropdown").click(() => {
    $(".classbox-unselected").slideToggle();
});
$("#signin-redirect").click(() => {
    window.location.replace("login.jsp");
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

    if(status === 'student') 
        classes = getStudentClasses()

    updateClassDropdown(classes);

    let time = getWaitTime(classes[0]);
    //Countdown for waittime every minute
    setInterval(() => {
        $("#time").html(time);
        time = time-1;
    }, 60000);

    //Check for updates in waittime every 10 seconds
    setInterval(() => {
        const waittime = getWaitTime(classes[0]);
        if(waittime < time) {
            time = waittime;
        }
    }, 10000);
});

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

//Calculates wait time for given class
function getWaitTime(className) {
    const waitTime = 30;

    return 30;
}

//Get all classes for guest to see
function getClasses() {
    const classes = ["CSCI 270", "CSCI 104", "CSCI 170", "CSCI 360"];

    return classes;
}

//Get all of student's classes
function getStudentClasses() {
    const classes = ["CSCI 104", "CSCI 170"];

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

