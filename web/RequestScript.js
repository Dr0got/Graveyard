function setUser(){
    let req = new XMLHttpRequest();
    req.open('POST', 'RegistrationServlet', true);
    req.setRequestHeader('login', document.getElementById("userLogin").value);
    req.setRequestHeader('password', document.getElementById("userPassword").value);
    /*req.setRequestHeader('full_name', document.getElementById("username").value + " " + document.getElementById("usersname").value);
    req.setRequestHeader('passport_number', document.getElementById("userpassword").value);
    req.setRequestHeader('phone', document.getElementById("userphone").value);
    req.setRequestHeader('email', document.getElementById("useremail").value);*/
    req.responseType = 'text';

    req.onload = function() {
        var str = req.response; // get the string from the response
        alert(str);
    };

    req.send(null);
}
