package ui;

import classes.currentweather.CurrentWeather;
import classes.forecast.hourly.HourlyForecast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.shredzone.commons.suncalc.SunPosition;
import utils.OWM;
import utils.Conversions;
import utils.scAPI;

import static ui.Main.*;



public class DetailsController{
    //all ids from the fxml file

    @FXML
    private Pane container;

    @FXML
    private Label sunPos7;

    @FXML
    private Label sunPos6;

    @FXML
    private Label sunPos5;

    @FXML
    private Label sunPos4;

    @FXML
    private Label sunPos3;

    @FXML
    private Label chanceOfRain;

    @FXML
    private Label sunPos2;

    @FXML
    private Label sunPos1;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label sunPos8;

    @FXML
    private Label vis7;

    @FXML
    private Label vis6;

    @FXML
    private ImageView btnBack;

    @FXML
    private ToggleButton btnWeekly;

    @FXML
    private Label vis5;

    @FXML
    private Label vis4;

    @FXML
    private Label vis3;

    @FXML
    private Label visibility;

    @FXML
    private Label vis2;

    @FXML
    private Label vis1;

    @FXML
    private Label cloudCover;

    @FXML
    private Label vis8;

    @FXML
    private Label sunPosition;

    @FXML
    private ToggleButton btnHourly;

    @FXML
    private Label sunrise;

    @FXML
    private Label locate;

    @FXML
    private Label temp2;

    @FXML
    private Label temp3;

    @FXML
    private Label temp1;

    @FXML
    private Label rain8;

    @FXML
    private Label cld7;

    @FXML
    private Label cld8;

    @FXML
    private Label rain4;

    @FXML
    private Label cld5;

    @FXML
    private Label rain5;

    @FXML
    private Label cld6;

    @FXML
    private Label rain6;

    @FXML
    private Label cld3;

    @FXML
    private Label rain7;

    @FXML
    private Label cld4;

    @FXML
    private Label cld1;

    @FXML
    private Label temperature;

    @FXML
    private Label cld2;

    @FXML
    private GridPane hourlyGrid;

    @FXML
    private Label rain1;

    @FXML
    private Label rain2;

    @FXML
    private Label rain3;

    @FXML
    private ToggleGroup timeGroup;

    @FXML
    private ImageView sunType;

    @FXML
    private Label temp6;

    @FXML
    private Label temp7;

    @FXML
    private ScrollPane horizScrollPane;

    @FXML
    private Label temp4;

    @FXML
    private Label temp5;

    @FXML
    private Label temp8;

    @FXML
    private Label sunset;

    @FXML
    private Label time1;

    @FXML
    private Label time2;

    @FXML
    private Label time3;

    @FXML
    private Label time4;

    @FXML
    private Label time5;

    @FXML
    private Label time6;

    @FXML
    private Label time7;

    @FXML
    private Label time8;

    @FXML
    private ImageView sun1;

    @FXML
    private ImageView sun2;

    @FXML
    private ImageView sun3;

    @FXML
    private ImageView sun4;

    @FXML
    private ImageView sun5;

    @FXML
    private ImageView sun6;

    @FXML
    private ImageView sun7;

    @FXML
    private ImageView sun8;

    //this is the only parent
    private Parent root;
    private CurrentWeather cw;
    private Timer t;

    //called when the back button is pressed
    public void back() throws IOException {
        Main.navigateBackToMainPage();
    }

    //When the button is toggled we switch to weekly
    public void weeklySwitch(ActionEvent event) throws IOException {

        Parent weeklyParent = new FXMLLoader().load(getClass().getResource("detailpageweekly.fxml"));
        Scene weeklyScene = new Scene(weeklyParent);

        //Get the stage information from the clicked button
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(weeklyScene);
        window.show();


    }

