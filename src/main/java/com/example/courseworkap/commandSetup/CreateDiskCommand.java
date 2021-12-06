package com.example.courseworkap.commandSetup;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;


import java.util.Scanner;

public class CreateDiskCommand extends SetupCommand{

    public CreateDiskCommand() {
        setName("CreateDisk");
    }

    @Override
    public boolean execute() {
        DBManager dbManager = DBManager.getInstance();
        //dbManager.insertDisk(new Disk(sc.nextLine()));
        return true;
    }
}
