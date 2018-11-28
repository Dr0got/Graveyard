let tableData;

function getAdministrators(){
    let req = getAdministratorsFromServer();

    req.onload = function(){
        tableData =  JSON.parse(req.response);
        let table = document.getElementById("mainTable");
        table.style.borderCollapse = "collapse";

        //set header
        let tr = document.createElement("tr");
        tr.style.backgroundColor = "green";
        tr.style.color = "white";

        let headers = ["Имя", "Фамилия", "Электронная почта", "Телефон", "Редактировать"];
        for(let header of headers){
            let td = document.createElement("td");
            td.innerHTML = header;
            tr.appendChild(td);
        }
        table.appendChild(tr);

        //set data;
        for(let i = 0; i < tableData.allAdmins.length; ++i) {
            let data = tableData.allAdmins[i];
            let dataRow = document.createElement("tr");
            dataRow.id = "row" + i;

            let dataCheck = document.createElement("td");
            dataCheck.id = "name" + i;
            dataCheck.innerHTML = data.admin_name;
            dataCheck.style.border = "2px black";
            dataRow.appendChild(dataCheck);

            let dataCheck2 = document.createElement("td");
            dataCheck2.id = "sname" + i;
            dataCheck2.innerHTML = data.admin_sname;
            dataCheck2.style.border = "2px black";
            dataRow.appendChild(dataCheck2);

            let dataCheck3 = document.createElement("td");
            dataCheck3.id = "email" + i;
            dataCheck3.innerHTML = data.admin_email;
            dataCheck3.style.border = "2px black";
            dataRow.appendChild(dataCheck3);

            let dataCheck4 = document.createElement("td");
            dataCheck4.id = "phone" + i;
            dataCheck4.innerHTML = data.admin_phone;
            dataCheck4.style.border = "2px black";
            dataRow.appendChild(dataCheck4);

            let button = document.createElement("td");
            button.id = "button" + i;
            button.innerHTML = "Редактировать";
            button.onclick = function (event) {
                EditAdmin(event.currentTarget.id.substring(event.currentTarget.id.length - 1));
            };
            let btnCheck = document.createElement("td");
            btnCheck.appendChild(button);
            dataRow.appendChild(btnCheck);

            table.appendChild(dataRow);
        }
    }
}

function EditAdmin(number){
    let form = document.createElement("div");
    form.style.border = "3px navy solid";
    form.id = "EditForm";
    form.row = number;

    let textbox1= document.createElement("input");
    textbox1.type = "text";
    textbox1.id = "newName";
    textbox1.value = document.getElementById("name"+number).innerText;
    form.appendChild(textbox1);

    let textbox2= document.createElement("input");
    textbox2.type = "text";
    textbox2.id = "newSname";
    textbox2.value = document.getElementById("sname"+number).innerText;
    form.appendChild(textbox2);

    let textbox3= document.createElement("input");
    textbox3.type = "text";
    textbox3.id = "newEmail";
    textbox3.value = document.getElementById("email"+number).innerText;
    form.appendChild(textbox3);

    let textbox4= document.createElement("input");
    textbox4.type = "text";
    textbox4.id = "newPhone";
    textbox4.value = document.getElementById("phone"+number).innerText;
    form.appendChild(textbox4);

    let btn = document.createElement("button");
    btn.innerHTML = "Принять";
    btn.onclick = function(){
        UpdateAdmins(document.getElementById("newName").value, document.getElementById("newSname").value, document.getElementById("newEmail").value, document.getElementById("newPhone").value, document.getElementById("phone" + document.getElementById("EditForm").row).innerText);
    };
    form.appendChild(btn);

    let btn2 = document.createElement("button");
    btn2.innerHTML = "Отмена";
    btn2.onclick = function(){
        document.getElementsByTagName("body")[0].removeChild(document.getElementById("EditForm"));
    };
    form.appendChild(btn2);
    document.getElementsByTagName("body")[0].appendChild(form);
}

function showAddAdmin(){
    if(document.getElementById("addAdminForm").style.visibility == "hidden")
        document.getElementById("addAdminForm").style.visibility = "visible";
    else
        document.getElementById("addAdminForm").style.visibility = "hidden";
}