    /*This is called when the hourly button is pressed.
    It changes the scene to a weekly controller which has
    the same upper part but displays weekly forecast in the
    lower part*/
    public void hourlySwitch(ActionEvent event) throws IOException {

        Parent hourlyParent = new FXMLLoader().load(getClass().getResource("detailpage.fxml"));
        Scene hourlyScene = new Scene(hourlyParent);

        //Get the stage information from the clicked
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(hourlyScene);
        window.show();


    }
    /* Initializes the whole state on entering the scene from any other page.
    * */
    public void initialize(){
        btnHourly.setSelected(true);
        System.out.println(btnHourly.isSelected());
        cw = OWM.getCurrentWeather(detailsPageLocation);
        locate.setText(Main.detailsPageLocation.name);

        //set up time fields
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        time1.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time2.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time3.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time4.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time5.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time6.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time7.setText(simpleDateFormat.format(calendar.getTime())+":00");
        calendar.add(Calendar.HOUR_OF_DAY,3);
        time8.setText(simpleDateFormat.format(calendar.getTime())+":00");


        /*
        * Initialzie temperature fields for the hourly forecast*/
        List<HourlyForecast> listHourly = OWM.getDayForecast(Main.detailsPageLocation).forecasts;
        String bd = Conversions.convertToPreferredTemperature(listHourly.get(0).mainParameters.temperature);
        temp1.setText(bd);
        String bd2 = Conversions.convertToPreferredTemperature(listHourly.get(1).mainParameters.temperature);
        temp2.setText(bd2);
        String bd3 = Conversions.convertToPreferredTemperature(listHourly.get(2).mainParameters.temperature);
        temp3.setText(bd3);
        String bd4 = Conversions.convertToPreferredTemperature(listHourly.get(3).mainParameters.temperature);
        temp4.setText(bd4);
        String bd5 = Conversions.convertToPreferredTemperature(listHourly.get(4).mainParameters.temperature);
        temp5.setText(bd5);
        String bd6 = Conversions.convertToPreferredTemperature(listHourly.get(5).mainParameters.temperature);
        temp6.setText(bd6);
        String bd7 = Conversions.convertToPreferredTemperature(listHourly.get(6).mainParameters.temperature);
        temp7.setText(bd7);
        String bd8 = Conversions.convertToPreferredTemperature(listHourly.get(7).mainParameters.temperature);
        temp8.setText(bd8);

        //This sets the cloudiness in percent of the hour
        cld1.setText(String.valueOf(listHourly.get(0).clouds.cloudiness));
        cld2.setText(String.valueOf(listHourly.get(1).clouds.cloudiness));
        cld3.setText(String.valueOf(listHourly.get(2).clouds.cloudiness));
        cld4.setText(String.valueOf(listHourly.get(3).clouds.cloudiness));
        cld5.setText(String.valueOf(listHourly.get(4).clouds.cloudiness));
        cld6.setText(String.valueOf(listHourly.get(5).clouds.cloudiness));
        cld7.setText(String.valueOf(listHourly.get(6).clouds.cloudiness));
        cld8.setText(String.valueOf(listHourly.get(7).clouds.cloudiness));


        /*Initialzie the rain amount and display it in the scene.*/
        String rainOutput = listHourly.get(0).rain != null ? (listHourly.get(0).rain.rainAmt + "%") : "N/A";
        rain1.setText(rainOutput);
        rainOutput = listHourly.get(1).rain != null ? (listHourly.get(1).rain.rainAmt + "%") : "N/A";
        rain2.setText(rainOutput);
        rainOutput = listHourly.get(2).rain != null ? (listHourly.get(2).rain.rainAmt + "%") : "N/A";
        rain3.setText(rainOutput);
        rainOutput = listHourly.get(3).rain != null ? (listHourly.get(3).rain.rainAmt + "%") : "N/A";
        rain4.setText(rainOutput);
        rainOutput = listHourly.get(4).rain != null ? (listHourly.get(4).rain.rainAmt + "%") : "N/A";
        rain5.setText(rainOutput);
        rainOutput = listHourly.get(5).rain != null ? (listHourly.get(5).rain.rainAmt + "%") : "N/A";
        rain6.setText(rainOutput);
        rainOutput = listHourly.get(6).rain != null ? (listHourly.get(6).rain.rainAmt + "%") : "N/A";
        rain7.setText(rainOutput);
        rainOutput = listHourly.get(7).rain != null ? (listHourly.get(7).rain.rainAmt + "%") : "N/A";
        rain8.setText(rainOutput);

        /*This sets a different icon depending on the weather condition
        * for each hour*/
        sun1.setImage(listHourly.get(0).getIcon());
        sun2.setImage(listHourly.get(1).getIcon());
        sun3.setImage(listHourly.get(2).getIcon());
        sun4.setImage(listHourly.get(3).getIcon());
        sun5.setImage(listHourly.get(4).getIcon());
        sun6.setImage(listHourly.get(5).getIcon());
        sun7.setImage(listHourly.get(6).getIcon());
        sun8.setImage(listHourly.get(7).getIcon());

        /*
        * This sets the sun position field in degrees.*/
        List<SunPosition> sunPositions = scAPI.getSunPositionsDay(detailsPageLocation);
        String sunOutput;
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(0).getAltitude());
        sunPos1.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(3).getAltitude());
        sunPos2.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(6).getAltitude());
        sunPos3.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(9).getAltitude());
        sunPos4.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(12).getAltitude());
        sunPos5.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(15).getAltitude());
        sunPos6.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(18).getAltitude());
        sunPos7.setText(sunOutput);
        sunOutput = ""+Conversions.roundDouble(sunPositions.get(21).getAltitude());
        sunPos8.setText(sunOutput);


        /*This is a method we call to initialize the top part of the screen.*/
        updateCurentWeather(cw);

        // Background fetched here
        String weatherDesc = cw.weather.get(0).description;


        /* The background image is set dynamically based on the weather*/
        BackgroundImage backgroundImage = new BackgroundImage(utils.Background.getBackgroundImage(weatherDesc), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        container.setBackground(new Background(backgroundImage));


    }


    /* This initializes all state in the upper part of the screen.
    It uses the same logic as above. */
    private void updateCurentWeather(CurrentWeather cw){
        System.out.println("refresh current weather");
        String bd = Conversions.convertToPreferredTemperature(cw.mainParameters.temperature);
        temperature.setText(bd);
        visibility.setText(Conversions.convertToPreferredDistance(Double.parseDouble(cw.visibility)));
        sunrise.setText(cw.systemParameters.sunrise.toString().split(" ")[3].substring(0,5));
        sunset.setText(cw.systemParameters.sunset.toString().split(" ")[3].substring(0,5));
        cloudCover.setText(new Double(cw.clouds.cloudiness).intValue() +"%");
        sunPosition.setText(Conversions.roundDouble(scAPI.getSunPositionNow(detailsPageLocation).getAltitude()) +  "°");
        String rainOutput = cw.rain != null ? (cw.rain.rainAmt + "%") : "N/A";
        chanceOfRain.setText(rainOutput);
        sunType.setImage(cw.getIcon());
    }
}

