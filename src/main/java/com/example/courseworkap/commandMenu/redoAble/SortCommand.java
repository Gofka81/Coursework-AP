package com.example.courseworkap.commandMenu.redoAble;

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
        if(musicList.isEmpty()) {
            return false;
        }
        DBManager dbManager = DBManager.getInstance();
        dbManager.clearDisk(Menu.getCurrentDisk());
        dbManager.loadMusicOnDisk(musicList);
        this.setDisk(disk);

        return true;
    }
}
