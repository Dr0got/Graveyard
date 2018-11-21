<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Map</title>
        <link rel="stylesheet" href="css/map.css">
        <script src="js/Map.js"></script>
        <script src="js/Cookie.js"></script>
        <script src="js/RequestScript.js"></script>
    </head>
    <body>
        <div id = "map">
            <img src = "images/map.png" usemap="#Navigation" style="height: 1094px; width: 887px;" id="mapImage">
            <map id="Navigation"  name="Navigation">
                <area shape="polygon" coords="42, 705, 140, 700, 110, 730, 15, 735" onclick="getMap(1)">
                <area shape="polygon" coords="390, 450, 440, 400, 470, 430, 420, 480" onclick="getMap(2)">
                <area shape="poly" coords="440, 500, 500, 450, 520, 480, 4700, 530" onclick="getMap(3)">
            </map>
        </div>
        <script>
            //getMap(1);
        </script>
    </body>
</html>
