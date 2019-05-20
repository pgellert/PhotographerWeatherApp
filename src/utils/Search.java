package utils;

import classes.Location;
import classes.coords.Coordinate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static utils.QueryParser.callQuery;

public class Search {

    //String for Google API
    private static final String api_id_google = "AIzaSyAhxpVJs2OH7oYj_LM8DR5Yyzdyl8eMGu0";

    //Gets coordinates for a corresponding GoogleID
    public static Coordinate getCoords(String googleId){

        //HTTPS query...
        String query = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + googleId + "&key=" + api_id_google;

        //which is sent to corresponding server, and Json generated
        JSONObject jobj = callQuery(query);

        //System.out.println(jobj);

        Gson g = new Gson();

        //Convert JSONObject (one library) to JsonObject (another object, Google version))
        JsonObject jsonObject = g.fromJson(jobj.toJSONString(), JsonObject.class);

        //Manually place data in coordinate (not easily parseable without lots of dummy classes)
        JsonObject location = jsonObject.get("result").getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();

        System.out.println(location);
        return new Coordinate(location.get("lng").getAsString(), location.get("lat").getAsString());
    }

    public static Location getCompletedLocation(Location location){
        Location locationToReturn = autoCompleteInput(location.name).get(0);
        return locationToReturn;
    }

    //Autocomplete input given to function by search bar
    public static List<Location> autoCompleteInput(String stringLocation){
        String query = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%22" + stringLocation.replace(" ", "%20") + "%22&key=" + api_id_google;
        JSONObject jobj = callQuery(query);
        System.out.println(query);
        System.out.println(jobj);

        Gson g = new Gson();

        //Conversion to correct Google JsonObject
        JsonObject jsonObject = g.fromJson(jobj.toJSONString(), JsonObject.class);

        List<Location> autoCompResults = new ArrayList<>();


        //Iterate through each possible autocomplete value provided and return a corresponding Location object
        for (JsonElement job: jsonObject.get("predictions").getAsJsonArray()){
            String id = job.getAsJsonObject().get("place_id").getAsString();
            Coordinate coordinate = getCoords(id);
            autoCompResults.add(new Location(
                    job.getAsJsonObject().get("description").getAsString(),
                    id,
                    coordinate
            ));
        }

        return autoCompResults;
    }

    //Cannot get actual 'current' location - have to ask on startup

    public static void main(String[] args) {

        System.out.println(autoCompleteInput("Cambridge, UK").get(0).name);
    }
}
