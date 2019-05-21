package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;

public class tipsPage {
    @FXML
    private Button backButtonTips;

    public void back() throws IOException {
        Main.navigateBackToMainPage();
    }

}

