package com.example.micke.labb2mobil.Activities.Buttons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.micke.labb2mobil.Activities.GameActivity;

/**
 * Created by Micke on 11/26/2017.
 */

public class NewGameListener implements View.OnClickListener {

    private Context context;

    public NewGameListener(Context c){
        context = c;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }
}
