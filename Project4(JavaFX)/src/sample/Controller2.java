package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller2 {

    @FXML
    private TextField alarmTime;

    @FXML
    private Label labelError;

    private ObservableList items;
    private ListView<String> timeList;

    public void addToList() throws Exception
    {
        Pattern pattern = Pattern.compile("[0-1]{1}[0-9]{1}:[0-5]{1}[0-9]{1}");
        Matcher matcher = pattern.matcher(alarmTime.getCharacters());
        Pattern pattern2 = Pattern.compile("[2]{1}[0-3]{1}:[0-5]{1}[0-9]{1}");
        Matcher matcher2 = pattern2.matcher(alarmTime.getCharacters());

        if((matcher.find()|| matcher2.find())&& alarmTime.getCharacters().length() == 5) {
            labelError.setVisible(false);

            items.add(alarmTime.getText());
            timeList.setItems(items.sorted());
        }
        else {
            labelError.setVisible(true);
        }


    }



    public void setitems(ObservableList items2)
    {
        items = items2;
    }

    public void setList(ListView<String> timeList2)
    {
        timeList = timeList2;
    }




}
