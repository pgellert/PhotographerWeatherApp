package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;

public class tipsPage {
    @FXML
    private Button backButtonTips;

    public void back() throws IOException {
        Main.navigateBackToMainPage();
    }

    public void initialise(){
        Image image = new Image("res/pics/hamburger.png", backButtonTips.getWidth(), backButtonTips.getHeight(), false, true, true);
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(backButtonTips.getWidth(), backButtonTips.getHeight(), true, true, true, false));
        backButtonTips.setMinSize(55, 37);
        backButtonTips.setMaxSize(55, 37);

        Background backGround = new Background(bImage);
        backButtonTips.setBackground(backGround);

    }

}

