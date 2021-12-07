package com.example.courseworkap.entity.music;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

public class RockMusic extends Music{
    public RockMusic(ObservableStringValue name, ObservableValue<Integer> duration){
        super(name, duration, new SimpleStringProperty("Rock"));
    }
}
