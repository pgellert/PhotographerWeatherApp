package classes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


/*Encapsulates all JSON data about the wind.*/
@Data
public class Wind {
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double direction;
}
