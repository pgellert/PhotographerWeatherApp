package classes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


/*Separate JSON encapsulation for the cloudiness API calls.*/
@Data
public class Clouds {
    /**
     * Cloudiness, %
     */
    @SerializedName("all")
    public double cloudiness;
    
    
}
