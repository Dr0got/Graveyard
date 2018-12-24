package servlets;

import classes.Administrators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = request.getHeader("table");
        switch(table){
            case "administrators": InsertAdmin(request, response); break;
            case "deceased": InsertDeceased(request, response); break;
            case "coffins": InsertCoffin(request, response); break;
            case "monuments": InsertMonument(request, response); break;
            case "katafalk": InsertCoffin(request, response); break;
            case "dop": InsertDop(request, response); break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void InsertAdmin(HttpServletRequest request, HttpServletResponse response){
        String[] data = {request.getHeader("password"), request.getHeader("name"), request.getHeader("sname"), request.getHeader("email"), request.getHeader("phone")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_admin)+1 AS ID FROM graveyard.administrators");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.administrators values (?, ?, ?, ?, ?, ?)");
            prSt.setInt(1, id);
            for(int i = 0; i < data.length; ++i) {
                prSt.setString(i+2, data[i]);
            }
            prSt.execute();

            response.getWriter().write("New admin's id - "+ id);
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
             response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }

    private void InsertDeceased(HttpServletRequest request, HttpServletResponse response) {
        String[] data = {request.getHeader("name"), request.getHeader("sname"), request.getHeader("birthday"), request.getHeader("deathday")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_dec)+1 AS ID FROM graveyard.deceased");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.deceased values (?, ?, ?, ?)");
            prSt.setInt(1, id);
            prSt.setString(2, data[0] + " " + data[1]);
            prSt.setString(3, data[2]);
            prSt.setString(4, data[3]);
            prSt.execute();

            response.getWriter().write("Отправка выполнена успешно");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }

    private void InsertCoffin(HttpServletRequest request, HttpServletResponse response) {
        String[] data = {request.getHeader("name"), request.getHeader("sname"), request.getHeader("birthday"), request.getHeader("deathday")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_dec)+1 AS ID FROM graveyard.deceased");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.deceased values (?, ?, ?, ?)");
            prSt.setInt(1, id);
            prSt.setString(2, data[0] + " " + data[1]);
            prSt.setString(3, data[2]);
            prSt.setString(4, data[3]);
            prSt.execute();

            response.getWriter().write("Отправка выполнена успешно");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }

    private void InsertMonument(HttpServletRequest request, HttpServletResponse response) {
        String[] data = {request.getHeader("name"), request.getHeader("sname"), request.getHeader("birthday"), request.getHeader("deathday")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_dec)+1 AS ID FROM graveyard.deceased");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.deceased values (?, ?, ?, ?)");
            prSt.setInt(1, id);
            prSt.setString(2, data[0] + " " + data[1]);
            prSt.setString(3, data[2]);
            prSt.setString(4, data[3]);
            prSt.execute();

            response.getWriter().write("Отправка выполнена успешно");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }

    private void InsertKata(HttpServletRequest request, HttpServletResponse response) {
        String[] data = {request.getHeader("name"), request.getHeader("sname"), request.getHeader("birthday"), request.getHeader("deathday")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_dec)+1 AS ID FROM graveyard.deceased");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.deceased values (?, ?, ?, ?)");
            prSt.setInt(1, id);
            prSt.setString(2, data[0] + " " + data[1]);
            prSt.setString(3, data[2]);
            prSt.setString(4, data[3]);
            prSt.execute();

            response.getWriter().write("Отправка выполнена успешно");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }

    private void InsertDop(HttpServletRequest request, HttpServletResponse response) {
        String[] data = {request.getHeader("name"), request.getHeader("sname"), request.getHeader("birthday"), request.getHeader("deathday")};

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT MAX(id_dec)+1 AS ID FROM graveyard.deceased");
            ResultSet rs = prSt.executeQuery();
            rs.next();
            int id=rs.getInt("ID");

            prSt = connection.prepareStatement("INSERT into graveyard.deceased values (?, ?, ?, ?)");
            prSt.setInt(1, id);
            prSt.setString(2, data[0] + " " + data[1]);
            prSt.setString(3, data[2]);
            prSt.setString(4, data[3]);
            prSt.execute();

            response.getWriter().write("Отправка выполнена успешно");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }catch(IOException e1){
            System.out.println("Error in IO");
            e1.printStackTrace();
            try {
                response.getWriter().write("Серверная ошибка");
            }catch(IOException e2){}
        }
    }
}
