package sample;

import classes.Location;
import classes.currentweather.CurrentWeather;
import classes.forecast.ForecastInformationDay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.shredzone.commons.suncalc.SunPosition;
import processes.UpdateAllLocations;
import utils.OWM;
import utils.scAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ListItem {

    private Location location;


    @FXML
    private Pane container;

    @FXML
    private Label locationValue;
    @FXML
    private Label temperatureValue;
    @FXML
    private Label visibilityValue;
    @FXML
    private Label chanceOfRainValue;
    @FXML
    private Label sunPositionValue;
    @FXML
    private Label cloudCoverValue;
    @FXML
    private ImageView weatherIcon;
    @FXML
    private ImageView currentLocationIcon;


    public ListItem()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listitem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Location location, boolean isCurrentLocation)
    {
        this.location = location;
        CurrentWeather weather = OWM.getCurrentWeather(location);
        SunPosition sunPosition = scAPI.getSunPositionNow(location);

        /*
            TODO: change from fixed value
             - weather icon
             - chance of rain
             - sun position
         */



        try {
            weatherIcon.setImage(new Image(new FileInputStream("pics/" + weather.weather.get(0).description + ".png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        locationValue.setText(weather.cityName);
        temperatureValue.setText(String.valueOf(weather.mainParameters.temperature));
        visibilityValue.setText(weather.visibility);
        chanceOfRainValue.setText(String.valueOf(weather.rain.rainAmt));
        sunPositionValue.setText(String.valueOf(sunPosition.getAzimuth()));
        cloudCoverValue.setText(String.valueOf(weather.clouds.cloudiness));
        currentLocationIcon.setVisible(isCurrentLocation);
    }

    public Pane getContainer()
    {
        return container;
    }

    @FXML
    private void removeButtonClicked() throws IOException {
        UpdateAllLocations.getUwa().removeLocation(location);
    }

    @FXML
    private void weatherTileClicked() throws IOException {
        Main.navigateToDetails(location);
    }
}
