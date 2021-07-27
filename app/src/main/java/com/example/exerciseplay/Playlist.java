package com.example.exerciseplay;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private int duration;
    private List<Song> songs;

    public Playlist(){
        this.name = "";
        this.duration = 0;
        this.songs = new ArrayList<>();
    }

    @NonNull
    public String toString(){
        String whole;
        whole = "Name: " + name +'\n';
        whole += "Duration: " + duration + '\n';
        whole += "Size: " + songs.size() + '\n';
        return whole;
    }

    public Playlist(String name, int duration, List<Song> songs){
        this.name = name;
        this.duration = duration;
        this.songs = songs;
    }

    public void updateDuration(){
        this.duration = 0;
        for(Song s : songs){
            this.duration += s.getDuration();
        }
    }
    public void addSong(Song s){
        this.songs.add(s);
    }
    public void removeSong(int pos){
        if(pos < songs.size()){
            songs.remove(pos);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

