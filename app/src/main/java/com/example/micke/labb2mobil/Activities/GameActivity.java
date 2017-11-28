package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.micke.labb2mobil.Activities.View.GameView;
import com.example.micke.labb2mobil.R;


public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView= (GameView) findViewById(R.id.gameView);
    }
}
