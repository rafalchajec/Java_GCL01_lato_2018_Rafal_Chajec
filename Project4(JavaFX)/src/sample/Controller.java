package sample;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private Boolean statePoint = true;
    private Boolean musicState = true;
    private ObservableList items = FXCollections.observableArrayList();
    private String musicFile = "music.mp3";
    private Media sound;
    private MediaPlayer mediaPlayer;

    @FXML
    private ListView<String> timeList;

    @FXML
    private Canvas canvas;

    @FXML
    void initialize() {
        GraphicsContext hour = canvas.getGraphicsContext2D();
        drawHour(hour);
        Timeline timelinedrawHour = new Timeline(new KeyFrame(Duration.millis(10), ae -> drawHour(hour)));
        timelinedrawHour.setCycleCount(Animation.INDEFINITE);
        timelinedrawHour.play();
        Timeline timelineanimation = new Timeline(new KeyFrame(Duration.seconds(1), ae -> animation()));
        timelineanimation.setCycleCount(Animation.INDEFINITE);
        timelineanimation.play();
        Timeline timelineMusic = new Timeline(new KeyFrame(Duration.minutes(1), ae -> setStateMusic()));
        timelineMusic.setCycleCount(Animation.INDEFINITE);
        timelineMusic.play();
    }



    private void drawHour(GraphicsContext gc) {

        DateFormat HOUR = new SimpleDateFormat("HH");
        DateFormat MINUTE = new SimpleDateFormat("mm");
        DateFormat SECOND = new SimpleDateFormat("ss");
        Date date = new Date();
        String msg = HOUR.format(date);
        if(statePoint) msg += ".";
        else msg += ":";
        msg+=MINUTE.format(date);
        Text text = new Text(msg);

        String msg2 = SECOND.format(date);
        Text text2 = new Text(msg2);
        Font font = Font.loadFont("file:resources/fonts/czcionka.ttf", 160);
        Font font2 = Font.loadFont("file:resources/fonts/czcionka.ttf", 70);
        text.setFont(font);
        text2.setFont(font2);

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFont(font);
        gc.fillText(msg,canvas.getWidth()/2 - text.getLayoutBounds().getWidth()/2, canvas.getHeight()/2 + text.getLayoutBounds().getHeight()/2);
        gc.setFont(font2);
        gc.fillText(msg2,canvas.getWidth()/2 + text.getLayoutBounds().getWidth()/2, canvas.getHeight()/2 + text.getLayoutBounds().getHeight()/2-text2.getLayoutBounds().getHeight()/2);
    }

    private void animation()
    {
        if(statePoint)
            statePoint = false;
        else
            statePoint = true;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String msg = dateFormat.format(date);

        if(items.contains(msg) && musicState)
            playmusic();

    }

    private void setStateMusic()
    {
        musicState = true;
    }

    private void playmusic()
    {
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        musicState = false;
    }



    public void goToSecond(ActionEvent actionEvent) throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aboutAuthor.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Author");
        primaryStage.setScene(new Scene(root, 633, 377));
        primaryStage.show();



    }

    public void goToAddClock(ActionEvent actionEvent) throws Exception {


        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("addClock.fxml"));
        Parent root = (Parent) fxmlLoader1.load();

        Controller2 controller = fxmlLoader1.getController();
        controller.setitems(items);
        controller.setList(timeList);

        Stage primaryStage1 = new Stage();
        primaryStage1.setTitle("Dodaj alarm");
        primaryStage1.setScene(new Scene(root, 633, 377));
        primaryStage1.show();
    }

    public void closeProgram()
    {
        System.exit(1);
    }



    public void cancelAlarms(ActionEvent actionEvent) {
        items.clear();
    }

    public void deleteAlarm(ActionEvent actionEvent) {
        if(timeList.getSelectionModel().getSelectedIndex() >= 0) {
            items.remove(timeList.getSelectionModel().getSelectedIndex());
            timeList.refresh();
        }
    }
}

