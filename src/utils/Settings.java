package utils;

import java.util.prefs.Preferences;

public class Settings {
    /*
        State - IS_CELSIUS_KEY keeps the current temperature,
                IS_METER_KEY keeps the current distance unit
                TemperatureUnit and DistanceUnit are enums to work with these
     */
    private static final String IS_CELSIUS_KEY = "TEMPERATURE_UNIT_KEY";
    private static final String IS_METER_KEY = "DISTANCE_UNIT_KEY";
    public enum TemperatureUnit {CELSIUS, KELVIN, FAHRENHEIT}
    public enum DistanceUnit {METER, MILE}

    //Returns the current temperature unit preference as a TemperatureUnit
    public static TemperatureUnit getTemperatureUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_CELSIUS_KEY, true) ? TemperatureUnit.CELSIUS : TemperatureUnit.FAHRENHEIT;
    }

    //Sets the current temperature unit preference to the TemperatureUnit provided as an input variable
    public static void setTemperatureUnitPreference(TemperatureUnit temperatureUnitPreference) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_CELSIUS_KEY, temperatureUnitPreference == TemperatureUnit.CELSIUS);
    }

    //Returns the current distance unit preference as a DistanceUnit
    public static DistanceUnit getDistanceUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_METER_KEY, true) ? DistanceUnit.METER : DistanceUnit.MILE;
    }

    //Sets the current distance unit preference to the DistanceUnit provided as an input variable
    public static void setDistanceUnitPreference(DistanceUnit distanceUnit) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_METER_KEY, distanceUnit == DistanceUnit.METER);
    }





}
