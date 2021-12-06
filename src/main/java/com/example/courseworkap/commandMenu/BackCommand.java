package com.example.courseworkap.commandMenu;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.Menu;


public class BackCommand extends Command{

    public BackCommand(Disk disk) {
        super(disk);
        setName("Back");
    }

    public BackCommand() {
        setName("Back");
    }

    @Override
    public boolean execute() {

        return true;
    }
}
