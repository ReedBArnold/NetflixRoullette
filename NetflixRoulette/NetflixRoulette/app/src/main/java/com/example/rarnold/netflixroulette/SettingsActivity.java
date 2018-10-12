package com.example.rarnold.netflixroulette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class SettingsActivity extends AppCompatActivity {

    public static int colorHolder = Color.parseColor("#dc0707");
    String previousActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        previousActivity = intent.getStringExtra("previous");
        RelativeLayout settingsBackground = (RelativeLayout)findViewById(R.id.settingsLayout);
        settingsBackground.setBackgroundColor(colorHolder);
    }

    public void saveChanges(View view) {
        if (getIntent().getStringExtra("previous").equals("inputActivity")) {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
        } else if (getIntent().getStringExtra("previous").equals("movieActivity")) {
            Intent intent = new Intent(this, MovieActivity.class);
            startActivity(intent);
        }
    }

    public void setColor(View view){
        switch(view.getId()){
            case R.id.red:
                RelativeLayout settingsBackground = (RelativeLayout)findViewById(R.id.settingsLayout);
                settingsBackground.setBackgroundColor(Color.parseColor("#dc0707"));
                colorHolder = Color.parseColor("#dc0707");
                break;
            case R.id.blue:
                settingsBackground = (RelativeLayout)findViewById(R.id.settingsLayout);
                settingsBackground.setBackgroundColor(Color.BLUE);
                colorHolder = Color.BLUE;
                break;
            case R.id.yellow:
                settingsBackground = (RelativeLayout)findViewById(R.id.settingsLayout);
                settingsBackground.setBackgroundColor(Color.YELLOW);
                colorHolder = Color.YELLOW;
                break;
            case R.id.green:
                settingsBackground = (RelativeLayout)findViewById(R.id.settingsLayout);
                settingsBackground.setBackgroundColor(Color.GREEN);
                colorHolder = Color.GREEN;
                break;
        }
    }
}
