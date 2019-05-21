package utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

import classes.coords.Coordinate;
import classes.currentweather.CurrentWeather;
import classes.forecast.ForecastInformationDay;
import classes.forecast.ForecastInformationWeek;
import classes.forecast.daily.DailyForecast;
import classes.forecast.hourly.HourlyForecast;
import com.google.gson.*;
import org.json.simple.JSONObject;
import classes.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static utils.QueryParser.callQuery;


public class OWM {

    private static final String api_id_owm = "d3701ecc1c0c1202a1e0add340d8d345";

    public static String getIdFromLatLong(Coordinate coordinate){
        String query;

        if (coordinate == null){
            System.out.println("Null coordinates");
            return null;
        } else if ((coordinate.longitude == null) || (coordinate.latitude == null)){
            System.out.println("Invalid coordinates");
            return null;
        } else {
            query = "http://api.openweathermap.org/data/2.5/weather?lat=" + coordinate.latitude + "&lon=" + coordinate.longitude + "&APPID=" + api_id_owm;
        }

        System.out.println(query);

        JSONObject jobj = callQuery(query);

        Gson g = new Gson();

        JsonObject jsonObject = g.fromJson(jobj.toJSONString(), JsonObject.class);

        return jsonObject.get("id").getAsString();
    }

    public static ForecastInformationWeek getWeekForecast(Location location) {

        String query;

        if (location == null){
            System.out.println("Null location");
            return null;
        }

        if ((location.coordinate != null) && (location.coordinate.longitude != null & location.coordinate.latitude != null))
        {
            query = "http://api.openweathermap.org/data/2.5/forecast?lon=" + location.coordinate.longitude+ "&lat=" + location.coordinate.latitude + "&APPID=" + api_id_owm;
        } else if (location.owmId != null){
            query = "http://api.openweathermap.org/data/2.5/forecast?id=" + location.owmId+ "&APPID=" + api_id_owm;
        } else if (location.name != null){
            query = "http://api.openweathermap.org/data/2.5/forecast?q=" + location.name.replace(" ", "%20") + "&APPID=" + api_id_owm;
        } else {
            System.out.println("Invalid location");
            return null;
        }

        JSONObject jsonObject = callQuery(query);

        Gson g = new Gson();

        //System.out.println(jsonObject);

        ForecastInformationWeek dailyForecast = g.fromJson(jsonObject.toJSONString(), ForecastInformationWeek.class);

        //Let us remove all entries we don't need because they are not valid datapoints for a week forecast.

        Iterator<DailyForecast> forecastIterator = dailyForecast.forecasts.iterator();

        DailyForecast df = forecastIterator.next();
        LocalDateTime lastDay = LocalDateTime.ofInstant(df.dateTime.toInstant(), ZoneId.systemDefault());

        while (forecastIterator.hasNext()){
            df = forecastIterator.next();
            LocalDateTime ldt = LocalDateTime.ofInstant(df.dateTime.toInstant(), ZoneId.systemDefault());

            if (DAYS.between(lastDay, ldt) < 1){
                forecastIterator.remove();
            } else {
                lastDay = ldt;
            }
        }

        return dailyForecast;
    }

    public static ForecastInformationDay getDayForecast(Location location){
        String query;

        if (location == null){
            System.out.println("Null location");
            return null;
        }

        if ((location.coordinate != null) && (location.coordinate.longitude != null & location.coordinate.latitude != null)){
            query = "http://api.openweathermap.org/data/2.5/forecast?lon=" + location.coordinate.longitude+ "&lat=" + location.coordinate.latitude + "&APPID=" + api_id_owm;
        } else if (location.owmId != null){
            query = "http://api.openweathermap.org/data/2.5/forecast?id=" + location.owmId+ "&APPID=" + api_id_owm;
        } else if (location.name != null){
            query = "http://api.openweathermap.org/data/2.5/forecast?q=" + location.name.replace(" ", "%20") + "&APPID=" + api_id_owm;
        } else {
            System.out.println("Invalid location");
            return null;
        }

        JSONObject jsonObject = callQuery(query);

        Gson g = new Gson();

        //System.out.println(jsonObject);

        ForecastInformationDay hourlyForecast = g.fromJson(jsonObject.toJSONString(), ForecastInformationDay.class);

        //Let us remove all entries we don't need because they are not valid datapoints for a week forecast.

        Iterator<HourlyForecast> forecastIterator = hourlyForecast.forecasts.iterator();

        hourlyForecast.forecasts = hourlyForecast.forecasts.subList(0,8);

        /*
        while (forecastIterator.hasNext()){
            HourlyForecast hf = forecastIterator.next();
            LocalDateTime ldt = LocalDateTime.ofInstant(hf.dateTime.toInstant(), ZoneId.systemDefault());
            if (DAYS.between(LocalDateTime.now(), ldt) >= 1){
                forecastIterator.remove();
            }
        }

         */

        return hourlyForecast;
    }


