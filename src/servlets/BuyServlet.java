package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private String forCoffin = "INSERT into graveyard.coffins values((Select max(c1.id_coffin)+1 FROM graveyard.coffins c1), ?, ?, ?, ?, ?)";
    private String forMonument = "INSERT into graveyard.monuments values((Select max(m1.id_monument)+1 FROM graveyard.monuments m1), ?, ?, ?, ?, ?, null )";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getHeader("type");
        switch(type){
            case "coffin": BuyCoffin(request, response); break;
            case "monument": BuyMonument(request, response); break;
            case "katafalk": BuyKatafalk(request, response); break;
            case "dop": BuyDop(request, response); break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void BuyCoffin(HttpServletRequest request, HttpServletResponse response) {
        String material = request.getHeader("material");
        String length = request.getHeader("length");
        String width = request.getHeader("width");
        String user = request.getHeader("user");
        Timestamp ts = new Timestamp((new java.util.Date()).getTime());

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement(forCoffin);
            prSt.setInt(1, getClient(user));
            prSt.setString(2, material);
            prSt.setDouble(3, Double.parseDouble(length));
            prSt.setTimestamp(4, ts);
            prSt.setDouble(5, Double.parseDouble(width));
            prSt.execute();

            response.getWriter().write("Покупка совершена");
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

    private void BuyMonument(HttpServletRequest request, HttpServletResponse response) {
        String material = request.getHeader("material");
        String height = request.getHeader("height");
        String form = request.getHeader("form");
        String user = request.getHeader("user");
        Timestamp ts = new Timestamp((new java.util.Date()).getTime());

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement(forMonument);
            prSt.setInt(1, getClient(user));
            prSt.setString(2, material);
            prSt.setString(3, form);
            prSt.setDouble(4, Double.parseDouble(height));
            prSt.setTimestamp(5, ts);
            prSt.execute();

            response.getWriter().write("Покупка совершена");
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

    private void BuyKatafalk(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getHeader("user");
        String katafalk = request.getHeader("katafalk");
        Timestamp ts = new Timestamp((new java.util.Date()).getTime());

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prStPrev = connection.prepareStatement("SELECT id_prodService FROM graveyard.assortment WHERE prodService_name = ?");
            prStPrev.setString(1, katafalk);
            ResultSet rs = prStPrev.executeQuery();
            rs.next();

            PreparedStatement prSt = connection.prepareStatement("INSERT INTO client_choices VALUES (?, ?, ?)");
            prSt.setInt(1, getClient(user));
            prSt.setInt(2, rs.getInt(1));
            prSt.setTimestamp(3, ts);
            prSt.execute();

            response.getWriter().write("Покупка совершена");
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

    private void BuyDop(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getHeader("user");
        String dop = request.getHeader("dop");
        Timestamp ts = new Timestamp((new java.util.Date()).getTime());

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prStPrev = connection.prepareStatement("SELECT id_prodService FROM graveyard.assortment WHERE prodService_name = ?");
            prStPrev.setString(1, dop);
            ResultSet rs = prStPrev.executeQuery();
            rs.next();

            PreparedStatement prSt = connection.prepareStatement("INSERT INTO client_choices VALUES (?, ?, ?)");
            prSt.setInt(1, getClient(user));
            prSt.setInt(2, rs.getInt(1));
            prSt.setTimestamp(3, ts);
            prSt.execute();

            response.getWriter().write("Покупка совершена");
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

    private int getClient(String login){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT id_client FROM graveyard.clients WHERE login = ?");
            prSt.setString(1,login);
            ResultSet rs = prSt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e) {
            System.out.println("Error in SQL");
            e.printStackTrace();
        }
        return -1;
    }
}
