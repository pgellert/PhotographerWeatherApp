package processes;

import classes.Location;
import classes.currentweather.CurrentWeather;
import classes.forecast.ForecastInformationDay;
import classes.forecast.ForecastInformationWeek;
import org.shredzone.commons.suncalc.SunPosition;
import utils.LocationFinder;
import utils.OWM;
import utils.Search;
import utils.scAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UpdateAllLocations {
    private static final String LOCATION_LIST_FILE_NAME = "location_list.txt";

    private Map<Location, ForecastInformationWeek> dailyForecasts = new HashMap<>();
    private Map<Location, ForecastInformationDay> hourlyForecasts = new HashMap<>();
    private Map<Location, CurrentWeather> allCurrentWeather = new HashMap<>();
    private Map<Location, SunPosition> currentSunPositions = new HashMap<>();

    private List<Location> locations = new ArrayList<>();

    private static UpdateAllLocations uwa;
    private ScheduledExecutorService executor;


    private Runnable updateAllHourly = () -> {
        Map<Location, ForecastInformationDay> newHourlyForecasts = new HashMap<>();
        for(Location location: hourlyForecasts.keySet()){
            newHourlyForecasts.put(location, OWM.getDayForecast(location));
        }
        hourlyForecasts = newHourlyForecasts;
    };

    private Runnable updateAllDaily = () -> {
        Map<Location, ForecastInformationWeek> newDailyForecasts = new HashMap<>();
        for(Location location: dailyForecasts.keySet()){
            newDailyForecasts.put(location, OWM.getWeekForecast(location));
        }
        dailyForecasts = newDailyForecasts;
    };

    private Runnable updateAllCurrent = () -> {
        Map<Location, CurrentWeather> newCurrentWeather = new HashMap<>();
        for(Location location: allCurrentWeather.keySet()){
            newCurrentWeather.put(location, OWM.getCurrentWeather(location));
        }
        allCurrentWeather = newCurrentWeather;
    };

    private Runnable updateAllSunPositions = () -> {
        Map<Location, SunPosition> newSunPositions = new HashMap<>();
        for(Location location: currentSunPositions.keySet()) {
            newSunPositions.put(location, scAPI.getSunPositionNow(location));
        }
        currentSunPositions = newSunPositions;
    };


    private UpdateAllLocations(){
        loadLocations();
        addLocation(LocationFinder.getCurrentLocation());

        executor = Executors.newScheduledThreadPool(3);
        executor.schedule(updateAllDaily, 10L, TimeUnit.MINUTES);
        executor.schedule(updateAllHourly, 10L, TimeUnit.MINUTES);
        executor.schedule(updateAllCurrent, 5L, TimeUnit.MINUTES);
        executor.schedule(updateAllSunPositions, 5L, TimeUnit.MINUTES);
    }


    public void addLocation(Location location){
        if (location.coordinate == null){
            if (location.googleId == null){
                location.googleId = Search.autoCompleteInput(location.name).get(0).googleId;
            }
            location.coordinate = Search.getCoords(location.googleId);
        }
        locations.add(location);
        dailyForecasts.put(location, OWM.getWeekForecast(location));
        hourlyForecasts.put(location, OWM.getDayForecast(location));
        allCurrentWeather.put(location, OWM.getCurrentWeather(location));
        currentSunPositions.put(location, scAPI.getSunPositionNow(location));

        saveLocations();
    }

    public void removeLocation(Location location){
        locations.remove(location);
        dailyForecasts.remove(location);
        hourlyForecasts.remove(location);
        allCurrentWeather.remove(location);
        currentSunPositions.remove(location);

        saveLocations();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public ForecastInformationWeek getDaily(Location location){
        if (dailyForecasts.containsKey(location)) {
            return dailyForecasts.get(location);
        } else {
            System.out.println("No forecast exists for this yet.");
        }
        return null;
    }

    public ForecastInformationDay getHourly(Location location){
        if (hourlyForecasts.containsKey(location)) {
            return hourlyForecasts.get(location);
        } else {
            System.out.println("No forecast exists for this yet.");
        }
        return null;
    }

    public CurrentWeather getCurrent(Location location){
        if (allCurrentWeather.containsKey(location)) {
            return allCurrentWeather.get(location);
        } else {
            System.out.println("Location not found");
        }
        return null;
    }

    public SunPosition getSunPosition(Location location){
        if (currentSunPositions.containsKey(location)) {
            return currentSunPositions.get(location);
        } else {
            System.out.println("Location not found");
        }
        return null;
    }

    public void closeProcess(){
        executor.shutdown();
        uwa = null;
    }

    public static UpdateAllLocations getUwa(){
        if (uwa != null) {
            return uwa;
        } else {
            uwa = new UpdateAllLocations();
        }
        return uwa;
    }


    private void saveLocations(){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(LOCATION_LIST_FILE_NAME))){
            pw.flush();
            for (Location location : locations.subList(1,locations.size()))
                pw.println(location.name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadLocations(){
        try (BufferedReader file = new BufferedReader(new FileReader (LOCATION_LIST_FILE_NAME))){
            String line;
            while ((line = file.readLine()) != null){
                locations.add(Location.fromName(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
