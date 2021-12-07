package com.example.courseworkap.controller;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartUIController implements Initializable {
    @FXML
    private ComboBox<Disk> diskComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diskComboBox.setItems(FXCollections.observableArrayList(DBManager.getInstance().findAllDisks()));
    }

    @FXML
    void createDisk(ActionEvent event) throws IOException {
        StageController.getInstance().switchToCreateDiskScene(event);
    }

    @FXML
    void deleteDisk(ActionEvent event){
        if (diskComboBox.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Виберіть який диск видалити.");

            alert.showAndWait();
            Logger.log("[" + getClass().getName() + "] Диск не видалено");
        }else{
            DBManager.setCurrentDisk(diskComboBox.getValue().getId());
            diskComboBox.getItems().remove(diskComboBox.getValue());
            DBManager.getInstance().deleteDisk(DBManager.getCurrentDisk());
            Logger.log("["+getClass().getName()+"] Диск видалено");
        }

    }

    @FXML
    void musicPlaylistScene(ActionEvent event) throws IOException {
        if(!diskComboBox.getSelectionModel().isEmpty()) {
            DBManager.setCurrentDisk(diskComboBox.getValue().getId());
            StageController.getInstance().switchToMusicMenu(event);
            Logger.log("[" + getClass().getName() + "] Диск вибрано");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Будь ласка виберіть диск, а потім тисніть Далі.");

            alert.showAndWait();
            Logger.log("[" + getClass().getName() + "] Диск не вибрано");
        }
    }

    @FXML
    void exit(ActionEvent event){
        Logger.log("["+getClass().getName()+"] Вихід з програми Код:0");
        Logger.saveLogs();
        if(Logger.haveMistakes()){
            Logger.sendMessage();
        }
        System.exit(0);
    }
}