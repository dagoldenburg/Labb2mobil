package com.example.micke.labb2mobil.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.micke.labb2mobil.Activities.Controller.Buttons.ChoiceGameListener;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;
import com.example.micke.labb2mobil.Tasks.LoadGame;

import java.io.File;
import java.util.ArrayList;

public class LoadGameActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);


        ListView  listView= (ListView) findViewById(R.id.gameNames);
        File[] files = getFilesDir().listFiles();
        ArrayList<String> filenames = new ArrayList<>();

        for(int i =0;i< files.length;i++){

            filenames.add(files[i].getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, filenames);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ChoiceGameListener(this));


    }

}
