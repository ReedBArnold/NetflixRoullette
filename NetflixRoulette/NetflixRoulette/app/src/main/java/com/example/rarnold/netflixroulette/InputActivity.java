package com.example.rarnold.netflixroulette;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import android.view.Menu;
/**
 * Created by Reed on 3/6/2016.
 * The general class for all input activity
 */
public class InputActivity extends AppCompatActivity {

    private FetchMovie fetchMovie;
    private static final String INPUT_ACTIVITY = "inputActivity";

    static ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        GridLayout background = (GridLayout)findViewById(R.id.inputLayout);
        background.setBackgroundColor(SettingsActivity.colorHolder);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("previous", INPUT_ACTIVITY);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createToast() {
        Toast toast = Toast.makeText(getApplicationContext(), "No movies found... Try again", Toast.LENGTH_SHORT);
        toast.show();
    }

    //AsyncTask to retrieve movie list from NetflixRoulette.net
    private class FetchMovie extends AsyncTask<String, Void, String> {

        //Input text variables
        EditText editTextA = (EditText) findViewById(R.id.actor);
        String actor = editTextA.getText().toString().replace(" ","%20");

        EditText editTextD = (EditText) findViewById(R.id.director);
        String director = editTextD.getText().toString().replace(" ","%20");

        EditText editTextT = (EditText) findViewById(R.id.title);
        String title = editTextT.getText().toString().replace(" ","%20");

        ArrayList<Movie> movies = new ArrayList();

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            URL url = null;

            try {
                //Determines which input was used to search
                if(!actor.equals("")){
                    url = new URL("https://netflixroulette.net/api/api.php?actor=" + actor);
                    director.equals("");
                    title.equals("");
                }
                if(!director.equals("")){
                    url = new URL("https://netflixroulette.net/api/api.php?director=" + director);
                    actor.equals("");
                    title.equals("");
                }
                if(!title.equals("")){
                    url = new URL("http://netflixroulette.net/api/api.php?title=" + title);
                    actor.equals("");
                    director.equals("");
                }

                //Creates URL connection with valid input
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                //If the input is empty
                if (inputStream == null) {
                    return null;
                }
                JsonReader readerJSON = new JsonReader(new InputStreamReader(inputStream));
                movies = readMoviesArray(readerJSON);
            } catch (IOException e) {
                Log.e("exception", "Error " + e.getMessage(), e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                return null;
            }
        }//Ends doInBackground()


        protected void onPostExecute(String result) {
            Random random = new Random();
            int index = random.nextInt(movies.size() - 1);

            Intent intent = new Intent(InputActivity.this, MovieActivity.class);

            intent.putExtra("title", movies.get(index).getTitle());
            intent.putExtra("genre", movies.get(index).getGenre());
            intent.putExtra("director", movies.get(index).getDirector());
            intent.putExtra("cast", movies.get(index).getCast());
            intent.putExtra("summary", movies.get(index).getSummary());

            startActivity(intent);
        }
    }

    public void go(View view){
        fetchMovie = new FetchMovie();
        fetchMovie.execute();
    }

    public ArrayList<Movie> readMoviesArray(JsonReader reader) throws IOException {
        ArrayList<Movie> movies = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            movies.add(readMovie(reader));
        }
        reader.endArray();
        return movies;
    }

    public Movie readMovie(JsonReader reader) throws IOException {
        String show_title = null;
        String genre = null;
        String show_cast = null;
        String director = null;
        String summary = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("show_title")) {
                show_title = reader.nextString();

            } else if (name.equals("category")){
                genre = reader.nextString();
            } else if (name.equals("show_cast")) {
                show_cast = reader.nextString();
            } else if (name.equals("director")){
                director = reader.nextString();
            } else if (name.equals("summary")) {
                summary = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new Movie(show_title, genre, show_cast, director, summary);

    }
}
