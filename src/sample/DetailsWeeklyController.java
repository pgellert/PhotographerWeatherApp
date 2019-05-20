package sample;

import classes.Location;
import classes.currentweather.CurrentWeather;
import classes.forecast.daily.DailyForecast;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Conversions;
import utils.OWM;
import utils.scAPI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static sample.Main.*;


public class DetailsWeeklyController extends TimerTask{


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
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Main.stage.setTitle("Back from Weekly");
        Main.stage.setScene(new Scene(root, 479, 673));
        stage.show();
    }

    //When the button is toggled we switch to weekly
    public void weeklySwitch(ActionEvent event) throws IOException {
        //t2.cancel();
        //t2.purge();
        Parent weeklyParent = new FXMLLoader().load(getClass().getResource("detailpageweekly.fxml"));
        Scene weeklyScene = new Scene(weeklyParent);

        //Get the stage information from the clicked button
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(weeklyScene);
        window.show();

    }

    //When the button is toggled we switch to hourly
    public void hourlySwitch() throws IOException {
        //t2.cancel();
        //t2.purge();
        root = FXMLLoader.load(getClass().getResource("detailpage.fxml"));
        Main.stage.setTitle("Back from Weekly");
        Main.stage.setScene(new Scene(root, 479, 673));
        stage.show();
//        Parent hourlyParent = new FXMLLoader().load(getClass().getResource("detailpage.fxml"));
//        Scene hourlyScene = new Scene(hourlyParent);
//
//        //Get the stage information from the clicked
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(hourlyScene);
//        window.show();

    }
    //Mihnea's method which initializes all fields in the top part of the screen
    public void initialize(){
        btnWeekly.setSelected(true);
        //set up time fields
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM");
        int currentMonth = calendar.get(Calendar.MONTH); // gets hour in 24h format
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        time1.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time2.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time3.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time4.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time5.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time6.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time7.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH,3);
        time8.setText(simpleDateFormat.format(calendar.getTime()));

        CurrentWeather cw = OWM.getCurrentWeather(new Location(getCity()+","+getCountry()));
        locate.setText(getCity() + ", " + getCountry() );
        List<DailyForecast> list = OWM.getWeekForecast(new Location(getCity()+","+getCountry())).forecasts;

        //sets up temps
        double bd = Conversions.toCelsius(list.get(0).temperature);
        for(DailyForecast d: list){
            System.out.println(d.temperature);
        }
        double bd2 = Conversions.toCelsius(list.get(1).temperature);

        double bd3 =Conversions.toCelsius(list.get(2).temperature);

        double bd4 =Conversions.toCelsius(list.get(3).temperature);

        double bd5 =Conversions.toCelsius(list.get(4).temperature);

        double bd6 =Conversions.toCelsius(list.get(5).temperature);

        double bd7 = Conversions.toCelsius(list.get(6).temperature);

        double bd8 =Conversions.toCelsius(list.get(7).temperature);

        temp1.setText(String.format("%.1f", bd) + "°");

        temp2.setText(String.format("%.1f", bd2) + "°");

        temp3.setText(String.format("%.1f", bd3) + "°");

        temp4.setText(String.format("%.1f", bd4) + "°");

        temp5.setText(String.format("%.1f", bd5) + "°");

        temp6.setText(String.format("%.1f", bd6) + "°");

        temp7.setText(String.format("%.1f", bd7) + "°");

        temp8.setText(String.format("%.1f", bd8) + "°");


        cld1.setText(String.valueOf(list.get(0).clouds.cloudiness));
        cld2.setText(String.valueOf(list.get(1).clouds.cloudiness));
        cld3.setText(String.valueOf(list.get(2).clouds.cloudiness));
        cld4.setText(String.valueOf(list.get(3).clouds.cloudiness));
        cld5.setText(String.valueOf(list.get(4).clouds.cloudiness));
        cld6.setText(String.valueOf(list.get(5).clouds.cloudiness));
        cld7.setText(String.valueOf(list.get(6).clouds.cloudiness));
        cld8.setText(String.valueOf(list.get(7).clouds.cloudiness));

        String rainOutput = list.get(0).rain != null ? (list.get(0).rain.rainAmt + "%") : "N/A";
        rain1.setText(rainOutput);
        rainOutput = list.get(1).rain != null ? (list.get(1).rain.rainAmt + "%") : "N/A";
        rain2.setText(rainOutput);
        rainOutput = list.get(2).rain != null ? (list.get(2).rain.rainAmt + "%") : "N/A";
        rain3.setText(rainOutput);
        rainOutput = list.get(3).rain != null ? (list.get(3).rain.rainAmt + "%") : "N/A";
        rain4.setText(rainOutput);
        rainOutput = list.get(4).rain != null ? (list.get(4).rain.rainAmt + "%") : "N/A";
        rain5.setText(rainOutput);
        rainOutput = list.get(5).rain != null ? (list.get(5).rain.rainAmt + "%") : "N/A";
        rain6.setText(rainOutput);
        rainOutput = list.get(6).rain != null ? (list.get(6).rain.rainAmt + "%") : "N/A";
        rain7.setText(rainOutput);
        rainOutput = list.get(7).rain != null ? (list.get(7).rain.rainAmt + "%") : "N/A";
        rain8.setText(rainOutput);







//        BigDecimal bd = new BigDecimal(cw.mainParameters.temperature - 273.15);
//        bd = bd.round(new MathContext(3));
//        temperature.setText(String.valueOf(bd) + "°");
//        visibility.setText(cw.visibility);
//        sunrise.setText(cw.systemParameters.sunrise.toString().split(" ")[3].substring(0,5));
//        sunset.setText(cw.systemParameters.sunset.toString().split(" ")[3].substring(0,5));
//        cloudCover.setText(new Double(cw.clouds.cloudiness).intValue() +"%");
//        String rainOutput = cw.rain != null ? (cw.rain.rainAmt + "%") : "N/A";
//        chanceOfRain.setText(rainOutput);
        updateCurentWeather(cw);
        //t2 = new Timer();

        //t2.scheduleAtFixedRate(this,0,1000);

    }

    private void updateCurentWeather(CurrentWeather cw){
        System.out.println("refresh current weather");
        System.out.println(cw.mainParameters.temperature);
        System.out.println("once");
        BigDecimal bd = new BigDecimal(cw.mainParameters.temperature - 273.15);
        bd = bd.round(new MathContext(3));
        temperature.setText(String.valueOf(bd) + "°");
        visibility.setText(cw.visibility);
        sunrise.setText(cw.systemParameters.sunrise.toString().split(" ")[3].substring(0,5));
        sunset.setText(cw.systemParameters.sunset.toString().split(" ")[3].substring(0,5));
        cloudCover.setText(new Double(cw.clouds.cloudiness).intValue() +"%");
        //sunPosition.setText(scAPI.getSunPositionNow(new Location(getCity()+","+getCountry())).getAzimuth() +  "°");
        String rainOutput = cw.rain != null ? (cw.rain.rainAmt + "%") : "N/A";
        chanceOfRain.setText(rainOutput);
        java.awt.Image icon = cw.getIcon();
        BufferedImage img = new BufferedImage(icon.getWidth(null), icon.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D drawer = img.createGraphics();
        drawer.drawImage(img, 0,0,null);
        drawer.dispose();
        sunType.setImage(SwingFXUtils.toFXImage(img, null));

    }

    @Override
    public void run() {
        updateCurentWeather(cw);
    }
}
