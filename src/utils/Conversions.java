package utils;

public class Conversions {
    private static double kmtomiles_mult = 0.621371;
    private static double kelvintocelsius_add = -273.15;
    private static double kelvintofahrenheit_mult = 1.8;
    private static double kelvintofahrenheit_add = -459.67;

    public static double toMiles(double kilometres){
        return kilometres * kmtomiles_mult;
    }

    public static double toCelsius(double kelvin){
        return kelvin + kelvintocelsius_add;
    }

    public static double toFahrenheit(double kelvin){
        return (kelvin * kelvintofahrenheit_mult) + kelvintofahrenheit_add;
    }
}
