package servlets;

import classes.Coordinates;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC"+
            "&allowPublicKeyRetrieval=true";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sname = request.getHeader("sname");
        String deathDate = request.getHeader("deathDate");

        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement("SELECT graveyard_number, x_grave, y_grave"+
                    " FROM graveyard.graveyards, graveyard.graves, graveyard.deceased, graveyard.burials" +
                    " WHERE graveyards.id_graveyard = graveyard.graves.id_graveyard" +
                    " AND graveyard.graves.id_grave = burials.id_grave" +
                    " AND burials.id_dec = deceased.id_dec" +
                    " AND deceased.dec_fullname LIKE '%" + sname + "'" +
                    " AND deceased.dec_deathday = ?");
            prSt.setString(1, deathDate);
            ResultSet res = prSt.executeQuery();
            res.next();

            Coordinates coord = new Coordinates(res.getInt(1), res.getInt(2),  res.getInt(3));
            String str = coord.getGraveJSON();
            response.getWriter().write(str);
        }catch(SQLException e){
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
