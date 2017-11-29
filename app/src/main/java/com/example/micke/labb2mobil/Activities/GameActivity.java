package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.micke.labb2mobil.Activities.View.GameView;
import com.example.micke.labb2mobil.Activities.View.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;

public class GameActivity extends AppCompatActivity {
    GameView gameView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PosDrawable.initPositions(this);
        setContentView(new GameView(this));
    }
}
