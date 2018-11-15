package classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Graveyard {
    @JsonProperty("graves")
    public List<Grave> graves;

    public Graveyard(ArrayList<Grave> grvs){
        graves = grvs;
    }

    public synchronized String getGraveJSON() {
        try {
            System.out.println("Start parsing JSON");
            //ObjectMapper mapper = new ObjectMapper();
            Gson gs = new Gson();
            System.out.println("Create mapper");
            /*String str = "";
            PrintWriter pw = new PrintWriter(str);
            mapper.writeValue(pw, this);
            pw.write(str);*/
            String str = gs.toJson(this);
            return str;
        } catch (Exception e) {
            System.out.println("Error in parsing JSON");
        }
        return "";
    }

    @Override
    public String toString() {
        return "Graveyard{" +
                "graves='" + graves +
                '}';
    }
}
