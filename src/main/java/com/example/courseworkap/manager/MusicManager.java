package com.example.courseworkap.manager;

import com.example.courseworkap.entity.music.*;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

public class MusicManager {
    private static String[] genres = {"Country","Electronic","Jazz","Pop","Rock","Hip hop","Another genre"};

    private MusicManager(){}

    public static int genreStringToIntConverter(String genre){
        int count =0;
        for(String s: genres){
            if(s.equalsIgnoreCase(genre)){
                return  count+1;
            }
            count++;
        }
        return genres.length-1;
    }

    public static Music getCreatedClass(int id, ObservableStringValue name, ObservableValue<Integer> duration, ObservableStringValue genre){
        Music currentMusic;
        switch (genre.getValue()){
            case("Country"):
                currentMusic = new CountryMusic(name,duration);
                break;
            case("Electronic"):
                currentMusic = new ElectronicMusic(name,duration);
                break;
            case("Jazz"):
                currentMusic = new JazzMusic(name,duration);
                break;
            case("Pop"):
                currentMusic = new PopMusic(name,duration);
                break;
            case ("Rock"):
                currentMusic = new RockMusic(name, duration);
                break;
            case("Hip hop"):
                currentMusic = new HipHopMusic(name, duration);
                break;
            default:
                currentMusic = new UnknownMusic(name, duration);
                break;
        }
        currentMusic.setId(id);
        return currentMusic;
    }

    public static String[] getGenres() {
        return genres;
    }
}
