package utils;

import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/*
 * This class deals with everything related to quering the OpenWeatherMap
 * database that we use to get JSON objects which contain information about
 * weather. It does all the networking with proper exceptions and then
 * returns further queriable data structures.
  * */
public class QueryParser {

    public static Image getIcon(String icon){
        return new Image("http://openweathermap.org/img/w/" + icon + ".png");
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
