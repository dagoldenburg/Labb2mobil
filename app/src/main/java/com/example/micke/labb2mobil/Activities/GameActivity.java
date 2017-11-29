package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.micke.labb2mobil.Model.EmptySpace;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmptySpace.createEmptySpaces(this);
        setContentView(R.layout.activity_game);
    }
}
