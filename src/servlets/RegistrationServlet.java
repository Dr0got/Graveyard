package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getHeader("login");
        String pass = request.getHeader("password");
        String fullName = request.getHeader("full_name");
        String passport = request.getHeader("passport_number");
        String email = request.getHeader("email");
        String userphone = request.getHeader("phone");

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("insert into klients (login, password, full_name, passport_number, email, phone)values (?, ?, ?, ?, ?, ?)");
            prSt.setString(1, login);
            prSt.setString(2, pass);
            prSt.setString(3, fullName);
            prSt.setString(4, passport);
            prSt.setString(5, email);
            prSt.setString(6, userphone);
            prSt.execute();
            response.getWriter().write(login);
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            response.getWriter().write("Exception");
        }
    }
}
