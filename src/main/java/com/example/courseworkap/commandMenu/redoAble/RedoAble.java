package com.example.courseworkap.commandMenu.redoAble;

import com.example.courseworkap.Logger;
import com.example.courseworkap.entity.Disk;

public interface RedoAble {
     default Disk saveDisk(Disk save){
        try {
            return save.clone();
        } catch (Exception e) {
            System.out.println("Неправильно використаний клас для колування.");
            Logger.logMistake("Неправильно використаний клас для клонування "+e.toString()+"\n");
            return save;
        }
    }
}
