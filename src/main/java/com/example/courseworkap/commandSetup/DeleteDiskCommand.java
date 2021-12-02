package com.example.courseworkap.commandSetup;

import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;


public class DeleteDiskCommand extends  SetupCommand{

    public DeleteDiskCommand(){
        setName("DeleteDisk");
    }

    @Override
    public boolean execute() {
        if(Menu.getCurrentDisk() == 0){
            System.out.println("Не вибрано диск!");
            return false;
        }
        DBManager.getInstance().deleteDisk(Menu.getCurrentDisk());
        return true;
    }
}
