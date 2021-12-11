package com.example.courseworkap.controller;

import com.example.courseworkap.Logger;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.MusicManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private void addMusic(ActionEvent event) {
        try {
            if(musicComboBox.getSelectionModel().isEmpty() ){
                throw new  Exception("Не вибрано жанру музики.");
            }
            if(textFieldName.getText().isEmpty() || textFieldDuration.getText().isEmpty()){
                throw new Exception("Пусте поле вводу.");
            }
            DBManager.getInstance().insertMusic(MusicManager.getCreatedClass(0,new SimpleStringProperty(textFieldName.getText()),
                    (ObservableValue) new SimpleIntegerProperty(Integer.parseInt(textFieldDuration.getText())),
                    new SimpleStringProperty(musicComboBox.getValue())), DBManager.getCurrentDisk());
            StageController.getInstance().switchToMusicMenu(event);
            Logger.log("["+getClass().getName()+"] Додано музику");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Введено не вірний формат данних.\n"+e.getMessage());

            alert.showAndWait();
            Logger.log("[" + getClass().getName() + "] Введено невірний формат");
            Logger.logMistake("[" + getClass().getName() + "] Введено невірний формат " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicComboBox.setItems(FXCollections.observableArrayList(MusicManager.getGenres()));
    }
}
