package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {

    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin = request.getHeader("admin");
        String author = request.getHeader("user");
        String text = request.getHeader("text");
        Timestamp ts = new Timestamp((new java.util.Date()).getTime());

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("INSERT into graveyard.messages values ((SELECT MAX(id_message)+2 FROM messages m1), ?, ?, ?, ?)");
            prSt.setString(1, admin);
            prSt.setString(2, author);
            prSt.setString(3, text);
            prSt.setTimestamp(4, ts);

            prSt.execute();
            response.getWriter().write("OK");
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            response.getWriter().write("NO");
        }
    }

}
