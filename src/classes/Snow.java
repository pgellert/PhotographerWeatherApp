package classes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


/*This encapsulates all the information related to snow levels*/
@Data
public class Snow {
    /**
     * Snow volume for the last 3 hours
     */
    @SerializedName("3h")
    public double volumeSnow;
}
