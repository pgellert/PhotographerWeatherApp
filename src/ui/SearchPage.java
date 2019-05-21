package ui;

import classes.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import utils.Search;

import java.io.IOException;
import java.util.List;

public class SearchPage {

    @FXML
    private TextField locationTextField;

    @FXML
    private ListView suggestionsList;

    // Suggestion list loaded into the ListView
    ObservableList observableList = FXCollections.observableArrayList();

    // If back button clicked, navigate back to MainPage
    @FXML
    private void backButtonClicked() throws IOException {
        Main.navigateBackToMainPage();
    }


    // If search button is clicked, show location suggestions
    @FXML
    private void searchButtonClicked() throws IOException {
        List<Location> suggestions = Search.autoCompleteInput(locationTextField.getText());

        observableList.clear();
        observableList.addAll(suggestions);
        suggestionsList.setItems(observableList);
        suggestionsList.setCellFactory((Callback<javafx.scene.control.ListView<Location>, ListCell<Location>>) listView -> new SettingsSuggestionCell());
    }




    // Custom ListItem for suggestion list
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
