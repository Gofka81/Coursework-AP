package com.example.courseworkap.entity.music;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

public class ElectronicMusic extends Music{
    public ElectronicMusic(ObservableStringValue name, ObservableValue<Integer> duration){
        super(name, duration, new SimpleStringProperty("Electronic"));
    }
}
