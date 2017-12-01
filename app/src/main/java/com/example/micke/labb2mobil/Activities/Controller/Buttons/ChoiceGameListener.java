package com.example.micke.labb2mobil.Activities.Controller.Buttons;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.micke.labb2mobil.Activities.GameActivity;
import com.example.micke.labb2mobil.Activities.LoadGameActivity;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.Tasks.LoadGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by douglas on 11/30/17.
 */

public class ChoiceGameListener implements AdapterView.OnItemClickListener {
    private  Context context;
    public ChoiceGameListener(Context context) {
        this.context=context;
    }

    /**
     * create Gamtestete object from the filename that user choices to load
     * and create view state from that gamte state
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String fileName =(String)   parent.getItemAtPosition(position);
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameState.setGameState( (GameState) ois.readObject());
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();

        }

        ViewState.startNewGame(context,GameState.getGameState().getGamesize());
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }
}
