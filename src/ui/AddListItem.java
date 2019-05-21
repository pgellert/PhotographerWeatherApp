package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AddListItem {

    @FXML
    private Pane container;

    public AddListItem()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addlistitem.fxml"));
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


    // Used for loading the item into the list (see ListViewCell)
    public Pane getContainer()
    {
        return container;
    }


    // When the add location tile is clicked, move to the SearchPage
    @FXML
    public void addLocationClicked(MouseEvent arg0) throws IOException {
        Main.navigateToSearchPage();
    }
}
