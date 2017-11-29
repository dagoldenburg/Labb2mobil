package com.example.micke.labb2mobil.Activities.Buttons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.micke.labb2mobil.Activities.GameActivity;
import com.example.micke.labb2mobil.Activities.View.PosDrawable;
import com.example.micke.labb2mobil.Model.GameState;

/**
 * Created by Micke on 11/28/2017.
 */

public class StartGameListener implements View.OnClickListener{

    private int gameSize;
    private Context context;

    public StartGameListener(int gameSize,Context context){
        this.gameSize = gameSize;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        GameState.startNewGame(gameSize);
        PosDrawable.initPositions(context,gameSize);
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }
}
