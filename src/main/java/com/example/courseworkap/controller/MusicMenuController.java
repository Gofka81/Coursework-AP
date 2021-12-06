package com.example.courseworkap.controller;

import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.MusicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.List;

public class MusicMenuController {

    private static MusicMenuController musicMenuController;
    private final ObservableList<Music> musicData = FXCollections.observableArrayList();

    public MusicMenuController(){

    }

    public static MusicMenuController getInstance(){
        if(musicMenuController == null){
            musicMenuController =  new MusicMenuController();
        }
        return musicMenuController;
    }

    @FXML
    private void  insertMusic(ActionEvent event) throws IOException {
        StageController.getInstance().switchToInsetrMusic(event);
    }

    @FXML
    private void getMusicInRange(ActionEvent event) throws IOException {
        musicData.clear();
        StageController.getInstance().switchMusicInRange(event);
    }

    @FXML
    private void  getMusicDuration(ActionEvent event) throws IOException {
        StageController.getInstance().switchToDurationMusic(event);
    }

    @FXML
    private void sortMusic(ActionEvent event){
        List<Music> musicList = DBManager.getInstance().findAllMusicByStyle(DBManager.getCurrentDisk());
        if(musicList.isEmpty()) {
            return;
        }
        DBManager dbManager = DBManager.getInstance();
        dbManager.clearDisk(DBManager.getCurrentDisk());
        dbManager.loadMusicOnDisk(musicList);
        musicData.clear();
        initialize();
    }

    @FXML
    private void deleteMusic(ActionEvent event){
        Music music = tableMusic.getSelectionModel().getSelectedItem();
        if(music != null) {
            DBManager.getInstance().deleteMusic(music);
        }
        musicData.clear();
        initialize();
    }

    @FXML
    private TableView<Music> tableMusic;

    @FXML
    private TableColumn<Music, String> nameMusic;

    @FXML
    private TableColumn<Music, Integer> durationMusic;

    @FXML
    private TableColumn<Music, String> genreMusic;

    @FXML
    private void initialize(){
        initData();

        nameMusic.setCellValueFactory(x-> x.getValue().getName());
        durationMusic.setCellValueFactory(x-> x.getValue().getDuration());
        genreMusic.setCellValueFactory(x->x.getValue().getStyle());

        tableMusic.setItems(musicData);
    }

    private void initData(){
        List<Music> musicList = DBManager.getInstance().findAllMusic(DBManager.getCurrentDisk());

        for(Music music: musicList){
            musicData.add(MusicManager.getCreatedClass(0,music.getName(),music.getDuration(),MusicManager.genreStringToIntConverter(music.getStyle().getValue())));
        }

    }

    public void loadTempData(List<Music> musicList){
        musicData.clear();
        for(Music music: musicList){
            musicData.add(MusicManager.getCreatedClass(0,music.getName(),music.getDuration(),MusicManager.genreStringToIntConverter(music.getStyle().getValue())));
        }
    }

    @FXML
    public void goBackToSetup(ActionEvent event) throws IOException {
        StageController.getInstance().switchToStartView(event);
    }

}
