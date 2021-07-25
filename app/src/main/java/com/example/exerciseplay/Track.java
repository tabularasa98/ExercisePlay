package com.example.exerciseplay;

import java.util.List;

public class Track {
    public class Artist{
        private int id;
        private String name;
        private String link;
        private String picture_small;
        private String picture_medium;
        private String picture_big;
        private String picture_xl;
        private String tracklist;
        private String type;

        public Artist(int id, String name, String link, String picture_small, String picture_medium, String picture_big, String picture_xl, String tracklist, String type){
            this.id = id;
            this.name = name;
            this.link = link;
            this.picture_small = picture_small;
            this.picture_medium = picture_medium;
            this.picture_big = picture_big;
            this.picture_xl = picture_xl;
            this.tracklist = tracklist;
            this.type = type;
        }
    }
    public class Album{
        private int id;
        private String title;
        private String cover;
        private String cover_small;
        private String cover_medium;
        private String cover_big;
        private String cover_xl;
        private String md5_image;
        private String tracklist;
        private String type;

        public Album(int id, String title, String cover, String cover_small, String cover_medium, String cover_big, String cover_xl, String md5_image, String tracklist, String type){
            this.id = id;
            this.title = title;
            this.cover = cover;
            this.cover_small = cover_small;
            this.cover_medium = cover_medium;
            this.cover_big = cover_big;
            this.cover_xl = cover_xl;
            this.md5_image = md5_image;
            this.tracklist = tracklist;
            this.type = type;
        }
    }
    private int id;
    private boolean readable;
    private String title;
    private String title_short;
    private String title_version;
    private String link;
    private int duration;
    private int rank;
    private boolean explicit_lyrics;
    private int explicit_content_lyrics;
    private int explicit_content_cover;
    private String preview;
    private String md5_image;
    private Artist artist;
    private Album album;

    public Track( int id, boolean readable, String title, String title_short, String title_version, String link, int duration, int rank, boolean explicit_lyrics, int explicit_content_lyrics, int explicit_content_cover, String preview, String md5_image, Artist artist, Album album){
        this.id = id;
        this.readable = readable;
        this.title = title;
        this.title_short = title_short;
        this.title_version = title_version;
        this.link = link;
        this.duration = duration;
        this.rank = rank;
        this.explicit_lyrics = explicit_lyrics;
        this.explicit_content_lyrics = explicit_content_lyrics;
        this.explicit_content_cover = explicit_content_cover;
        this.preview = preview;
        this.md5_image = md5_image;
        this.artist = artist;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }


    public boolean isExplicit_lyrics() {
        return explicit_lyrics;
    }


    public String getMd5_image() {
        return md5_image;
    }

}
