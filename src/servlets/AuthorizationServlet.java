package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getHeader("login");
        String pass = request.getHeader("password");
        String mode = request.getHeader("mode");
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement((mode.equals("admin"))?"SELECT password FROM graveyard.administrators WHERE login = ?" : "SELECT password FROM graveyard.clients WHERE login = ?");
            prSt.setString(1, login);
            ResultSet res = prSt.executeQuery();
            if(res.next()){
                if(res.getString(1).equals(pass))
                    response.getWriter().write(login);
                else response.getWriter().write("PasswordException");
                return;
            }else
                response.getWriter().write("LoginException");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            response.getWriter().write("Exception");
        }
    }
}
