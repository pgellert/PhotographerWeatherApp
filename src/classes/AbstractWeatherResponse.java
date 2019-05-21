package classes;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import classes.gson.SecondsDateTypeAdapter;

import java.util.Date;
import java.util.List;


/* This takes a JSON object and encapsulates it. */
@Data
public class AbstractWeatherResponse {
    @JsonAdapter(SecondsDateTypeAdapter.class)
    @SerializedName("dt")
    public Date dateTime;
    public List<Weather> weather;
}
