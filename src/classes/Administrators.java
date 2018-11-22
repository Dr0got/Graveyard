package classes;

import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Administrators {
    class Administrator{
        public String admin_name;
        public String admin_sname;
        public String admin_email;
        public String admin_phone;
    }

    private ArrayList<Administrator> allAdmins;

    public Administrators(ResultSet res) throws SQLException {
        allAdmins = new ArrayList<Administrator>();
        while(res.next()) {
            Administrator adm = new Administrator();
            adm.admin_name = res.getString(3);
            adm.admin_sname = res.getString(4);
            adm.admin_email = res.getString(5);
            adm.admin_phone = res.getString(6);

            allAdmins.add(adm);
        }
    }

    public String getAdministratorsJSON(){
        try {
            Gson gs = new Gson();
            String str = gs.toJson(this);
            return str;
        } catch (Exception e) {
            System.out.println("Error in parsing JSON");
        }
        return "";
    }

    @Override
    public String toString() {
        return "Administrators{" +
                "allAdmins='" + allAdmins +
                '}';
    }
}
