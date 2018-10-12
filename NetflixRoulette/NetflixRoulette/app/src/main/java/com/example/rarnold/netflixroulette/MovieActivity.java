package com.example.rarnold.netflixroulette;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MovieActivity extends Activity {

    private String title;
    private String genre;
    private String cast;
    private String summary;
    private ShareActionProvider shareActionProvider;
    ArrayList<Movie> movies = InputActivity.movies;

    TextView titleView, castView, genreView, summaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        RelativeLayout settingsBackground = (RelativeLayout)findViewById(R.id.movieFragLayout);
        settingsBackground.setBackgroundColor(SettingsActivity.colorHolder);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        genre = intent.getStringExtra("genre");
        cast = intent.getStringExtra("cast");
        summary = intent.getStringExtra("summary");

        titleView = (TextView) findViewById(R.id.title);
        castView = (TextView) findViewById(R.id.cast);
        genreView = (TextView) findViewById(R.id.genre);
        summaryView = (TextView) findViewById(R.id.summary);

        titleView.setText(title);
        castView.setText(cast);
        genreView.setText(genre);
        summaryView.setText(summary);

        //Code to set fragment info
        MovieFragment frag = (MovieFragment) getFragmentManager().findFragmentById(R.id.movieFrag);
        frag.setMovieInfo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(menuItem, shareActionProvider);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("previous", "movieActivity");
                startActivity(intent);

            case R.id.action_retry:
                Random random = new Random();
                int index = random.nextInt(movies.size() - 1);
                String nextTitle = movies.get(index).getTitle();
                if(nextTitle == title){
                    index++;
                }
                titleView.setText(movies.get(index).getTitle());
                genreView.setText(movies.get(index).getGenre());
                castView.setText(movies.get(index).getCast());
                summaryView.setText(movies.get(index).getSummary());

            case R.id.action_share:
                setIntent("Check out the movie " + titleView.getText() + ", I think you would like it!");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setIntent(String text) {
        Intent intent = ShareCompat.IntentBuilder.from(this).setType("text/plain").setText(text).getIntent();
        if (shareActionProvider != null){
            shareActionProvider.setShareIntent(intent);
        }
    }
}
