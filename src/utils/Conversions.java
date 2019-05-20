package utils;

public class Conversions {

    public static double toKilometres(double metres){
        return metres / 1000;
    }

    public static double toMiles(double metres){
        return metres / 1000 * 0.621371;
    }

    public static double toCelsius(double kelvin){
        return kelvin -273.15;
    }

    public static double toFahrenheit(double kelvin){
        return (kelvin * 1.8) - 459.67;
    }
}
