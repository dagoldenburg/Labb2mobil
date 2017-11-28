package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.micke.labb2mobil.Activities.Buttons.StartGameListener;
import com.example.micke.labb2mobil.R;

public class LevelChoiceActivity extends AppCompatActivity {

    private Button threeButton;
    private Button sixButton;
    private Button nineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choice);
        threeButton = findViewById(R.id.button_three_morris);
        sixButton = findViewById(R.id.button_six_morris);
        nineButton = findViewById(R.id.button_nine_morris);
        threeButton.setOnClickListener(new StartGameListener(3));
        sixButton.setOnClickListener(new StartGameListener(6));
        nineButton.setOnClickListener(new StartGameListener(9));
    }
}
