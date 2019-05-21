package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import utils.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Settingspage implements Initializable {

    @FXML
    private ToggleButton tempCelsius;

    @FXML
    private ToggleButton tempFahr;

    @FXML
    private ToggleButton distKilom;

    @FXML
    private ToggleButton distMiles;

    @FXML
    private Button backButtonSettings;


    public void back() throws IOException {
        Main.navigateBackToMainPage();
    }

    public void btnCelsius() {
        Settings.setTemperatureUnitPreference(Settings.TemperatureUnit.CELSIUS);
    }

    public void btnFahrenheit() {
        Settings.setTemperatureUnitPreference(Settings.TemperatureUnit.FAHRENHEIT);
    }

    public void btnKm() {
        Settings.setDistanceUnitPreference(Settings.DistanceUnit.METER);
    }

    public void btnMiles() {
        Settings.setDistanceUnitPreference(Settings.DistanceUnit.MILE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Settings.getTemperatureUnitPreference() == Settings.TemperatureUnit.CELSIUS){
            tempCelsius.setSelected(true);
        } else {
            tempFahr.setSelected(true);
        }

        if(Settings.getDistanceUnitPreference() == Settings.DistanceUnit.METER){
            distKilom.setSelected(true);
        } else {
            distMiles.setSelected(true);
        }

        Image image = new Image("res/pics/back.png", backButtonSettings.getWidth(), backButtonSettings.getHeight(), false, true, true);
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(backButtonSettings.getWidth(), backButtonSettings.getHeight(), true, true, true, false));
        backButtonSettings.setMinSize(55, 37);
        backButtonSettings.setMaxSize(55, 37);

        Background backGround = new Background(bImage);
        backButtonSettings.setBackground(backGround);

    }
}
