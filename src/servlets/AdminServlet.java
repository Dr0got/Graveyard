package servlets;

import classes.Administrators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = request.getHeader("table");
        switch(table){
            case "administrators": updateAdmin(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = request.getHeader("table");
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT * FROM graveyard." + table + ";");
            ResultSet res = prSt.executeQuery();
            switch(table){
                case "administrators":
                    String s = (new Administrators(res)).getAdministratorsJSON();
                    response.getWriter().write(s);
                    return;
            }
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            response.getWriter().write("Exception");
        }
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response){
        String name = request.getHeader("name");
        String sname = request.getHeader("sname");
        String email = request.getHeader("email");
        String phone = request.getHeader("phone");
        String oldphone = request.getHeader("oldphone");

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("Update graveyard.administrators SET admin_name = ?, admin_sname=?, admin_email=?, admin_phone=? WHERE admin_phone = ?");
            prSt.setString(1, name);
            prSt.setString(2, sname);
            prSt.setString(3, email);
            prSt.setString(4, phone);
            prSt.setString(5, oldphone);
            prSt.execute();
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            try {
                response.getWriter().write("Exception");
            }catch(IOException e1){
                throw new RuntimeException("Exception");
            }
        }
    }
}
