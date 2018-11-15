function getMap(numGraveyard){
    let req = new XMLHttpRequest();
    req.open('GET', 'GraveServlet', true);
    req.setRequestHeader('numOfYard', numGraveyard);
    req.responseType = 'text';

    //When there is a response from the server
    req.onload = function() {
        let str = req.response; // get the string from the response
        let obj = JSON.parse(str);
        alert(obj);
    };

    req.send(null);
}