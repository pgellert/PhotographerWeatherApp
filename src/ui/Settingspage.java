package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
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
    }
}