    public static CurrentWeather getCurrentWeather(Location location){
        String query;

        if ((location.coordinate != null) && (location.coordinate.longitude != null & location.coordinate.latitude != null)){
            query = "http://api.openweathermap.org/data/2.5/weather?lon=" + location.coordinate.longitude+ "&lat=" + location.coordinate.latitude + "&APPID=" + api_id_owm;
        } else if (location.owmId != null){
            query = "http://api.openweathermap.org/data/2.5/weather?id=" + location.owmId+ "&APPID=" + api_id_owm;
        } else if (location.name != null){
            query = "http://api.openweathermap.org/data/2.5/weather?q=" + location.name.replace(" ", "%20") + "&APPID=" + api_id_owm;
        } else {
            System.out.println("Invalid location");
            return null;
        }

        JSONObject jsonObject = callQuery(query);

        Gson g = new Gson();
        CurrentWeather currentWeather = g.fromJson(jsonObject.toJSONString(), CurrentWeather.class);
        return currentWeather;
    }

    public static void main(String[] args) {
        //How to get CurrentWeather object
        CurrentWeather currentWeather = OWM.getCurrentWeather( Location.fromName("Berlin"));

        //How to get specific forecast datapoint (ie Thursday 1pm) from ForecastInformationWeek object
        DailyForecast dailyForecast = OWM.getWeekForecast( Location.fromName("Paris")).forecasts.get(2);
        //0 can be changed to 0-4

        //How to get specific forecast datapoint (ie today/tomorrow 9am) from ForecastInformationDay object
        HourlyForecast hourlyForecast = OWM.getDayForecast(Location.fromName("Paris")).forecasts.get(2);
        //0 can be changed from 0-7 ?? i think

        //What data can we get from currentweather/day/hour forecast?

        System.out.println(currentWeather.dateTime); //Time of forecast
        System.out.println(currentWeather.clouds.cloudiness); //Cloud cover amount 0-1
        System.out.println(currentWeather.coordinate.latitude); //Latitude of place (similarly "longitude")
        System.out.println(currentWeather.rain != null ? currentWeather.rain.rainAmt : "N/A"); //Rain amount - null if no rain so use this to fix
        System.out.println(currentWeather.cityName); //Name of place
        System.out.println(currentWeather.visibility); //visibility
        System.out.println(currentWeather.snow); //amount of snow - may be null if no snow
        System.out.println(currentWeather.weather.get(0).description); //general desc of weather - use for main icon
        System.out.println(currentWeather.wind.speed); //wind speed (similarly "direction")
        System.out.println(currentWeather.mainParameters.temperature); //temperature, similarly:
        //mainParameters.minTemperature
        //mainParameters.maxTemperature
        //mainParameters.pressure
        //mainParameters.seaLevelPressure
        //mainParameters.groundLevelPressure
        //mainParameters.humidity
        System.out.println(currentWeather.systemParameters.sunrise);//sunrise, similarly:
        //systemParameters.sunset
        //systemParameters.country
        //System.out.println(QueryParser.getIcon(hourlyForecast.weather.get(0).icon)); //Date of forecast and time
        System.out.println(hourlyForecast.clouds.cloudiness);

        System.out.println(dailyForecast.clouds.cloudiness);
    }
}
