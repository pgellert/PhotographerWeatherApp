package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    public Pane getContainer()
    {
        return container;
    }


    @FXML
    public void addLocationClicked(MouseEvent arg0) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../formihnnea2/src/sample/searchpage.fxml"));
        Main.stage.setScene(new Scene(root, 400, 700));
        Main.stage.show();
    }
}
