package classes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;



/* This is an abstract weather information class which takes a JSON object
 * and maps it to a class which encapsulates all functionality. */
@EqualsAndHashCode(callSuper = true)
@Data
public class AbstractWeatherInformation extends AbstractWeatherResponse {
    @SerializedName("main")
    public MainParameters mainParameters;
    public Wind wind;
    public Clouds clouds;
    public Rain rain;
    public Snow snow;
}
