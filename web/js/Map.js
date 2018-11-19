function getMap(numGraveyard){
    let req = new XMLHttpRequest();
    req.open('GET', 'GraveServlet', true);
    req.setRequestHeader('numOfYard', numGraveyard);
    req.responseType = 'text';

    //When there is a response from the server
    req.onload = function() {
        let str = req.response; // get the string from the response
        let obj = JSON.parse(str);
        for(let grave of obj.graves){
            let button = document.createElement("button");
            button.style.position = "absolute";
            button.style.left = grave.x_grave * 20 + "px";
            button.style.top = grave.y_grave * 20 + "px";
            button.style.width = grave.grave_width * 20 + "px";
            button.style.height = grave.grave_length * 20 + "px";
            button.style.backgroundColor = (grave.dec_fullname) ? "black" : "aqua";
            document.getElementById("map").appendChild(button);
        }
    };

    req.send(null);
}