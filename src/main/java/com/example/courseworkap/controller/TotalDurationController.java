package com.example.courseworkap.controller;

import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class TotalDurationController {
    @FXML
    private Label countDuration;


    @FXML
    private void goBack(ActionEvent event) throws IOException {
        StageController.getInstance().switchToMusicMenu(event);
    }

    @FXML
    private void initialize(){
        int duration=0;
        List<Music> musicList = DBManager.getInstance().findAllMusic(DBManager.getCurrentDisk());
        for(Music music: musicList){
            duration += music.getDuration().getValue();
        }
        countDuration.setText(String.format("%2d:%2d:%-2d",duration/3600,(duration%3600)/60,(duration%360)%60));
    }
}
