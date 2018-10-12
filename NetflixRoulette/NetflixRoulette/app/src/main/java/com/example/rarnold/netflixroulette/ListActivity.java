package com.example.rarnold.netflixroulette;

import android.app.Activity;
import android.os.Bundle;

import com.example.bccarducci.netflixroulette.R;
/**
 * Created by Brian C on 3/10/2016.
 * The list activity class for all movies
 */
public class ListActivity extends Activity implements MovieFragment.MovieListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }


    @Override
    public void movieClicked(int id){

    }

}
