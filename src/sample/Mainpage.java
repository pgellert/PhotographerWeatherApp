package sample;

import classes.Location;
import classes.currentweather.CurrentWeather;
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
    private ListView listView;

    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private void navigateToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../formihnnea2/src/sample/settingspage.fxml"));
        Main.stage.setScene(new Scene(root, 400, 700));
        Main.stage.show();

    }

    @Override
    public void initialize(URL locationUrl, ResourceBundle resources) {
        /*
        TODO:
         - load list of locations -> populate weather data list
         - load background image for current location's weather data
         */



        /*
        // Set background image
        BackgroundImage myBI= null;

        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("pics/background.jpg")),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        container.setBackground(new Background(myBI));

         */


        UpdateAllLocations.getUwa().addLocation(Location.fromName("Cambridge, UK"));
        UpdateAllLocations.getUwa().addLocation(Location.fromName("Bristol, UK"));
        UpdateAllLocations.getUwa().addLocation(Location.fromName("Budapest"));


        boolean isFirst = true;
        // Populate list
        /*
        for (Location location : UpdateAllLocations.getUwa().getLocations()) {
            System.out.println(location);
            observableList.add(new WeatherTileData(location, isFirst));
            isFirst = false;
        }
        */


        // If we pass a null into the list, it creates an Add New Location Tile
        observableList.add(null);
        listView.setItems(observableList);
        listView.setCellFactory((Callback<ListView<WeatherTileData>, ListCell<WeatherTileData>>) listView -> new ListViewCell());
    }



}
