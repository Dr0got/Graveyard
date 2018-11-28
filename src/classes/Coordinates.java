package classes;

import com.google.gson.Gson;

public class Coordinates {

    public int x_grave;

    public int y_grave;

    public int numYard;

    public Coordinates(int x, int y, int num){
        x_grave = x;
        y_grave = y;
        numYard = num;
    }

    public synchronized String getGraveJSON() {
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
        return "Coordinates{" +
                "x_grave='" + x_grave +
                "y_grave='" + y_grave +
                "'numYard='" + numYard +
                '}';
    }
}
