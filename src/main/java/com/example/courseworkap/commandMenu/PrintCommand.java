package com.example.courseworkap.commandMenu;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.List;

public class PrintCommand extends Command{

    public PrintCommand() {
        setName("Print");
    }

    @Override
    public boolean execute() {
        int count=0;
        List<Music> musicList = DBManager.getInstance().findAllMusic(Menu.getCurrentDisk());
        if(musicList.isEmpty()){
            System.out.println("Немає елементів :(");
            Logger.log("Немає елементів.\n");
            return false;
        }
        System.out.println("\n┌------------------------------------------------------------------------┐");
        for(Music music : musicList){
            count++;
            System.out.format("|%-3d|%-40s|%2d:%-2d| %-20s|%n",count,music.getName(),music.getDuration().getValue()/60,music.getDuration().getValue()%60,music.getStyle());
        }
        System.out.println("└------------------------------------------------------------------------┘\n\n\n");
        return true;
    }
}
