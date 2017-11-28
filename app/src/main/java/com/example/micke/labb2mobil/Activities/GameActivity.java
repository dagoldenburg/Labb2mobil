package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Position.initPositions(this);
        setContentView(R.layout.activity_game);
    }
}
