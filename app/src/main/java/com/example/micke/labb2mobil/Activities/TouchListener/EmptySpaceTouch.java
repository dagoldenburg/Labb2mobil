package com.example.micke.labb2mobil.Activities.TouchListener;

import android.view.MotionEvent;
import android.view.View;

import com.example.micke.labb2mobil.Model.EmptySpace;
import com.example.micke.labb2mobil.Model.GameState;

/**
 * Created by Micke on 11/28/2017.
 */

public class EmptySpaceTouch implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int position = EmptySpace.getPositionByViewId(v.getId());
        if(position!=-1) {
            GameState.getGameState().set(position);
            return true;
        }else return false;
    }

}
