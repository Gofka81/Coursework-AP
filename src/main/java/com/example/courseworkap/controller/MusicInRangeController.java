package com.example.courseworkap.controller;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.MusicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MusicInRangeController implements Initializable {
    private boolean doubleTap = false;
    private List<Music> musicList;
    private final ObservableList<Music> musicData = FXCollections.observableArrayList();

    @FXML
    private CheckBox checkBox;

    @FXML
    private Slider sliderMin;

    @FXML
    private Slider sliderMax;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMusic.setVisible(false);
        int max = DBManager.getInstance().findTheLongest();
        sliderMin.setMax(max);
        sliderMax.setMax(max);
        sliderMax.setValue(max);
    }

    @FXML
    private void findInRange(ActionEvent event) throws IOException {
       musicList = DBManager.getInstance().findAllMusicInRange((int)sliderMin.getValue(),(int)sliderMax.getValue());
       if(doubleTap){
           StageController.getInstance().switchToMusicMenu(event);
       }
        doubleTap = true;
       initialize();
        Logger.log("["+getClass().getName()+"] Пошук за довжиною");
    }

    @FXML
    private void configureSliderMax(ActionEvent event){
        if(checkBox.isSelected()) {
            sliderMax.setMin(sliderMin.getValue());
            sliderMax.setDisable(false);
            sliderMin.setDisable(true);
        }
        else{
            sliderMax.setDisable(true);
            sliderMin.setDisable(false);
            sliderMin.setMax(sliderMax.getValue());
        }
    }

    @FXML
    private TableView<Music> tableMusic;

    @FXML
    private TableColumn<Music, String> nameMusic;

    @FXML
    private TableColumn<Music, Integer> durationMusic;

    @FXML
    private TableColumn<Music, String> genreMusic;


    private void initialize(){
        tableMusic.setVisible(true);
        initData();

        nameMusic.setCellValueFactory(x-> x.getValue().getName());
        durationMusic.setCellValueFactory(x-> x.getValue().getDuration());
        genreMusic.setCellValueFactory(x->x.getValue().getStyle());

        tableMusic.setItems(musicData);
    }

    private void initData(){
        for(Music music: musicList){
            musicData.add(MusicManager.getCreatedClass(0,music.getName(),music.getDuration(),MusicManager.genreStringToIntConverter(music.getStyle().getValue())));
        }

    }
}
