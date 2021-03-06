package ui;

import classes.Location;
import classes.currentweather.CurrentWeather;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.shredzone.commons.suncalc.SunPosition;
import processes.UpdateAllLocations;
import utils.Conversions;
import utils.OWM;
import utils.scAPI;

import java.io.IOException;

public class ListItem {

    private Location location;


    @FXML
    private Pane container;

    @FXML
    private Button removeButton;

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


    // Used for updating the list item on the MainPage (see ListViewCell for usage)
    public void setInfo(Location location, boolean isCurrentLocation)
    {
        this.location = location;
        CurrentWeather weather = OWM.getCurrentWeather(location);
        SunPosition sunPosition = scAPI.getSunPositionNow(location);

        weatherIcon.setImage(weather.getIcon());
        locationValue.setText(weather.cityName);
        temperatureValue.setText(Conversions.convertToPreferredTemperature(weather.mainParameters.temperature));
        visibilityValue.setText(Conversions.convertToPreferredDistance(Double.valueOf(weather.visibility)));
        if(weather.rain == null){
            chanceOfRainValue.setText("0%");
        } else{
            chanceOfRainValue.setText(weather.rain.rainAmt + "%");
        }
        sunPositionValue.setText(Conversions.roundDouble(sunPosition.getAltitude()) + "°");
        cloudCoverValue.setText(Math.round(weather.clouds.cloudiness) + "%");
        currentLocationIcon.setVisible(isCurrentLocation);
        removeButton.setVisible(!isCurrentLocation);
    }

    public Pane getContainer()
    {
        return container;
    }


    // When remove button is clicked on the tile, remove the button and reload the page
    @FXML
    private void removeButtonClicked() throws IOException {
        UpdateAllLocations.getUwa().removeLocation(location);
        Main.navigateBackToMainPage();
    }


    // If we click on a weather tile, show the details of it
    @FXML
    private void weatherTileClicked() throws IOException {
        Main.navigateToDetails(location);
    }
}
