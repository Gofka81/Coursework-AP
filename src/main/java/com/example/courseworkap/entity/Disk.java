package com.example.courseworkap.entity;

import com.example.courseworkap.entity.music.Music;

import java.util.ArrayList;
import java.util.List;

public class Disk {
    private List<Music> musicList = new ArrayList<>();
    private String name;

    public Disk(){}

    public Disk(String name){
        this.name = name;
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
        StringBuilder sb = new StringBuilder();
        for(Music music: musicList){
            sb.append(music.getName()).append(System.lineSeparator())
                    .append(music.getDuration()).append(System.lineSeparator())
                    .append(music.getStyle()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
