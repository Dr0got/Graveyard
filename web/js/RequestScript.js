//Registration
function setNewUser(){
    let req = new XMLHttpRequest();
    req.open('POST', 'RegistrationServlet', true);
    req.setRequestHeader('login', document.getElementById("userLogin").value);
    req.setRequestHeader('password', document.getElementById("userPassword").value);
    req.setRequestHeader('full_name', document.getElementById("username").value + " " + document.getElementById("usersname").value);
    req.setRequestHeader('passport_number', document.getElementById("userpassport").value);
    req.setRequestHeader('phone', document.getElementById("userphone").value);
    req.setRequestHeader('email', document.getElementById("useremail").value);
    req.responseType = 'text';

    //When there is a response from the server
    req.onload = function() {
        let str = req.response; // get the string from the response
        (str == "Exception") ? throwException("Error in registration") : setCookie("user", str);
    };

    req.send(null);
}

//Authorization
function setOldUser(){
    let req = new XMLHttpRequest();
    req.open('POST', 'AuthorizationServlet', true);
    req.setRequestHeader('login', document.getElementById("userLogin").value);
    req.setRequestHeader('password', document.getElementById("userPassword").value);
    req.setRequestHeader('mode', ((document.getElementById("isAdmin")).checked)?"admin":"user");
    req.responseType = "text";

    req.onload = function(){
        let str = req.response;
        switch(str){
            case "LoginException": throwException("Invalid login entered"); break;
            case "PasswordException": throwException("Invalid password entered");  break;
            case "Exception": throwException("Something went wrong"); break;
            default: setCookie("user", str);
        }
    }

    req.send(null);
}