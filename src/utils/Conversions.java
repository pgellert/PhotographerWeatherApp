package utils;

public class Conversions {

    public static double roundDouble(double toRound) {
        return Math.round(toRound*10d)/10d;
    }

    public static String convertToPreferredTemperature(double temperatureInKelvin){
        if(Settings.getTemperatureUnitPreference() == Settings.TemperatureUnit.CELSIUS){
            return toCelsius(temperatureInKelvin) + "°C";
        } else {
            return toFahrenheit(temperatureInKelvin) + "°F";
        }
    }

    public static String convertToPreferredDistance(double distanceInMeter){
        if(Settings.getDistanceUnitPreference() == Settings.DistanceUnit.MILE){
            return toMiles(distanceInMeter) + "mi";
        } else {
            return Math.round(distanceInMeter / 1000) + "km";
        }
    }

    private static double toKilometres(double metres){
        return roundDouble(metres / 1000);
    }

    private static double toMiles(double metres){
        return roundDouble(metres / 1000 * 0.621371);
    }

    private static double toCelsius(double kelvin){
        return roundDouble(kelvin -273.15);
    }

    private static double toFahrenheit(double kelvin){
        return roundDouble((kelvin * 1.8) - 459.67);
    }
}
