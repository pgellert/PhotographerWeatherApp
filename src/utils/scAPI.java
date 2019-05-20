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
        use .azimuth and .attitude to get the
     */
    public static SunPosition getSunPositionNow(Location location) {
        double lat = Double.parseDouble(location.coordinate.latitude);
        double lon = Double.parseDouble(location.coordinate.longitude);
        return getSunPositionNow(lat, lon);
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

    public static List<SunPosition> getSunPositionsDay(Location location) {
        double lat = Double.parseDouble(location.coordinate.latitude);
        double lon = Double.parseDouble(location.coordinate.longitude);
        return getSunPositionsDay(lat, lon);
    }

    /*
    Returns a list of 24 SunPositions for the current day at 0:00, 1:00, etc. up to 23:00
     */

    public static List<SunPosition> getSunPositionsDay(double lat, double lon) {
        SunPosition.Parameters positionBuilder = SunPosition.compute();
        positionBuilder = positionBuilder.latitude(lat).longitude(lon);
        List<SunPosition> sunPositionsDay = new ArrayList<>();
        for(int i =0; i < 24; i++) {
            LocalDate dateToday =LocalDate.now();
            positionBuilder.on(dateToday.getYear(), dateToday.getMonthValue(), dateToday.getDayOfMonth(), i, 0, 0 );
            sunPositionsDay.add(positionBuilder.execute());
        }
        return sunPositionsDay;
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
        return getSunTimesToday(lat, lon);
    }

    /*
        Returns a List of SunTimes for next week at given lat/long position
        to get sunrise/sunset time use getRise and getSet
     */
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

    public static List<SunTimes> getSunTimesWeek(Location location) {
        double lat = Double.parseDouble(location.coordinate.latitude);
        double lon = Double.parseDouble(location.coordinate.longitude);
        return getSunTimesWeek(lat, lon);
    }


    public static void main(String[] args) {
        System.out.println(getSunPositionNow(Location.fromName("Moscow")).getAltitude());
        List<SunPosition> sunPositions = getSunPositionsDay(Location.fromName("Moscow"));
        for(SunPosition sunPosition : sunPositions) {
            System.out.println(sunPosition.getAltitude());
        }
    }
}
