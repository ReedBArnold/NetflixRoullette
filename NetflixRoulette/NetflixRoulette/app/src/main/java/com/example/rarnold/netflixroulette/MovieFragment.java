package com.example.rarnold.netflixroulette;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Reed on 3/24/2016.
 * A simple movie fragment subclass.
 */

public class MovieFragment extends Fragment {

    private String title;
    private String genre;
    private String cast;
    private String summary;
    private String director;

    ArrayList<Movie> movies = InputActivity.movies;

    TextView titleView, castView, genreView, summaryView, directorView;

    static interface MovieListListener{
        void movieClicked(int id);
    };

    private MovieListListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }


    public void setMovieInfo(){
        View view = this.getView();
        Intent intent = new Intent(getActivity(), InputActivity.class);

        director = intent.getStringExtra("director");
        title = intent.getStringExtra("title");
        genre = intent.getStringExtra("genre");
        cast = intent.getStringExtra("cast");
        summary = intent.getStringExtra("summary");

        titleView = (TextView) view.findViewById(R.id.title);
        directorView = (TextView) view.findViewById(R.id.director);
        castView = (TextView) view.findViewById(R.id.cast);
        genreView = (TextView) view.findViewById(R.id.genre);
        summaryView = (TextView) view.findViewById(R.id.summary);

        titleView.setText(title);
        directorView.setText(director);
        castView.setText(cast);
        genreView.setText(genre);
        summaryView.setText(summary);
    }


}
