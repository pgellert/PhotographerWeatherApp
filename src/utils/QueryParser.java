package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class QueryParser {

    public static Image getIcon(String icon){
        Image image = null;
        try {
            URL url = new URL("http://openweathermap.org/img/w/" + icon + ".png");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static JSONObject callQuery(String query){
        InputStream input;

        try{
            input = new URL(query).openStream();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("No connection");
            return null;
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(
                    new InputStreamReader(input, "UTF-8"));
        } catch (Exception e){
            System.out.println("JSON object improperly formed.");
            return null;
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        Image image = OWM.getCurrentWeather(LocationFinder.getCurrentLocation()).getIcon();
    }
}
