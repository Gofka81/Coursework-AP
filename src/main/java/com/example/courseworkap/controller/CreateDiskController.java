package com.example.courseworkap.controller;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateDiskController {
    @FXML
    private TextField textField;


    @FXML
    void createDisk(ActionEvent event) throws IOException {
        if(textField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Назвіть цей диск!");

            alert.showAndWait();
        }else {
            DBManager.getInstance().insertDisk(new Disk(textField.getText()));
            StageController.getInstance().switchToStartView(event);
            Logger.log("[" + getClass().getName() + "] Створено диск");
        }
    }
}
