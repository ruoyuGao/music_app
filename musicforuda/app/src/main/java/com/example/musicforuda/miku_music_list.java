package com.example.musicforuda;

/**
 * Created by 高若宇 on 2018/5/19.
 */

public class miku_music_list {
    private String songs;
    private String singers;
    public miku_music_list(String song,String singer){
        songs=song;
        singers=singer;
    }
    public String getSongs(){
        return songs;
    }
    public  String getSingers(){
        return singers;
    }
}
