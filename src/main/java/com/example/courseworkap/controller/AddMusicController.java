package com.example.courseworkap.controller;

import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.MusicManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class AddMusicController implements Initializable {
    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldDuration;

    @FXML
    private ComboBox<String> musicComboBox;

    @FXML
    private void addMusic(ActionEvent event) throws IOException {
        DBManager.getInstance().insertMusic(MusicManager.getCreatedClass(0,new SimpleStringProperty(textFieldName.getText()),
                (ObservableValue) new SimpleIntegerProperty(Integer.parseInt(textFieldDuration.getText()) ),
                MusicManager.genreStringToIntConverter(musicComboBox.getValue())), DBManager.getCurrentDisk());
        StageController.getInstance().switchToMusicMenu(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicComboBox.setItems(FXCollections.observableArrayList(MusicManager.getGenres()));
    }
}
