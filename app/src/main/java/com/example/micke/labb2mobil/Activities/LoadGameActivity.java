package com.example.micke.labb2mobil.Activities;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.micke.labb2mobil.Activities.View.GameView;
import com.example.micke.labb2mobil.R;

public class LoadGameActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        gameView= (GameView) findViewById(R.id.gameView);

    }
}
