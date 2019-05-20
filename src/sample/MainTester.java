package sample;

import classes.Location;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainTester {
    public TextField txtCity;
    public TextField txtCountry;

    public void switchToDetails() throws IOException {
        System.out.println("goto details page");

        Location location = Location.fromName(txtCity.getText() + "," + txtCountry.getText());

        Main.navigateToDetails(location);
    }

}