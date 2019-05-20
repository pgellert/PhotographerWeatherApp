package classes;

import classes.coords.Coordinate;
import utils.Search;

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
