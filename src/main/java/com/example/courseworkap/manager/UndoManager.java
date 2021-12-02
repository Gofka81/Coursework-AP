package com.example.courseworkap.manager;

import com.example.courseworkap.commandMenu.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class UndoManager{

    private static int size =0;
    private static final int capacity =5;

    private static final Deque<Command> commandsStack = new ArrayDeque<>();

    private  UndoManager(){}

    public static void add(Command command){
        if(size+1>capacity){
            commandsStack.removeLast();
        }
        size++;
        commandsStack.addFirst(command);
    }

    public static Command pop(){
        if(size<= 0){
            return null;
        }
        size--;
        return  commandsStack.removeFirst();
    }

    public static boolean isEmpty(){
        return commandsStack.isEmpty();
    }

    public static Deque<Command> getCommandsStack() {
        return commandsStack;
    }
}
