package com.example.courseworkap.commandMenu;

import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.List;


public class CountDurationCommand extends Command{

    public CountDurationCommand(){
        setName("CountDuration");
    }

    @Override
    public boolean execute() {
        int duration=0;
        List<Music>musicList = DBManager.getInstance().findAllMusic(Menu.getCurrentDisk());
        for(Music music: musicList){
            duration+= music.getDuration();
        }

        System.out.format("%nTotal time is %d hours, %d minutes and %d seconds.",duration/3600,(duration%3600)/60,(duration%360)%60);
        return true;
    }
}
