package com.example.courseworkap.commandSetup;

import com.example.courseworkap.manager.Menu;

public class ExitCommand extends SetupCommand{

    public ExitCommand() {
        setName("Exit");
    }

    @Override
    public boolean execute() {
        return true;
    }
}
