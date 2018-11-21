package classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;

public class Grave {
    @JsonProperty("x_grave")
    public int x_grave;
    @JsonProperty("y_grave")
    public int y_grave;
    @JsonProperty("grave_length")
    public int grave_length;
    @JsonProperty("grave_width")
    public int grave_width;
    @JsonProperty("client")
    public String client;
    @JsonProperty("dec_fullname")
    public String dec_fullname;
    @JsonProperty("dec_birthday")
    public String dec_birthday;
    @JsonProperty("dec_deathday")
    public String dec_deathday;
}
