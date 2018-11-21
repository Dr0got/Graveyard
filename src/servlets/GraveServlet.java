package servlets;

import classes.Grave;
import classes.Graveyard;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/GraveServlet")
public class GraveServlet extends HttpServlet {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://localhost:3306/graveyard?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC"+
            "&allowPublicKeyRetrieval=true";

    private String query = "SELECT DISTINCT x_grave, y_grave, grave_length, grave_width, id_client, null, null, null " +
            "FROM graveyard.graves,graveyard.graveyards, graveyard.burials " +
            "WHERE (graveyards.id_graveyard = graves.id_graveyard " +
            "AND graveyard_number = ? "+
            "AND graves.id_grave NOT IN ( SELECT burials.id_grave FROM graveyard.burials)) "+
            "UNION "+
            "SELECT x_grave, y_grave, grave_length, grave_width, id_client, dec_fullname, dec_birthday, dec_deathday "+
            "FROM graveyard.graves, graveyard.graveyards, graveyard.deceased, graveyard.burials "+
            "WHERE graveyards.id_graveyard = graves.id_graveyard AND graves.id_grave = burials.id_grave AND burials.id_dec = deceased.id_dec AND graveyard_number = ?;";

    private String updateQuery = "UPDATE graveyard.graves "+
    "SET id_client = (SELECT id_client FROM graveyard.clients WHERE login = ?), "+
    "grave_bought_datetime = ? " +
    "WHERE id_graveyard = (SELECT id_graveyard FROM graveyard.graveyards WHERE graveyard_number = ?) " +
    "AND x_grave = ? " +
    "AND y_grave = ?; ";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int graveyard = Integer.parseInt(request.getHeader("numOfYard"));
            String login = request.getHeader("login");
            int x = Integer.parseInt(request.getHeader("graveX"));
            int y = Integer.parseInt(request.getHeader("graveY"));
            //get present datetime
            Timestamp ts = new Timestamp((new java.util.Date()).getTime());

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement prSt = connection.prepareStatement(updateQuery);
            prSt.setString(1, login);
            prSt.setTimestamp(2, ts);
            prSt.setInt(3, graveyard);
            prSt.setInt(4, x);
            prSt.setInt(5, y);
            prSt.execute();
            response.getWriter().write("Okay");
        }catch(Exception e){
            response.getWriter().write("Exception");
            System.out.println("Error in buying grave");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numOfYard = request.getHeader("numOfYard");
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(connectionURL, userName, this.password);
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, Integer.parseInt(numOfYard));
            stat.setInt(2, Integer.parseInt(numOfYard));
            ResultSet res = stat.executeQuery();
            ArrayList<Grave> list= new ArrayList<Grave>();
            while(res.next()) {
                Grave gr = new Grave();
                gr.x_grave = res.getInt(1);
                gr.y_grave = res.getInt(2);
                gr.grave_length = res.getInt(3);
                gr.grave_width = res.getInt(4);
                try {
                    gr.client = res.getString(5);
                    gr.dec_fullname = res.getString(6);
                    gr.dec_birthday = res.getDate(7).toString();
                    gr.dec_deathday = res.getDate(8).toString();
                }catch(NullPointerException e){
                    gr.dec_fullname = gr.dec_birthday = gr.dec_deathday = null;
                }
                list.add(gr);
            }
            Graveyard graveyard = new Graveyard(list);
            String str = graveyard.getGraveJSON();
            response.getWriter().write(str);
        }catch(SQLException e){
            System.out.println("Error in SQL");
            e.printStackTrace();
            response.getWriter().write("Exception");
        }
    }
}
