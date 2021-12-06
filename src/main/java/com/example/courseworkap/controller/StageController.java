package com.example.courseworkap.controller;

import com.example.courseworkap.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {
    private static StageController stageController;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public static StageController getInstance() {
        if(stageController == null){
            stageController = new StageController();
        }
        return stageController;
    }

    public void switchToCreateDiskScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("createDisk.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStartView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("start-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMusicMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("musicMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDurationMusic(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("totalDuration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInsetrMusic(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("addMusic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchMusicInRange(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("musicInRange.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
