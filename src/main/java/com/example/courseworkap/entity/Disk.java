package com.example.courseworkap.entity;

import com.example.courseworkap.entity.music.Music;

import java.util.ArrayList;
import java.util.List;

public class Disk {
    private List<Music> musicList = new ArrayList<>();
    private int id;
    private String name;

    public Disk(){
        id = 0;
        name = "None";
    }

    public Disk(String name){
        id =0;
        this.name = name;
    }

    public Disk(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public String getName() {
        return name;
    }

    public Disk clone() throws CloneNotSupportedException {
        Disk disk = new Disk();
        disk.setMusicList(new ArrayList<>(musicList));
        return disk;
    }

    @Override
    public int hashCode() {
        int hash =0;
        for(Music music:musicList){
            hash+=music.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
