package com.example.courseworkap.commandSetup;

public abstract class SetupCommand {
    private  String name;

    public abstract boolean execute();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected SetupCommand(){
        name = "Command";
    }
}
