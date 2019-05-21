package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

// A page to show photographer related tips

public class tipsPage {
    @FXML
    private Button backButtonTips;

    // Move back to MainPage
    public void back() throws IOException {
        Main.navigateBackToMainPage();
    }

}

