package com.example.courseworkap.controller;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateDiskController {
    @FXML
    private TextField textField;


    @FXML
    void createDisk(ActionEvent event) throws IOException {
        DBManager.getInstance().insertDisk(new Disk(textField.getText()));
        StageController.getInstance().switchToStartView(event);
    }
}
