package com.example.rarnold.netflixroulette;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Reed on 3/8/2016.
 * The new movie type
 */

public class Movie {

    private String title, genre, cast, director, summary;

    public Movie(String title, String genre, String cast, String director, String summary) {
        this.title = title;
        this.genre = genre;
        this.cast = cast;
        this.director = director;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre(){ return genre; }

    public String getCast(){
        return cast;
    }

    public String getDirector(){
        return director;
    }

    public String getSummary(){
        return summary;
    }
}




