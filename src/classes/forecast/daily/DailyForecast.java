package classes.forecast.daily;

import classes.Clouds;
import classes.Rain;
import classes.Weather;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import classes.AbstractWeatherResponse;
import classes.forecast.Forecast;
import classes.forecast.ForecastInformation;
import utils.QueryParser;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DailyForecast extends AbstractWeatherResponse implements Forecast {
    public static final Type TYPE = new TypeToken<ForecastInformation<DailyForecast>>() {
    }.getType();
    public static final Type TYPE_LIST = TypeToken.getParameterized(List.class, TYPE).getType();

    @SerializedName("temp")
    public Temperature temperature;
    public double humidity;
    public double speed;
    public Clouds clouds;
    public Rain rain;
    @SerializedName("deg")
    public double direction;

    public Weather getWeather(){
        return weather.get(0);
    }

    public Image getIcon(){
        return QueryParser.getIcon(weather.get(0).icon);
    }

}
