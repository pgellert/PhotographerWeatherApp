package utils;
import classes.Location;
import classes.coords.Coordinate;

public class LocationFinder {

    // This function is mocking the location sensor of the phone, it just returns Cambridge for now
    public static Location getCurrentLocation(){
        return new Location("Cambridge", "ChIJzVVuP0p32EcRx4TmQ3Mi-Ks", new Coordinate("0.09173199999999997","52.210891"));
    }

}
