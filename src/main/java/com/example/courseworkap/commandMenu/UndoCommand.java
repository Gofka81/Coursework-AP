package com.example.courseworkap.commandMenu;

import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;
import com.example.courseworkap.manager.UndoManager;

import java.util.Objects;

public class UndoCommand extends Command{

    public UndoCommand() {
        setName("Undo");
    }

    @Override
    public boolean execute() {

        if(!UndoManager.isEmpty()) {
            DBManager dbManager = DBManager.getInstance();
            dbManager.clearDisk(Menu.getCurrentDisk());
            dbManager.loadMusicOnDisk(UndoManager.pop().getDisk().getMusicList());
            return true;
        }
        System.out.println("Нема що скасовувати!");
        return false;
    }
}