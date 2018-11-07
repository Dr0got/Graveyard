let div = document.createElement('div'); // Внешний блок формы
let div2 = document.createElement('div');
let form = document.createElement('form'); // Внутр. блок формы входа
let form2 = document.createElement('form'); // Внутр. блок регистрации
let login = document.createElement('input'); // Login
let pass = document.createElement('input'); // Password
let username = document.createElement('input'); // Username Имя
let usersname = document.createElement('input'); // UserSname Фамилия
let userpassport = document.createElement('input'); // Userpassport паспорт
let userphone = document.createElement('input'); // UserPhone
let usermail = document.createElement('input'); // E-mail
let check = document.createElement('div'); // Блок "Админ?"
let checkbox = document.createElement('input'); // Чекбокс "Админ?"
let label = document.createElement('label'); // Надпись "Я - администратор"

let subm = document.createElement('input'); // Кнопка подтвердить
let btn = document.createElement('button'); // Кнопка отмены


// <input name="newFName" id="username" type="text" class = "text" maxlength="50" autocomplete="on" placeholder="Введите Ваше имя" required="" />
//     <input name="newSName" id="usersname" type="text" class = "text" maxlength="50" autocomplete="on" placeholder="Введите Вашу фамилию" required="" />
//     <input name="newPassport" id="userpassport" type="text" class = "text" maxlength="10" autocomplete="on" placeholder="Введите номер Вашего паспорта" required="" />
//     <input name="newPhone" id="userphone" type="text" class = "text" maxlength="50" autocomplete="on" placeholder="Введите Вашу фамилию" required="" />
//     <input name="newEmail" id="useremail" type="text" class = "text" maxlength="50" autocomplete="on" placeholder="Введите Ваш email" required="" />


div.id = 'out';
div2.id = 'out2';

div.style.display="inline-flex";
div2.style.display="inline-flex";

form.id = 'enter-form';
form2.id = 'reg-form';

login.id = 'userLogin';
login.placeholder = 'Введите логин';
login.setAttribute('required', '');

pass.id = 'userPassword';
pass.type = 'password';
pass.maxlength ='50';
pass.placeholder = 'Введите пароль';
pass.setAttribute('required', '');

check.id = 'isAdmin-block';
checkbox.type = 'checkbox';
checkbox.id = 'isAdmin';
checkbox.value = 'Админ';
label.htmlFor = 'isAdmin';

username.id = 'username';
username.type = 'text';
username.autocomplete = 'on';
username.placeholder = 'Введите Ваше имя';
username.setAttribute('required', '');

usersname.id = 'usersname';
usersname.type = 'text';
usersname.autocomplete = 'on';
usersname.placeholder = 'Введите Вашу фамилию';
usersname.setAttribute('required', '');

userpassport.id = 'userpassport';
userpassport.type = 'text';
userpassport.autocomplete = 'on';
userpassport.placeholder = 'Введите номер пасспорта';
userpassport.setAttribute('required', '');

userphone.id = 'userphone';
userphone.type = 'text';
userphone.autocomplete = 'on';
userphone.placeholder = 'Введите номер Вашего телефона';
userphone.setAttribute('required', '');

usermail.id = 'useremail';
usermail.type = 'email';
usermail.autocomplete = 'on';
usermail.placeholder = 'Введите Ваш e-mail';
usermail.setAttribute('required', '');

subm.type = 'submit';
subm.value = 'Войти';
subm.onclick = 'setUser()';
subm.id = 'enter-btn';

btn.id = 'close-btn';
btn.innerHTML="Отмена";

label.appendChild(document.createTextNode('Я - администратор'));

document.getElementById('enter').onclick=displayEnter;
document.getElementById('registration').onclick=displayRegistration;

var fst = document.getElementById('enter-block');

function closeForm() {
  document.getElementById('enter-block').removeChild(div);
  document.getElementById('enter').onclick=displayEnter;
}

function closeForm2() {
  document.getElementById('enter-block').removeChild(div2);
  document.getElementById('registration').onclick=displayRegistration;
}
function displayEnter() {
  div2.style.display="none";

  form.appendChild(login);
  form.appendChild(pass);
  form.appendChild(check);
  check.appendChild(checkbox);
  check.appendChild(label);
  form.appendChild(subm);
  form.appendChild(btn);
  div.appendChild(form);
  fst.appendChild(div);
  div.style.display="inline-flex";

  document.getElementById('close-btn').onclick=closeForm;
  // document.getElementById('enter').onclick=closeForm;
}

function displayRegistration() {
  div.style.display="none";

  form2.appendChild(login);
  form2.appendChild(pass);
  form2.appendChild(username);
  form2.appendChild(usersname);
  form2.appendChild(userpassport);
  form2.appendChild(userphone);
  form2.appendChild(usermail);
  form2.appendChild(subm);
  form2.appendChild(btn);
  div2.appendChild(form2);
  fst.appendChild(div2);
  div2.style.display="inline-flex";

  document.getElementById('close-btn').onclick=closeForm2;
  subm.onclick = function(){setUser();};
  // document.getElementById('registration').onclick=closeForm2;

}
