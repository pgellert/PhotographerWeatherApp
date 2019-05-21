package utils;

public class Conversions {

    //rounds the double on the input to one decimal place
    public static double roundDouble(double toRound) {
        return Math.round(toRound*10d)/10d;
    }

    //converts the input temperature in Kelvin to the current prefered Temperature unit with symbol, as kept in settings
    public static String convertToPreferredTemperature(double temperatureInKelvin){
        if(Settings.getTemperatureUnitPreference() == Settings.TemperatureUnit.CELSIUS){
            return toCelsius(temperatureInKelvin) + "°C";
        } else {
            return toFahrenheit(temperatureInKelvin) + "°F";
        }
    }

    //converts the input distance in metres to the current prefered Distance unit with symbol, as kept in settings
    public static String convertToPreferredDistance(double distanceInMeter){
        if(Settings.getDistanceUnitPreference() == Settings.DistanceUnit.MILE){
            return toMiles(distanceInMeter) + "mi";
        } else {
            return Math.round(distanceInMeter / 1000) + "km";
        }
    }

    //converts the input distance in metres to kilometres and returns that
    private static double toKilometres(double metres){
        return roundDouble(metres / 1000);
    }

    //converts the input distance in metres to miles and returns that
    private static double toMiles(double metres){
        return roundDouble(metres / 1000 * 0.621371);
    }

    //converts the input temperature in kelvin to degrees Celsius
    private static double toCelsius(double kelvin){
        return roundDouble(kelvin -273.15);
    }

    //converts the input temperature in kelvin to degrees Celsius
    private static double toFahrenheit(double kelvin){
        return roundDouble((kelvin * 1.8) - 459.67);
    }
}
