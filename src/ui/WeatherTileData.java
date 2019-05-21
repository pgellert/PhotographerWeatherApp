package ui;

import classes.Location;

/*
    This class stores the data that we need to populate a ListItem (weather tile) on the MainPage
 */

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
