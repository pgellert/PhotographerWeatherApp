package utils;

import java.util.prefs.Preferences;

public class Settings {

    private static final String IS_CELSIUS_KEY = "TEMPERATURE_UNIT_KEY";
    private static final String IS_METER_KEY = "DISTANCE_UNIT_KEY";

    public enum TemperatureUnit {CELSIUS, KELVIN, FAHRENHEIT}
    public enum DistanceUnit {METER, MILE}

    public static TemperatureUnit getTemperatureUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_CELSIUS_KEY, true) ? TemperatureUnit.CELSIUS : TemperatureUnit.FAHRENHEIT;
    }

    public static void setTemperatureUnitPreference(TemperatureUnit temperatureUnitPreference) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_CELSIUS_KEY, temperatureUnitPreference == TemperatureUnit.CELSIUS);
    }


    public static DistanceUnit getDistanceUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_METER_KEY, true) ? DistanceUnit.METER : DistanceUnit.MILE;
    }

    public static void setDistanceUnitPreference(DistanceUnit distanceUnit) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_METER_KEY, distanceUnit == DistanceUnit.METER);
    }





}
