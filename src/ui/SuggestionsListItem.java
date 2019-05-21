package ui;

import classes.Location;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import processes.UpdateAllLocations;

import java.io.IOException;

public class SuggestionsListItem {

    @FXML
    private Pane container;

    @FXML
    private Label label;

    private Location location;

        public SuggestionsListItem()
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("suggestionslistitem.fxml"));
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

        public void setInfo(Location location)
        {
            this.location = location;
            label.setText(location.name);
        }

        public Pane getContainer()
        {
            return container;
        }

    @FXML
    private void locationItemClicked() throws IOException {
        UpdateAllLocations.getUwa().addLocation(location);
        Main.navigateToDetails(location);
    }
}
