let numGraveyard;
let map;
let human;

function getMap(nGraveyard){
    if(document.getElementById("mapImage"))
     document.getElementById("map").removeChild(document.getElementById("mapImage"));
    numGraveyard = nGraveyard;
    let req = new XMLHttpRequest();
    req.open('GET', 'GraveServlet', true);
    req.setRequestHeader('numOfYard', nGraveyard);
    req.responseType = 'text';

    //When there is a response from the server
    req.onload = function() {
        let str = req.response; // get the string from the response
        let obj = JSON.parse(str);
        map = obj;
        for(let grave of obj.graves){
            let button = document.createElement("button");
            button.style.position = "absolute";
            button.style.left = grave.x_grave * 20 + "px";
            button.style.top = grave.y_grave * 20 + "px";
            button.style.width = grave.grave_width * 20 + "px";
            button.style.height = grave.grave_length * 20 + "px";
            button.style.backgroundColor = (grave.client) ? "black" : "aqua";
            button.data = [grave.x_grave, grave.y_grave, grave.grave_width, grave.grave_length];
            if(grave.dec_fullname) {
                button.dec_data = [grave.dec_fullname, grave.dec_birthday, grave.dec_deathday];
            }
            button.onmouseover = (event)=>{
                event.currentTarget.focused = true;
                generateForm(button);
            };
            button.onmouseout = (event)=>{
                event.currentTarget.focused = false;
                document.getElementById("map").removeChild(document.getElementById("shownNow"));
                /*setTimeout(()=>{
                    if(!document.getElementById("shownNow").focused)
                    document.getElementById("map").removeChild(document.getElementById("shownNow"));}, 100);
*/
            };

            button.onclick = (event)=>{
                if(!event.currentTarget.dec_data)
                 placeBying(event.currentTarget);
            };

            this.focused = true;
            document.getElementById("map").appendChild(button);
        }
    };

    req.send(null);
}

function  generateForm(button) {
    let form = document.createElement("div");

    let info = document.createElement("span");
    info.innerText = "X: " + button.data[0] + "; ";
    form.appendChild(info);

    let info2 = document.createElement("span");
    info2.innerText = "Y: " + button.data[1] + "; ";
    form.appendChild(info2);

    let info3 = document.createElement("span");
    info3.innerText = "Width: " + button.data[2] + "м" + "; ";
    form.appendChild(info3);

    let info4 = document.createElement("span");
    info4.innerText = "Length: " + button.data[3] + "м" + "; ";
    form.appendChild(info4);

    if(button.dec_data){
        let info5 = document.createElement("span");
        info5.innerText = "Покойный: " + button.dec_data[0] + "; ";
        form.appendChild(info5);

        let info6 = document.createElement("span");
        info6.innerText = "Дата рождения: " + button.dec_data[1] + "; ";
        form.appendChild(info6);

        let info7 = document.createElement("span");
        info7.innerText = "Дата смерти: " + button.dec_data[2] + "; ";
        form.appendChild(info7);
    }
    else{
        /*let but = document.createElement("button");
        but.innerHTML = "Купить место";
        but.onclick = ()=>{placeBying(button)};
        form.appendChild(but);*/
    }

    form.parentButton = button;
    form.style.position = "absolute";
    form.style.left = button.style.left.substring(0, button.style.left.length-3) + "9px";
    form.style.top = button.style.top.substring(0, button.style.top.length-3) + "9px";
    form.style.border = "3px solid grey";
    form.style.backgroundColor = "white";
    form.id = "shownNow";
    form.onmouseover = ()=>{
        document.getElementById("shownNow").focused = true;
    };
    /*form.onmouseout = (event)=> {
        document.getElementById("shownNow").focused = false;
        setTimeout((smt) => {
            if (!smt.parentButton.focused)
                document.getElementById("map").removeChild(document.getElementById("shownNow"));
        }, 100, event.currentTarget);
    };*/
    document.getElementById("map").appendChild(form);
}

function placeBying(button){
    if(getCookie("user")){
        let ok = confirm("Вы действительно хотите купить эту могилу?");
        if(ok)
            BuyPlace(numGraveyard, button.data[0], button.data[1]);
        else alert("No");
    }
    else alert("Сначала авторизируйтесь");
}

function findHuman(){
    let req = new XMLHttpRequest();
    req.open('GET', 'MapServlet', true);
    req.setRequestHeader('sname', document.getElementById("human").value);
    req.setRequestHeader('deathDate', document.getElementById("deathDate").value);
    req.responseType = 'text';

    //When there is a response from the server
    req.onload = function() {
        let human = JSON.parse(req.response);
        getMap(human.numYard);

        setTimeout(showWay, 500, human);
    };

    req.send();
}

function showWay(human){
    let ctx = document.getElementById("canvas").getContext("2d");
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, 1000, 650);

    ctx.strokeStyle = "red";
    ctx.strokeWidth = "1px";
    let point = {x: map.x, y: map.y};
    ctx.beginPath();
    ctx.moveTo(point.x*20-10, point.y*20+10);
    //ctx.lineTo(human.x_grave*20, human.y_grave*20);
    while(point.x != human.x_grave && point.y != human.y_grave) {
        while (point.x > human.x_grave) {
          //  if(isProblem(point.x-1, point.y)){
           //     point.y += 1;
           //     break;
           // }
            point.x -= 1;
            ctx.lineTo(point.x * 20 - 10, point.y * 20 + 10);
        }
        while (point.x < human.x_grave) {
           // if(isProblem(point.x+1, point.y)){
            //    point.y += 1;
             //   break;
           // }
            point.x += 1;
            ctx.lineTo(point.x * 20 - 10, point.y * 20 + 10);
        }
        while (point.y > human.y_grave) {
            //if(isProblem(point.x, point.y-1)){
             //   point.x += 1;
              //  break;
          //  }
            point.y -= 1;
            ctx.lineTo(point.x * 20 - 10, point.y * 20 + 10);
        }
        while (point.y < human.y_grave) {
           // if(isProblem(point.x, point.y+1)){
            //    point.x += 1;
            //    break;
           // }
            point.y += 1;
            ctx.lineTo(point.x * 20 - 10, point.y * 20 + 10);
        }
    }
    ctx.lineTo(point.x*20, point.y*20+10);
    ctx.stroke();
}

function isProblem(x, y){
    for(let grave of map.graves){
        if(grave.x_grave == x && grave.y_grave == y)
            return true;
    }
    return false;
}