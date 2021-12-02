package com.example.courseworkap.manager;

import com.example.courseworkap.Logger;
import com.example.courseworkap.commandMenu.*;
import com.example.courseworkap.commandMenu.redoAble.DeleteCommand;
import com.example.courseworkap.commandMenu.redoAble.KeyboardReaderCommand;
import com.example.courseworkap.commandMenu.redoAble.SortCommand;
import com.example.courseworkap.commandSetup.*;
import com.example.courseworkap.entity.Disk;

import java.util.Scanner;

public class Menu {

   private Menu(){}

   private static Disk disk = new Disk();
   private static boolean IsRunningProgram = true;
   private static boolean IsRunningMenu = false;
   private static int currentDisk=0;

    public static void run(){
        while (IsRunningProgram){
            printChooseDisk();
            diskSetup(getSetupInput());
            while (IsRunningMenu) {
                loadBD();
                printMenu();
                perform(getMenuInput());
            }
        }
    }

    private static void printChooseDisk(){
        System.out.println("\n┌----------------------------------------┐");
        System.out.println("| 1)Вивести всі доступні диски           |");
        System.out.println("| 2)Вибрати диск                         |");
        System.out.println("| 3)Створити диск                        |");
        System.out.println("| 4)Видалити диск                        |");
        System.out.println("|                                        |");
        System.out.println("| 5)Вперед                               |");
        System.out.println("| 0)Вихід                                |");
        System.out.println("└----------------------------------------┘");
        System.out.print(">");
    }

    public static int getSetupInput(){
        int input;
        Scanner scanner = new Scanner(System.in);
        boolean check = false;

        do{
            if(check){
                System.out.println("\tПомилка!\nНеправильний ввід....");
                System.out.print(">");
                Logger.log("Неправильний вибір конфігурацій \n");
            }
            input = scanner.nextInt();
            check = true;
        }while (input>5 || input<0);

        return input;
    }

    private static void diskSetup(int input){
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

    private static void printMenu(){
        System.out.println("\n┌----------------------------------------┐");
        System.out.println("| 1)Вивести всі композиції               |");
        System.out.println("| 2)Додати композиції                    |");
        System.out.println("| 3)Підрахувати тривалість компзиції     |");
        System.out.println("| 4)Вивести відсортований список         |");
        System.out.println("| 5)Пошук по діапазону                   |");
        System.out.println("| 6)Видалення компохицій                 |");
        System.out.println("| 7)Скасувати вибір                      |");
        System.out.println("|                                        |");
        System.out.println("| 0)Назад                                |");
        System.out.println("└----------------------------------------┘");
        System.out.print(">");
    }

    public static int getMenuInput(){
        int input;
        Scanner scanner = new Scanner(System.in);
        boolean check = false;

        do{
            if(check){
                System.out.println("\tПомилка!\nНеправильний ввід....");
                System.out.print(">");
                Logger.log("Неправильний вибір команди \n");
            }
            input = scanner.nextInt();
            check = true;
        }while (input>7 || input<0);

        return input;
    }

    public static void perform(int input){
        Command command = null;
        switch (input){
            case(0):
                command = new BackCommand();
                break;
            case (1):
                command = new PrintCommand();
                break;
            case(2):
                command = new KeyboardReaderCommand(disk);
                break;
            case(3):
                command = new CountDurationCommand();
                break;
            case (4):
                command = new SortCommand(disk);
                break;
            case(5):
                command = new FindInRangeCommand();
                break;
            case (6):
                command = new DeleteCommand(disk);
                break;
            case (7):
                command = new UndoCommand();
                break;
            default:
                System.out.println("Idk how u could possibly do this....\n\tThat`s error brother.");
                Logger.logMistake("Неправильний input пройшов у default case.\n");
        }
        if(input == 2 || input == 4 || input==6){
            UndoManager.add(command);}
        if(command != null) {
            boolean status = command.execute();
            Logger.log("["+command.getName()+"] status of execution "+status+System.lineSeparator());
        }

    }

    public static void loadBD(){
        disk.setMusicList(DBManager.getInstance().findAllMusic(Menu.getCurrentDisk()));
    }

    public static boolean getIsRunningMenu() {
        return IsRunningMenu;}

    public static void setIsRunningMenu(boolean isRunning) {
        IsRunningMenu = isRunning;
    }

    public static boolean getIsRunningProgram() {
        return IsRunningProgram;}

    public static void setIsRunningProgram(boolean isRunning) {
        IsRunningProgram = isRunning;
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