package com.example.courseworkap.commandSetup;

import com.example.courseworkap.Logger;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.Scanner;

public class ChooseDiskCommand extends SetupCommand{

    public ChooseDiskCommand() {
        setName("ChooseDisk");
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        int input;
        boolean check =false;
        //TODO deleate this
        ////////////////////
        SetupCommand command = new PrintDiskCommand();
        if(!command.execute()){
            return false;
        }
        DBManager dbManager = DBManager.getInstance();
        do {
            if(check){
                System.out.println("Помикла вводу. Попадіть в рамки пісень.");
                Logger.log("Помикла вводу пісні для видалення.\n");
            }
            input = scanner.nextInt();
            check = true;
        }while(input<1 || input > dbManager.findAllDisks().size());
        Menu.setCurrentDisk(input);
        ////////////////
        return true;
    }
}
