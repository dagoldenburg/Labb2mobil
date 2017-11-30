package com.example.micke.labb2mobil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.micke.labb2mobil.Activities.Controller.Buttons.LoadGameListener;
import com.example.micke.labb2mobil.Activities.Controller.Buttons.NewGameListener;
import com.example.micke.labb2mobil.R;

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
