package com.example.micke.labb2mobil.Activities.TouchListener;

import android.view.MotionEvent;
import android.view.View;

import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;

/**
 * Created by Micke on 11/28/2017.
 */

public class EmptySpaceTouch implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            if(GameState.getGameState().set(Position.fetchPosition(v.getX(),v.getY()))){
                //Skapa en marker som ritas på gameview
                return true;
            }else return false;
    }

}
