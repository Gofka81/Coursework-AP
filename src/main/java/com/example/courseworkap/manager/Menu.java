package com.example.courseworkap.manager;

import com.example.courseworkap.Logger;
import com.example.courseworkap.commandMenu.*;
import com.example.courseworkap.commandMenu.redoAble.DeleteCommand;
import com.example.courseworkap.commandMenu.redoAble.KeyboardReaderCommand;
import com.example.courseworkap.commandMenu.redoAble.SortCommand;
import com.example.courseworkap.commandSetup.*;
import com.example.courseworkap.entity.Disk;

public class Menu {

   private Menu(){}
    private static Disk disk = new Disk();
   private static int currentDisk=0;

    public static void diskSetup(int input){
        SetupCommand setupCommand = null;
        switch (input){
            case(0):
                setupCommand = new ExitCommand();
                break;
            case (1):
                setupCommand = new PrintDiskCommand();
                break;
            case (2):
                setupCommand = new ChooseDiskCommand();
                break;
            case (3):
                setupCommand = new CreateDiskCommand();
                break;
            case (4):
                setupCommand = new DeleteDiskCommand();
                break;
            case (5):
                setupCommand = new NextMenuCommand();
                break;
            default:
                System.out.println("Idk how u could possibly do this....\n\tThat`s error brother.");
        }
        if(setupCommand != null) {
            boolean status = setupCommand.execute();
            Logger.log("["+setupCommand.getName()+"] status of execution "+status+System.lineSeparator());

        }

    }

    public static void perform(int input){
        Command command = null;
        switch (input){
            case(1):
                command = new KeyboardReaderCommand(disk);
                break;
            case (2):
                command = new SortCommand(disk);
                break;
            case (3):
                command = new DeleteCommand(disk);
                break;
            case (4):
                command = new UndoCommand();
                break;
            default:
                System.out.println("Idk how u could possibly do this....\n\tThat`s error brother.");
                Logger.logMistake("Неправильний input пройшов у default case.\n");
        }
        if(input != 4){
            UndoManager.add(command);}
        if(command != null) {
            boolean status = command.execute();
            Logger.log("["+command.getName()+"] status of execution "+status+System.lineSeparator());
        }

    }

    public static Disk getDisk() {
        return disk;}

    public static void setDisk(Disk disk) {
        Menu.disk = disk;}

    public static int getCurrentDisk() {
        return currentDisk;
    }

    public static void setCurrentDisk(int currentDisk) {
        Menu.currentDisk = currentDisk;
    }
}