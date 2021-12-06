package com.example.courseworkap.commandSetup;

import com.example.courseworkap.manager.Menu;

public class NextMenuCommand extends SetupCommand{
    public NextMenuCommand() {
        setName("NextMenu");
    }

    @Override
    public boolean execute() {
        if(Menu.getCurrentDisk() == 0){
            System.out.println("Спершу виберіть диск!");
            return false;
        }

        return true;
    }
}
