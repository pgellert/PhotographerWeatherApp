package sample;

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

    private static Parent mainPage;
    private static Parent mainTesterPage;
    private static Parent detailsPage;
    private static Parent settingPage;
    private static Parent searchPage;

    private static final int PAGE_WIDTH = 479;
    private static final int PAGE_HEIGHT = 673;

    public static Location detailsPageLocation = Location.fromName("Cambridge,UK");


    public static void navigateToDetails(Location location) throws IOException {
        System.out.println("goto details page");

        detailsPageLocation = Search.getCompletedLocation(location);
        stage.setScene(new Scene(detailsPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateBackToMainPage() throws IOException {
        System.out.println("goto main page");

        stage.setScene(new Scene(mainPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateToSearchPage() throws IOException {
        System.out.println("goto search page");

        stage.setScene(new Scene(searchPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void navigateToSettings() throws IOException {
        System.out.println("goto settings page");

        stage.setScene(new Scene(settingPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainPage = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        mainTesterPage = FXMLLoader.load(getClass().getResource("main.fxml"));
        detailsPage = FXMLLoader.load(getClass().getResource("detailpage.fxml"));
        settingPage = FXMLLoader.load(getClass().getResource("settingspage.fxml"));
        searchPage = FXMLLoader.load(getClass().getResource("searchpage.fxml"));

        this.stage = primaryStage;
        stage.setTitle("Weather App for Photographers");
        loadMain();
        //loadMainTester(); //this is for the details page ui designers
    }

    private void loadMainTester(){
        // TODO: should be removed at the end
        stage.setScene(new Scene(mainTesterPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    private void loadMain(){
        stage.setScene(new Scene(mainPage, PAGE_WIDTH, PAGE_HEIGHT));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}