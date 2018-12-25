function hideAll(){
    document.getElementById("grob").style.visibility = document.getElementById("nadGrob").style.visibility = document.getElementById("katafalk").style.visibility = document.getElementById("dopServices").style.visibility = "hidden";
    document.getElementById("sum").innerText = "0 грн";
}

function setPrice(){
    document.getElementById("f_grob").price = 2500;
    document.getElementById("s_grob").price = 2000;
    document.getElementById("t_grob").price = 4500;
    document.getElementById("f_plate").price = 0.7;
    document.getElementById("s_plate").price = 1.3;
    document.getElementById("t_plate").price = 0.9;
    document.getElementById("fr_plate").price = 3.0;
    document.getElementById("blmb").price = 3000;
    document.getElementById("conc").price = 1000;
    document.getElementById("gran").price = 4770;
    document.getElementById("met").price = 1000;
    document.getElementById("wtmb").price = 25000;
    document.getElementById("f_kat").price = 3000;
    document.getElementById("s_kat").price = 1500;
    document.getElementById("t_kat").price = 500;
    document.getElementById("table").price = 600;
    document.getElementById("group").price = 400;
    document.getElementById("ceremony").price = 500;

    document.getElementById("f_kat").onclick = document.getElementById("s_kat").onclick = document.getElementById("t_kat").onclick = document.getElementById("table").onclick = document.getElementById("group").onclick = document.getElementById("ceremony").onclick = function(event){
        document.getElementById("sum").innerText = this.price;
    };
    document.getElementById("f_plate").onclick = document.getElementById("s_plate").onclick = document.getElementById("t_plate").onclick = document.getElementById("fr_plate").onclick = function(event){
        document.getElementById("sum").innerText = this.price * document.getElementById("forms").options[document.getElementById("forms").selectedIndex].price * document.getElementById("height").value;
    };
    document.getElementById("forms").onchange = document.getElementById("height").onchange = function(event){
        if(document.getElementById("f_plate").checked)
            document.getElementById("sum").innerText = document.getElementById("forms").options[document.getElementById("forms").selectedIndex].price * document.getElementById("f_plate").price * document.getElementById("height").value;
        else if(document.getElementById("s_plate").checked)
            document.getElementById("sum").innerText = document.getElementById("forms").options[document.getElementById("forms").selectedIndex].price * document.getElementById("s_plate").price * document.getElementById("height").value;
        else if(document.getElementById("t_plate").checked)
            document.getElementById("sum").innerText = document.getElementById("forms").options[document.getElementById("forms").selectedIndex].price * document.getElementById("t_plate").price * document.getElementById("height").value;
        else if(document.getElementById("fr_plate").checked)
            document.getElementById("sum").innerText = document.getElementById("forms").options[document.getElementById("forms").selectedIndex].price * document.getElementById("fr_plate").price * document.getElementById("height").value;
    };
    document.getElementById("f_grob").onclick = document.getElementById("s_grob").onclick = document.getElementById("t_grob").onclick = function(event){
        document.getElementById("sum").innerText = this.price * document.getElementById("length").value;
    };
    document.getElementById("length").onchange = function(event){
        if(document.getElementById("f_grob").checked)
            document.getElementById("sum").innerText = document.getElementById("f_grob").price * document.getElementById("length").value;
        else if(document.getElementById("s_grob").checked)
            document.getElementById("sum").innerText = document.getElementById("s_grob").price * document.getElementById("length").value;
        else if(document.getElementById("t_grob").checked)
            document.getElementById("sum").innerText = document.getElementById("t_grob").price * document.getElementById("length").value;
    }
}

function buyCoffin() {
    let req = new XMLHttpRequest();
    req.open('POST', 'BuyServlet', true);
    req.setRequestHeader('type', "coffin");
    if(document.getElementById("f_grob").checked)
        req.setRequestHeader('material', "oak");
    else if(document.getElementById("s_grob").checked)
        req.setRequestHeader('material', "pine");
    else if(document.getElementById("t_grob").checked)
        req.setRequestHeader('material', "zinc");

    req.setRequestHeader('length', document.getElementById("length").value);
    req.setRequestHeader('width', document.getElementById("shir").value);
    if(getCookie("user"))
        req.setRequestHeader("user", getCookie("user"));
    else{
        throwException("Сначала авторизируйтесь");
    }

    req.responseType = "text";

    req.onload = function(){
        let str = req.response;
        alert((str == "Exception") ? "Серверная ошибка" : str);
    };

    req.send(null);
}

function buyMonument(){
    let req = new XMLHttpRequest();
    req.open('POST', 'BuyServlet', true);
    req.setRequestHeader('type', "monument");
    req.responseType = "text";
    req.setRequestHeader('material', document.getElementById("forms").options[document.getElementById("forms").selectedIndex].value);
    if(document.getElementById("f_plate").checked)
        req.setRequestHeader('form', "cross");
    else if(document.getElementById("s_plate").checked)
        req.setRequestHeader('form', "petal");
    else if(document.getElementById("t_plate").checked)
        req.setRequestHeader('form', "rectangle");
    else if(document.getElementById("fr_plate").checked)
        req.setRequestHeader('form', "bas-relief");

    req.setRequestHeader('height', document.getElementById("height").value);
    if(getCookie("user"))
        req.setRequestHeader("user", getCookie("user"));
    else{
        throwException("Сначала авторизируйтесь");
    }

    req.onload = function(){
        let str = req.response;
        alert((str == "Exception") ? "Серверная ошибка" : str);
    };

    req.send(null);
}

function buyKatafalk(){
    let req = new XMLHttpRequest();
    req.open('POST', 'BuyServlet', true);
    req.setRequestHeader('type', "katafalk");
    if(getCookie("user"))
        req.setRequestHeader("user", getCookie("user"));
    else{
        throwException("Сначала авторизируйтесь");
    }

    if(document.getElementById("f_kat").checked)
        req.setRequestHeader('katafalk', "katafalk_premium");
    else if(document.getElementById("s_kat").checked)
        req.setRequestHeader('katafalk', "katafalk_business");
    else if(document.getElementById("t_kat").checked)
        req.setRequestHeader('katafalk', "katafalk_econom");
    req.responseType = "text";

    req.onload = function(){
        let str = req.response;
        alert((str == "Exception") ? "Серверная ошибка" : str);
    };

    req.send(null);
}

function buyDop(){
    let req = new XMLHttpRequest();
    req.open('POST', 'BuyServlet', true);
    req.setRequestHeader('type', "dop");
    if(getCookie("user"))
        req.setRequestHeader("user", getCookie("user"));
    else{
        throwException("Сначала авторизируйтесь");
    }
    if(document.getElementById("table").checked)
        req.setRequestHeader('dop', "table");
    else if(document.getElementById("group").checked)
        req.setRequestHeader('dop', "group");
    else if(document.getElementById("ceremony").checked)
        req.setRequestHeader('dop', "ceremony");
    req.responseType = "text";

    req.onload = function(){
        let str = req.response;
        alert((str == "Exception") ? "Серверная ошибка" : str);
    };

    req.send(null);
}