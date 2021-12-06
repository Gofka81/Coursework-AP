package com.example.courseworkap.controller;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        DBManager.setCurrentDisk(diskComboBox.getValue().getId());
        diskComboBox.getItems().remove(diskComboBox.getValue());
        DBManager.getInstance().deleteDisk(DBManager.getCurrentDisk());
    }

    @FXML
    void musicPlaylistScene(ActionEvent event) throws IOException {
        DBManager.setCurrentDisk(diskComboBox.getValue().getId());
        StageController.getInstance().switchToMusicMenu(event);
    }

    @FXML
    void exit(ActionEvent event){
        System.exit(0);
    }
}