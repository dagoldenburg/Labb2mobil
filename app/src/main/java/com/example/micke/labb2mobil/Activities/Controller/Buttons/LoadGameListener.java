package com.example.micke.labb2mobil.Activities.Controller.Buttons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.micke.labb2mobil.Activities.LoadGameActivity;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Tasks.LoadGame;

/**
 * Created by Micke on 11/26/2017.
 */

public class LoadGameListener implements View.OnClickListener {

    private Context context;

    public LoadGameListener(Context c){
        context = c;
    }

    @Override
    public void onClick(View v) {
       // Intent intent = new Intent(context, LoadGameActivity.class);
        //context.startActivity(intent);


        LoadGame loadGame = new LoadGame(new LoadGame.TaskListener() {
            @Override
            public void onFinished(Boolean result) {
                ViewState.startNewGame(context,6);
                Intent intent = new Intent(context,GameActivity.class);
                context.startActivity(intent);
            }
        },context,"Douglas");


        loadGame.execute();
    }
}
