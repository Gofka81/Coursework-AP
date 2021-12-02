package com.example.courseworkap.commandMenu.redoAble;

import com.example.courseworkap.Logger;
import com.example.courseworkap.commandMenu.Command;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.List;

public class SortCommand extends Command implements RedoAble {

    public SortCommand(Disk disk) {
        super(disk);
        setName("Sort");
    }

    @Override
    public boolean execute() {
        Disk disk = saveDisk(getDisk());
        List<Music> musicList = DBManager.getInstance().findAllMusicByStyle(Menu.getCurrentDisk());
        DBManager dbManager = DBManager.getInstance();
        dbManager.clearDisk(Menu.getCurrentDisk());
        dbManager.loadMusicOnDisk(musicList);
        this.setDisk(disk);
        if(musicList.isEmpty()) {
            System.out.println("Тут немає що сортувати.");
            Logger.log("Тут нема що сорутвати\n");
            return false;
        }
        int count =0;
        System.out.println("\n┌------------------------------------------------------------------------┐");
        for(Music music : musicList){
            count++;
            System.out.format("|%-3d|%-40s|%2d:%-2d| %-20s|%n",count,music.getName(),music.getDuration()/60,music.getDuration()%60,music.getStyle());
        }
        System.out.println("└------------------------------------------------------------------------┘\n\n\n");
        return true;
    }
}
