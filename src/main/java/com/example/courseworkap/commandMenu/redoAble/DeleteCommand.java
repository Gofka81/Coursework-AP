package com.example.courseworkap.commandMenu.redoAble;

import com.example.courseworkap.Logger;
import com.example.courseworkap.commandMenu.Command;
import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;
import com.example.courseworkap.manager.Menu;

import java.util.List;
import java.util.Scanner;

public class DeleteCommand extends Command implements RedoAble {

    public DeleteCommand(Disk disk) {
        this.setDisk(disk);
        setName("Delete");
    }

    @Override
    public boolean execute() {
        int count=0;
        Scanner scanner = new Scanner(System.in);
        int input;
        boolean check =false;
        List<Music> musicList = DBManager.getInstance().findAllMusic(Menu.getCurrentDisk());
        if(musicList.isEmpty()){
            System.out.println("Нема що видаляти....");
            Logger.log("Немає що видаляти.\n");
            return false;
        }
        System.out.println("\n┌------------------------------------------------------------------------┐");
        for(Music music : musicList){
            count++;
            System.out.format("|%-3d|%-40s|%2d:%-2d| %-20s|%n",count,music.getName(),music.getDuration()/60,music.getDuration()%60,music.getStyle());
        }
        System.out.println("└------------------------------------------------------------------------┘\n");
        System.out.print("Ух... Напишіть порядок композиції яку композицію хочете видалити\n>");
        do {
            if(check){
                System.out.println("Помикла вводу. Попадіть в рамки пісень.");
                Logger.log("Помикла вводу пісні для видалення.\n");
            }
            input = scanner.nextInt();
            check = true;
        }while(input<1 || input > DBManager.getInstance().findAllMusic(Menu.getCurrentDisk()).size());
        Disk disk = saveDisk(getDisk());
        DBManager.getInstance().deleteMusic(musicList.get(input - 1));
        this.setDisk(disk);
        return  true;
    }
}
