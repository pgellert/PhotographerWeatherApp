package utils;

import javafx.scene.image.Image;
import sample.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Background {

    public static Image getBackgroundImage(String weatherDesc){
        try {
            switch (weatherDesc){
                case "few clouds":
                case "scattered clouds":
                case "broken clouds":
                    return new Image(new FileInputStream("src/res/background/cloud.jpg"), Main.PAGE_WIDTH, Main.PAGE_HEIGHT, true, true);
                case "shower rain":
                case "rain":
                case "thunderstorm":
                    return new Image(new FileInputStream("src/res/background/rain.jpg"), Main.PAGE_WIDTH, Main.PAGE_HEIGHT, true, true);
                case "snow":
                    return new Image(new FileInputStream("src/res/background/snow.jpg"), Main.PAGE_WIDTH, Main.PAGE_HEIGHT, true, true);
                case "mist":
                    return new Image(new FileInputStream("src/res/background/mist.jpg"), Main.PAGE_WIDTH, Main.PAGE_HEIGHT, true, true);
                case "clear sky":
                default:
                    return new Image(new FileInputStream("src/res/background/sun.jpg"), Main.PAGE_WIDTH, Main.PAGE_HEIGHT, true, true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
