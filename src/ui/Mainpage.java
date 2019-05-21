package ui;

import classes.Location;
import classes.currentweather.CurrentWeather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Callback;
import processes.UpdateAllLocations;
import utils.OWM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Mainpage implements Initializable {

    @FXML
    private Pane container;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuButton btnHamburger;

    @FXML
    private ListView listView;

    public static ObservableList observableList = FXCollections.observableArrayList();

    // if we click on Settings menu item, navigate to SettingsPage
    @FXML
    private void navigateToSettings(ActionEvent event) throws IOException {
        Main.navigateToSettings();
    }

    // if we click on Tips menu item, navigate to TipsPage
    @FXML
    private void navigateToTips(ActionEvent event) throws IOException {
        Main.navigateToTips();
    }

    @Override
    public void initialize(URL locationUrl, ResourceBundle resources) {

        // Set settings image to hamburger.png

        Image image = new Image("res/pics/hamburger.png", btnHamburger.getWidth(), btnHamburger.getHeight(), false, true, true);
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(btnHamburger.getWidth(), btnHamburger.getHeight(), true, true, true, false));
        btnHamburger.setMinSize(55, 37);
        btnHamburger.setMaxSize(55, 37);

        Background backGround = new Background(bImage);
        btnHamburger.setBackground(backGround);




        // Populate list

        observableList.clear();
        boolean isFirst = true;     // The first item is the current location, and it should show a logo to signal that
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
