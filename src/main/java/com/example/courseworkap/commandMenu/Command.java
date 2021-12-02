package com.example.courseworkap.commandMenu;

import com.example.courseworkap.entity.Disk;

public abstract class Command {
    private  String name;
    private Disk save;

    public abstract boolean execute();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Disk getDisk() {
        return save;
    }

    public void setDisk(Disk save) {
        this.save = save;
    }

    protected Command(){
        name = "Command";
    }
    protected Command(Disk disk){
        save = disk;
        name = "Command";
    }
}
