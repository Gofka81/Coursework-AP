package com.example.courseworkap.commandSetup;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;

import java.util.List;

public class PrintDiskCommand extends SetupCommand{

    public PrintDiskCommand() {
        setName("PrintDisk");
    }

    @Override
    public boolean execute() {
        DBManager dbManager = DBManager.getInstance();
        List<Disk> disks = dbManager.findAllDisks();
        if(disks == null|| disks.isEmpty()){
            System.out.println("Немає елементів :(");
            return false;
        }
        int count =1;
        for(Disk disk : disks){
            System.out.format("%-2d%-20s%n",count,disk.getName());
            count++;
        }
        return true;
    }
}
