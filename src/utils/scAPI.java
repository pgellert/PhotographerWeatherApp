package utils;

import classes.Location;
import org.shredzone.commons.suncalc.SunPosition;
import org.shredzone.commons.suncalc.SunTimes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class scAPI {

    /*
        Returns a SunPosition class for current time at given location
     */
    public static SunPosition getSunPositionNow(Location location) {
        double lat = Double.parseDouble(location.coordinate.latitude);
        double lon = Double.parseDouble(location.coordinate.longitude);
        SunPosition.Parameters positionBuilder = SunPosition.compute();
        positionBuilder.localTime();
        positionBuilder = positionBuilder.now();
        positionBuilder = positionBuilder.latitude(lat).longitude(lon);

        return positionBuilder.execute();
    }

    /*
        Returns a SunPosition class for current time at given lat/long position
     */
    public static SunPosition getSunPositionNow(double lat, double lon) {
        SunPosition.Parameters positionBuilder = SunPosition.compute();
        positionBuilder.localTime();
        positionBuilder = positionBuilder.now();
        positionBuilder = positionBuilder.latitude(lat).longitude(lon);
        return positionBuilder.execute();
    }

    /*
        Returns a SunTimes class for current day at given lat/long position
        deprecated - use ForecastInformationDay for sunrise/set instead
    */
    @Deprecated
    public static SunTimes getSunTimesToday(double lat, double lon) {
        SunTimes.Parameters timesBuilder = SunTimes.compute();
        timesBuilder.localTime();
        timesBuilder = timesBuilder.today();
        timesBuilder = timesBuilder.latitude(lat).longitude(lon);
        return timesBuilder.execute();
    }

    /*
       Returns a SunTimes class for current day at given location
       deprecated - use ForecastInformationDay for sunrise/set instead
   */
    @Deprecated
    public static SunTimes getSunTimesToday(Location location) {
        double lat = Double.parseDouble(location.coordinate.latitude);
        double lon = Double.parseDouble(location.coordinate.longitude);
        SunTimes.Parameters timesBuilder = SunTimes.compute();
        timesBuilder.localTime();
        timesBuilder = timesBuilder.today();
        timesBuilder = timesBuilder.latitude(lat).longitude(lon);
        return timesBuilder.execute();
    }

    /*
        Returns a List of SunTimes for next week at given lat/long position
        deprecated - use ForecastInformationWeek for sunrise/set times instead
     */
    @Deprecated
    public static List<SunTimes> getSunTimesWeek(double lat, double lon) {
        SunTimes.Parameters timesWeekBuilder = SunTimes.compute();
        timesWeekBuilder = timesWeekBuilder.longitude(lon).latitude(lat);
        List<SunTimes> sunTimesWeek = new ArrayList<>();
        LocalDate day = LocalDate.now();
        for(int i = 0; i < 7; i++) {
            timesWeekBuilder.on(Date.valueOf(day));
            SunTimes dayTimes = timesWeekBuilder.execute();
            sunTimesWeek.add(dayTimes);
            day = day.plusDays(1);
        }

        return sunTimesWeek;
    }

    public static void main(String[] args) {
        SunPosition sunPosition = getSunPositionNow(52.2043979,0.1218338);
        System.out.println(sunPosition.getAltitude());
        System.out.println(sunPosition.getAzimuth());

    }

}
