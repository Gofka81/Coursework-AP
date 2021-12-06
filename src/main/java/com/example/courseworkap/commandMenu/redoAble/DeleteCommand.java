package com.example.courseworkap.commandMenu.redoAble;

import com.example.courseworkap.commandMenu.Command;
import com.example.courseworkap.controller.MusicMenuController;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.List;

public class DeleteCommand extends Command implements RedoAble {

    public DeleteCommand(Disk disk) {
        this.setDisk(disk);
        setName("Delete");
    }

    @Override
    public boolean execute() {
        Disk disk = saveDisk(getDisk());

        setDisk(disk);
        return  true;
    }
}
