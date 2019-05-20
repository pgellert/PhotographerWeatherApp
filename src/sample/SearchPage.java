package sample;

import classes.Location;
import classes.currentweather.CurrentWeather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.shredzone.commons.suncalc.SunPosition;
import utils.OWM;
import utils.Search;
import utils.scAPI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPage implements Initializable {

    @FXML
    private TextField locationTextField;

    @FXML
    private ListView suggestionsList;

    ObservableList observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Location> suggestions = Search.autoCompleteInput(oldValue);

            observableList.clear();
            observableList.addAll(suggestions);
            suggestionsList.setItems(observableList);
            suggestionsList.setCellFactory((Callback<javafx.scene.control.ListView<Location>, ListCell<Location>>) listView -> new SettingsSuggestionCell());
            //suggestionsList.setCellFactory(());
        });
    }

    @FXML
    private void backButtonClicked() throws IOException {
        Main.navigateBackToMainPage();
    }


    private class SettingsSuggestionCell extends ListCell<Location> {
        @Override
        public void updateItem(Location data, boolean empty)
        {
            super.updateItem(data, empty);
            if(data != null)
            {
                SuggestionsListItem listItem = new SuggestionsListItem();
                listItem.setInfo(data);
                setGraphic(listItem.getContainer());
            }
        }
    }
}
