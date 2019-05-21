package sample;

import classes.Location;
import classes.currentweather.CurrentWeather;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import processes.UpdateAllLocations;
import utils.OWM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


public class Mainpage implements Initializable {

    @FXML
    private Pane container;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuButton btnHamburger;

    private static boolean isAdded;

    @FXML
    private ListView listView;

    public static ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private void navigateToSettings(ActionEvent event) throws IOException {
        Main.navigateToSettings();
    }

    @FXML
    private void navigateToTips(ActionEvent event) throws IOException {
        Main.navigateToTips();
    }

    @Override
    public void initialize(URL locationUrl, ResourceBundle resources) {
        /*
        TODO:
         - load list of locations -> populate weather data list
         - load background image for current location's weather data
         */




        if (!(isAdded)) {
            UpdateAllLocations.getUwa().addLocation(Location.fromName("Cambridge, UK"));
            UpdateAllLocations.getUwa().addLocation(Location.fromName("Bristol, UK"));

            isAdded = true;
        }

        boolean isFirst = true;


        Image image = new Image("src/res/pics/hamburger.png", btnHamburger.getWidth(), btnHamburger.getHeight(), false, true, true);
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(btnHamburger.getWidth(), btnHamburger.getHeight(), true, true, true, false));
        btnHamburger.setMinSize(55, 37);
        btnHamburger.setMaxSize(55, 37);

        Background backGround = new Background(bImage);
        btnHamburger.setBackground(backGround);




        // Populate list
        observableList.clear();
        for (Location location : UpdateAllLocations.getUwa().getLocations()) {
            System.out.println(location);
            observableList.add(new WeatherTileData(location, isFirst));
            isFirst = false;
        }

        // If we pass a null into the list, it creates an Add New Location Tile
        observableList.add(null);



        listView.setItems(observableList);
        listView.setCellFactory((Callback<ListView<WeatherTileData>, ListCell<WeatherTileData>>) listView -> new ListViewCell());



        // Set background image
        Location location = UpdateAllLocations.getUwa().getLocations().get(0);

        CurrentWeather weather = OWM.getCurrentWeather(location);
        String weatherDesc = weather.weather.get(0).description;

        BackgroundImage backgroundImage = new BackgroundImage(utils.Background.getBackgroundImage(weatherDesc), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        container.setBackground(new Background(backgroundImage));

    }



}
