package com.example.micke.labb2mobil.Activities.Buttons;

import android.view.View;

import com.example.micke.labb2mobil.Model.GameState;

/**
 * Created by Micke on 11/28/2017.
 */

public class StartGameListener implements View.OnClickListener{

    private int gameSize;

    public StartGameListener(int gameSize){
        this.gameSize = gameSize;
    }

    @Override
    public void onClick(View v) {
        GameState.startNewGame(gameSize);
    }
}
