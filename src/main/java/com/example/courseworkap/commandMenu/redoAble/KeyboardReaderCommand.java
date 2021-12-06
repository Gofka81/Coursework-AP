package com.example.courseworkap.commandMenu.redoAble;

import com.example.courseworkap.Logger;
import com.example.courseworkap.commandMenu.Command;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;
import com.example.courseworkap.manager.MusicManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import java.util.Scanner;

public class KeyboardReaderCommand extends Command implements RedoAble {

    public KeyboardReaderCommand(Disk disk) {
        super(disk);
       setName("KeyboardReader");
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        String tempName;
        int tempDuration;
        int tempStyle;
        int countinue = 0;
        do{
            try {
                System.out.print("\nОкей, введи назву пісні\n>");
                tempName = scanner.nextLine();
                System.out.print("\nАга, і ще жанр пісні будь ласка\n\n");
                MusicManager.printMusicGenres();
                System.out.print(">");
                tempStyle = scanner.nextInt();
                System.out.print("\nГаразд, тепер введи довжину треку (в секундах)\n>");
                tempDuration = scanner.nextInt();
                Disk disk = saveDisk(getDisk());
                this.setDisk(disk);
            }catch (Exception e){
                System.out.println("Помилка вводу\n");
                Logger.log("Помилка вводу\n");
                Logger.logMistake("Помилка вводу "+ e +System.lineSeparator());
                return false;
            }

            System.out.print("\nКлас, додаємо ще когось? ([1]Так)\n>");
            countinue = scanner.nextInt();
            scanner.nextLine();
        }while(countinue == 1);
        return true;
    }
}
