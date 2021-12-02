package com.example.courseworkap.manager;

import com.example.courseworkap.entity.music.*;

public class MusicManager {
    private static String[] genres = {"Country","Electronic","Jazz","Pop","Rock","Hip hop","Another genre"};

    private MusicManager(){}

    public static int genreStringToIntConverter(String genre){
        int count =0;
        for(String s: genres){
            if(s.equalsIgnoreCase(genre)){
                return  count;
            }
            count++;
        }
        return genres.length-1;
    }

    public static Music getCreatedClass(int id, String name, int duration, int genre){
        Music currentMusic;
        switch (genre){
            case(0):
                currentMusic = new CountryMusic(name,duration);
                break;
            case(1):
                currentMusic = new ElectronicMusic(name,duration);
                break;
            case(2):
                currentMusic = new JazzMusic(name,duration);
                break;
            case(3):
                currentMusic = new PopMusic(name,duration);
                break;
            case (4):
                currentMusic = new RockMusic(name, duration);
                break;
            case(5):
                currentMusic = new HipHopMusic(name, duration);
                break;
            default:
                currentMusic = new UknownMusic(name, duration);
                break;
        }
        currentMusic.setId(id);
        return currentMusic;
    }

    public static void printMusicGenres(){
        int count=0;
        System.out.println("\n┌-----------------┐");
        for(String s: genres){
            count++;
            System.out.format("|%1d|%-15s|%n",count,s);
        }
        System.out.println("└-----------------┘");
    }
}
