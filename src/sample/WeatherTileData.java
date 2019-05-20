package sample;

import classes.Location;

public class WeatherTileData {
    private Location location;
    private boolean isCurrentLocation;

    public WeatherTileData(Location location, boolean isCurrentLocation) {
        this.location = location;
        this.isCurrentLocation = isCurrentLocation;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isCurrentLocation() {
        return isCurrentLocation;
    }
}
