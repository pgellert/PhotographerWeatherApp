package utils;

public class Conversions {

    public static String convertToPreferredTemperature(double temperatureInKelvin){
        if(Settings.getTemperatureUnitPreference() == Settings.TemperatureUnit.Celsius){
            return toCelsius(temperatureInKelvin) + "°C";
        } else {
            return temperatureInKelvin + "K";
        }
    }

    public static String convertToPreferredDistance(double distanceInMeter){
        if(Settings.getDistanceUnitPreference() == Settings.DistanceUnit.Mile){
            return toMiles(distanceInMeter) + "m";
        } else {
            return Math.round(distanceInMeter / 1000) + "km";
        }
    }

    private static double toKilometres(double metres){
        return metres / 1000;
    }

    private static double toMiles(double metres){
        return metres / 1000 * 0.621371;
    }

    private static double toCelsius(double kelvin){
        return kelvin -273.15;
    }

    private static double toFahrenheit(double kelvin){
        return (kelvin * 1.8) - 459.67;
    }
}
