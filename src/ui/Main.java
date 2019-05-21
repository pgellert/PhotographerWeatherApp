package ui;

import classes.Location;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Search;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;

    public static final int PAGE_WIDTH = 479;
    public static final int PAGE_HEIGHT = 673;

    public static Location detailsPageLocation = Location.fromName("Cambridge,UK");
    private static Class<? extends Main> loaderClass;


    public static void navigateToDetails(Location location) throws IOException {
        System.out.println("goto details page");

        detailsPageLocation = Search.getCompletedLocation(location);

        Parent detailsPage = FXMLLoader.load(loaderClass.getResource("detailpage.fxml"));
        stage.setScene(new Scene(detailsPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateBackToMainPage() throws IOException {
        System.out.println("goto main page");

        Parent mainPage = FXMLLoader.load(loaderClass.getResource("mainpage.fxml"));
        stage.setScene(new Scene(mainPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateToSearchPage() throws IOException {
        System.out.println("goto search page");

        Parent searchPage = FXMLLoader.load(loaderClass.getResource("searchpage.fxml"));
        stage.setScene(new Scene(searchPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateToSettings() throws IOException {
        System.out.println("goto settings page");

        Parent settingPage = FXMLLoader.load(loaderClass.getResource("settingspage.fxml"));
        stage.setScene(new Scene(settingPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateToTips() throws IOException {
        System.out.println("goto tips page");

        Parent tipsPage = FXMLLoader.load(loaderClass.getResource("tipspage.fxml"));
        stage.setScene(new Scene(tipsPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loaderClass = getClass();

        this.stage = primaryStage;
        stage.setTitle("Weather App for Photographers");
        Parent mainPage = FXMLLoader.load(loaderClass.getResource("mainpage.fxml"));
        stage.setScene(new Scene(mainPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}