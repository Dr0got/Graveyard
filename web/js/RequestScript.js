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
        if(str == "Exception")
            throwException("Error in registration");
        else{
            let data = str.split(" ");
            setCookie("user", data[0]);
            setCookie("password", data[1]);
        }
        if(document.getElementById("out"))
            document.getElementById("enter-block").removeChild(document.getElementById("out"));
        if(document.getElementById("out2"))
            document.getElementById("enter-block").removeChild(document.getElementById("out2"));
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
        switch(str) {
            case "LoginException":
                throwException("Invalid login entered");
                break;
            case "PasswordException":
                throwException("Invalid password entered");
                break;
            case "Exception":
                throwException("Something went wrong");
                break;
            default:
                let data = str.split(" ");
                setCookie("user", data[0]);
                setCookie("password", data[1]);
                if((document.getElementById("isAdmin")).checked) {
                    location.href = "../AdminInfo.html";
                }
        }
        if(document.getElementById("out"))
            document.getElementById("enter-block").removeChild(document.getElementById("out"));
        if(document.getElementById("out2"))
            document.getElementById("enter-block").removeChild(document.getElementById("out2"));
    };

    req.send(null);
}

//Buy a Grave
function BuyPlace(numGraveyard, x, y){
    let req = new XMLHttpRequest();
    req.open('POST', 'GraveServlet', true);
    req.setRequestHeader('login', getCookie("user"));
    req.setRequestHeader('numOfYard', numGraveyard);
    req.setRequestHeader('graveX', x);
    req.setRequestHeader('graveY', y);
    req.responseType = "text";

    req.onload = function(){
        let str = req.response;
        alert((str == "Exception") ? "Серверная ошибка" : str);
    };

    req.send(null);
}


//admin information
function getAdministratorsFromServer(){
    let req = new XMLHttpRequest();
    req.open('POST', 'AdminServlet', true);
    req.setRequestHeader('table', "administrators");
    req.responseType = "text";

    req.send();
    return req;
}


function UpdateAdmins(name, sname, email, phone, oldphone){
    let req = new XMLHttpRequest();
    req.open('GET', 'AdminServlet', true);
    req.setRequestHeader('table', "administrators");
    req.setRequestHeader('name', name);
    req.setRequestHeader('sname', sname);
    req.setRequestHeader('email', email);
    req.setRequestHeader('phone', phone);
    req.setRequestHeader('oldphone', oldphone);
    req.responseType = "text";

    req.onload= function(){
        if(req.response)
            alert(req.response);
        location.reload();
    };

    req.send();
}

function InsertAdmin(password, name, sname, phone, email){
    let req = new XMLHttpRequest();
    req.open("POST", "AddServlet", true);
    req.setRequestHeader('table', "administrators");
    req.setRequestHeader('password', password);
    req.setRequestHeader('name', name);
    req.setRequestHeader('sname', sname);
    req.setRequestHeader('email', email);
    req.setRequestHeader('phone', phone);
    req.responseType = "text";

    req.onload= function(){
        if(req.response)
            alert(req.response);
        location.reload();
    };

    req.send();
}

function InsertDeceased(name, sname, birthday, deathday){
    let req = new XMLHttpRequest();
    req.open("POST", "AddServlet", true);
    req.setRequestHeader('table', "deceased");
    req.setRequestHeader('name', name);
    req.setRequestHeader('sname', sname);
    req.setRequestHeader('birthday', birthday);
    req.setRequestHeader('deathday', deathday);
    req.responseType = "text";

    req.onload= function(){
        if(req.response)
            alert(req.response);
        location.reload();
    };

    req.send();
}