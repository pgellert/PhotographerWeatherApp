package classes;

import classes.coords.Coordinate;
import utils.Search;


/*This is the main class we use to manage and display location data and
* calls to the API. It stores the name of the location in short,
* its API ID (googleId) and the coordinates of the location
* which can be used to get the sunposition. It provides
* getters and setters for each of the fields.*/
public class Location {

    public String name;
    public String googleId;
    public String owmId;
    public Coordinate coordinate;

    public static Location fromName(String name){
        Location locationToFix = new Location(name);
        return Search.getCompletedLocation(locationToFix);
    }

    public Location(String name, String googleId, Coordinate coordinate){
        this.name = name;
        this.googleId = googleId;
        this.coordinate = coordinate;
    }

    private Location(String name){
        this.name = name;
    }

    public Location(){};
}
