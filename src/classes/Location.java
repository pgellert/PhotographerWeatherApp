package classes;

import classes.coords.Coordinate;

public class Location {

    public String name;
    public String googleId;
    public String owmId;
    public Coordinate coordinate;

    public static Location fromName(String name){ return new Location(name);}

    public Location(String name, String googleId, Coordinate coordinate){
        this.name = name;
        this.googleId = googleId;
        this.coordinate = coordinate;
    }

    public Location(String name){
        this.name = name;
    }

    public Location(){};
}
