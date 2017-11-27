package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.micke.labb2mobil.Activities.Buttons.LoadGameListener;
import com.example.micke.labb2mobil.Activities.Buttons.NewGameListener;
import com.example.micke.laboration2.R;

public class MainActivity extends AppCompatActivity {

    Button newGameButton;
    Button loadGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = (Button) findViewById(R.id.button_new_game);
        newGameButton.setOnClickListener(new NewGameListener(this));

        loadGameButton = (Button) findViewById(R.id.button_load_game);
        loadGameButton.setOnClickListener(new LoadGameListener(this));
    }
}
