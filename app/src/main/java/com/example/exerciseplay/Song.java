package com.example.exerciseplay;

public class Song {
    private String title;
    private int duration;
    private boolean explicit_lyrics;
    private String preview;
    private String md5_image;
    private String artist_name;
    private String album_name;

    public Song(){
        this.title = "";
        this.duration = 0;
        this.explicit_lyrics = false;
        this.preview = "";
        this.md5_image = "";
        this.artist_name = "";
        this.album_name = "";
    }

    public Song(String title, int duration, boolean explicit_lyrics, String preview, String md5_image, String artist_name, String album_name){
        this.title = title;
        this.duration = duration;
        this.explicit_lyrics = explicit_lyrics;
        this.preview = preview;
        this.md5_image = md5_image;
        this.artist_name = artist_name;
        this.album_name = album_name;
    }
    public String toString(){
        String whole;
        whole = "Title: " + title +'\n';
        whole += "Duration: " + String.valueOf(duration) + '\n';
        whole += "Explicit: " + String.valueOf(explicit_lyrics) + '\n';
        whole += "Artist Name: " + artist_name + '\n';
        whole += "Album title: " + album_name + '\n';
        return whole;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isExplicit_lyrics() {
        return explicit_lyrics;
    }

    public void setExplicit_lyrics(boolean explicit_lyrics) {
        this.explicit_lyrics = explicit_lyrics;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getMd5_image() {
        return md5_image;
    }

    public void setMd5_image(String md5_image) {
        this.md5_image = md5_image;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
