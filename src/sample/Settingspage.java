package sample;

import utils.Settings;

import java.io.IOException;


public class Settingspage {

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
}
