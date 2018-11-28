<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Map</title>
        <link rel="stylesheet" href="css/map.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/main-page.css">
        <script src="js/Map.js"></script>
        <script src="js/Cookie.js"></script>
        <script src="js/RequestScript.js"></script>
    </head>
    <body>
      <div class="fixed">


      <header id="first-header">
        <button id="enter" class="enter-button">Вход</button>
        <button id="registration" class="enter-button">Регистрация</button>
      </header>
      <header id="second-header">
        <ul>
          <a href="Graveyard.html"><li>Главная</li></a>
          <a href="goods.html"><li>Товары и услуги</li></a>
          <a href="Map.jsp"><li>Карта</li></a>
          <a href="contacts.html"><li>Контакты</li></a>
        </ul>
      </header>
      </div>
      <section id="enter-block"></section>
        <canvas id="canvas"></canvas>
        <div id = "map" style="opacity: 1">
            <img src = "images/map.png" usemap="#Navigation" style="height: 1094px; width: 887px;" id="mapImage">
            <map id="Navigation"  name="Navigation">
                <area shape="polygon" coords="42, 705, 140, 700, 110, 730, 15, 735" onclick="getMap(1)">
                <area shape="polygon" coords="390, 450, 440, 400, 470, 430, 420, 480" onclick="getMap(2)">
                <area shape="poly" coords="440, 500, 500, 450, 520, 480, 4700, 530" onclick="getMap(3)">
            </map>
        </div>

        <div style="position:absolute; left: 80%; top: 15%;">
          <input type="text" id = "human" placeholder = "Фамилия" />
          <input type="date" id = "deathDate" placeholder = "Дата смерти" />
          <button onclick = "findHuman()">Найти</button>
        </div>

        <script src="js/login.js" charset="utf-8"></script>
        <script>
            //getMap(1);
        </script>
    </body>
</html>
