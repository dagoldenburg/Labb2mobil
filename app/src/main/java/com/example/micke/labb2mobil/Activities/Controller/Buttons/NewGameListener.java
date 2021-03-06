package com.example.micke.labb2mobil.Activities.Controller.Buttons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.micke.labb2mobil.Activities.LevelChoiceActivity;

/**
 * Created by Micke on 11/26/2017.
 */

public class NewGameListener implements View.OnClickListener {

    private Context context;

    public NewGameListener(Context c){
        context = c;
    }

    /**
     * on click action when user choices a lever 3-6-9
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, LevelChoiceActivity.class);
        context.startActivity(intent);
    }
}
