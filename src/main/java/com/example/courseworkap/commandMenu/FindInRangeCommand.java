package com.example.courseworkap.commandMenu;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.music.Music;
import com.example.courseworkap.manager.DBManager;

import java.util.List;
import java.util.Scanner;

public class FindInRangeCommand extends Command{
    public FindInRangeCommand() {
        setName("FindInRange");
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        int a1,a2,temp,count=0;
        boolean check = false;

        System.out.print("Окей введи межі довжин треків\n\nВведи першу межу\n>");
        a1 = scanner.nextInt();
        System.out.print("\nТепер другу межу\n>");
        a2 = scanner.nextInt();

        if(a1 > a2){
            temp = a1;
            a1 = a2;
            a2 = temp;
        }
        List<Music> musicList = DBManager.getInstance().findAllMusicInRange(a1,a2);
        for(Music music: musicList){
            check = true;
            count++;
            System.out.format("|%-3d|%-40s|%2d:%-2d| %-20s|%n",count,music.getName(),music.getDuration().getValue()/60,music.getDuration().getValue()%60,music.getStyle());
        }
        if (!check){
            System.out.println("Немає елементів");
            Logger.log("Немає елементів.\n");
            return  false;
        }
        return true;
    }
}
