package utils;

import java.util.prefs.Preferences;

public class Settings {

    private static final String IS_CELSIUS_KEY = "TEMPERATURE_UNIT_KEY";
    private static final String IS_METER_KEY = "TEMPERATURE_UNIT_KEY";

    enum TemperatureUnit {Celsius, Kelvin};
    enum DistanceUnit {Meter, Mile};

    public static TemperatureUnit getTemperatureUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_CELSIUS_KEY, true) ? TemperatureUnit.Celsius : TemperatureUnit.Kelvin;
    }

    public static void setTemperatureUnitPreference(TemperatureUnit temperatureUnitPreference) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_CELSIUS_KEY, temperatureUnitPreference == TemperatureUnit.Celsius);
    }


    public static DistanceUnit getDistanceUnitPreference() {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        return pref.getBoolean(IS_METER_KEY, true) ? DistanceUnit.Meter : DistanceUnit.Mile;
    }

    public static void setDistanceUnitPreference(DistanceUnit distanceUnit) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        pref.putBoolean(IS_METER_KEY, distanceUnit == DistanceUnit.Meter);
    }





}
