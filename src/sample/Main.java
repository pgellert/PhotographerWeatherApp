package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public TextField txtCity;
    public TextField txtCountry;
    public Button btnSearch;
    public static Stage stage;
    private Parent root;
    private static String city;
    private static String country;


    public void switchToDetails() throws IOException {
        System.out.println("goto details page");
        city = txtCity.getText();
        country = txtCountry.getText();
        root = FXMLLoader.load(getClass().getResource("detailpage.fxml"));
        stage.setScene(new Scene(root, 479, 673));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 479, 673));
        stage.show();
    }

    public static String getCity(){
        return city;
    }

    public static void setCity(String city){
        Main.city = city;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country){
        Main.country = country;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        primaryStage.setTitle("Weather Photo");
        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
 */