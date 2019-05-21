package ui;

import classes.currentweather.CurrentWeather;
import classes.forecast.daily.DailyForecast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.shredzone.commons.suncalc.SunTimes;
import utils.Conversions;
import utils.OWM;
import utils.scAPI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static ui.Main.*;


public class DetailsWeeklyController{

    @FXML
    private Pane container;

    //set up IDs for all variables in the system
    @FXML
    private Label chanceOfRain;

    @FXML
    private Label time1;

    @FXML
    private Label time2;

    @FXML
    private Label time3;

    @FXML
    private AnchorPane anchorPane;

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
    private Label sundown8;

    @FXML
    private Label sundown6;

    @FXML
    private Label sunrise;

    @FXML
    private Label sundown7;

    @FXML
    private Label locate;

    @FXML
    private Label sundown1;

    @FXML
    private Label sunup2;

    @FXML
    private Label sunup1;

    @FXML
    private Label temp2;

    @FXML
    private Label sunup4;

    @FXML
    private Label sundown4;

    @FXML
    private Label temp3;

    @FXML
    private Label sunup3;

    @FXML
    private Label sundown5;

    @FXML
    private Label sundown2;

    @FXML
    private Label sunup6;

    @FXML
    private Label temp1;

    @FXML
    private Label sunup5;

    @FXML
    private Label sundown3;

    @FXML
    private ImageView sun2;

    @FXML
    private Label rain8;

    @FXML
    private Label sunup8;

    @FXML
    private ImageView sun3;

    @FXML
    private Label sunup7;

    @FXML
    private Label cld7;

    @FXML
    private ImageView sun1;

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
    private ImageView sun8;

    @FXML
    private ImageView sun6;

    @FXML
    private ImageView sun7;

    @FXML
    private ImageView sun4;

    @FXML
    private GridPane hourlyGrid;

    @FXML
    private ImageView sun5;

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


    //this is the only parent
    private Parent root;
    private CurrentWeather cw;
    private Timer t2;

    //called when the back button is pressed
    public void back() throws IOException {
        //t2.cancel();
        //t2.purge();
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
    public void hourlySwitch() throws IOException {

        root = FXMLLoader.load(getClass().getResource("detailpage.fxml"));
        Main.stage.setTitle("Back from Weekly");
        Main.stage.setScene(new Scene(root, 479, 673));
        stage.show();

    }


    /* Initializes the whole state on entering the scene from any other page.
    Sets all fields on the screen and icons and background image.
     * */
    public void initialize(){
        btnWeekly.setSelected(true);
        CurrentWeather cw = OWM.getCurrentWeather(detailsPageLocation);
        locate.setText(Main.detailsPageLocation.name);
        List<DailyForecast> listDaily = OWM.getWeekForecast(Main.detailsPageLocation).forecasts;
        //set up time fields
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM");
        int currentMonth = calendar.get(Calendar.MONTH); // gets hour in 24h format
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        time1.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,1);
        time2.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,1);
        time3.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,1);
        time4.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,1);
        time5.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,1);

        //sets up temperature fields
        String bd = Conversions.convertToPreferredTemperature(listDaily.get(0).mainParameters.temperature);
        String bd2 = Conversions.convertToPreferredTemperature(listDaily.get(1).mainParameters.temperature);
        String bd3 = Conversions.convertToPreferredTemperature(listDaily.get(2).mainParameters.temperature);
        String bd4 = Conversions.convertToPreferredTemperature(listDaily.get(3).mainParameters.temperature);
        String bd5 = Conversions.convertToPreferredTemperature(listDaily.get(4).mainParameters.temperature);

        temp1.setText(bd);

        temp2.setText(bd2);

        temp3.setText(bd3);

        temp4.setText(bd4);

        temp5.setText(bd5);

        //This sets the cloudiness in percent of the day
        cld1.setText(String.valueOf(listDaily.get(0).clouds.cloudiness));
        cld2.setText(String.valueOf(listDaily.get(1).clouds.cloudiness));
        cld3.setText(String.valueOf(listDaily.get(2).clouds.cloudiness));
        cld4.setText(String.valueOf(listDaily.get(3).clouds.cloudiness));
        cld5.setText(String.valueOf(listDaily.get(4).clouds.cloudiness));

        /*Initialzie the rain amount and display it in the scene.*/
        String rainOutput = listDaily.get(0).rain != null ? (listDaily.get(0).rain.rainAmt + "%") : "N/A";
        rain1.setText(rainOutput);
        rainOutput = listDaily.get(1).rain != null ? (listDaily.get(1).rain.rainAmt + "%") : "N/A";
        rain2.setText(rainOutput);
        rainOutput = listDaily.get(2).rain != null ? (listDaily.get(2).rain.rainAmt + "%") : "N/A";
        rain3.setText(rainOutput);
        rainOutput = listDaily.get(3).rain != null ? (listDaily.get(3).rain.rainAmt + "%") : "N/A";
        rain4.setText(rainOutput);
        rainOutput = listDaily.get(4).rain != null ? (listDaily.get(4).rain.rainAmt + "%") : "N/A";
        rain5.setText(rainOutput);


        //this is a format which displays the hour and minutes for use of the time fields above the icons
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        //this gets a list of sunrise and sunset times from the API
        List<SunTimes> sunTimes = scAPI.getSunTimesWeek(detailsPageLocation);
        //this sets the sundown time
        sundown1.setText(simpleDateFormat1.format(sunTimes.get(0).getSet()));
        sundown2.setText(simpleDateFormat1.format(sunTimes.get(1).getSet()));
        sundown3.setText(simpleDateFormat1.format(sunTimes.get(2).getSet()));
        sundown4.setText(simpleDateFormat1.format(sunTimes.get(3).getSet()));
        sundown5.setText(simpleDateFormat1.format(sunTimes.get(4).getSet()));
        //this fetches and displays the sunrise time in the fields in the FXML
        sunup1.setText(simpleDateFormat1.format(sunTimes.get(0).getRise()));
        sunup2.setText(simpleDateFormat1.format(sunTimes.get(1).getRise()));
        sunup3.setText(simpleDateFormat1.format(sunTimes.get(2).getRise()));
        sunup4.setText(simpleDateFormat1.format(sunTimes.get(3).getRise()));
        sunup5.setText(simpleDateFormat1.format(sunTimes.get(4).getRise()));

        /*This sets a different icon depending on the weather condition
         * for each hour*/
        sun1.setImage(listDaily.get(0).getIcon());
        sun2.setImage(listDaily.get(1).getIcon());
        sun3.setImage(listDaily.get(2).getIcon());
        sun4.setImage(listDaily.get(3).getIcon());
        sun5.setImage(listDaily.get(4).getIcon());


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
        System.out.println(cw.mainParameters.temperature);
        System.out.println("once");
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
