package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;


import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;

import java.util.ArrayList;


public class GameView extends View {
    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.CYAN);
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Drawable d : PosDrawable.getEmptyPositions()) {
            d.draw(canvas);
        }
        for (Drawable d : PosDrawable.getMarkers()) {
            d.draw(canvas);
        }
    }

    int move;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if ((GameState.getGameState().getWhiteMarker() != 0 || GameState.getGameState().getBlackMarker() != 0)) {
                Position position = PosDrawable.seeIfTouch(event.getX(), event.getY(), PosDrawable.getEmptyPositions());
                if (GameState.getGameState().set(position.getPosition())) {
                    Drawable d = null;
                    if (GameState.getGameState().isWhitePlayersTurn()) {
                        d = getResources().getDrawable(R.drawable.white_circle);
                        Log.i("PositionDrawable", "white");
                    } else {
                        Log.i("PositionDrawable", "black");
                        d = getResources().getDrawable(R.drawable.black_circle);
                    }
                    Rect rect = new Rect((int) position.getX(), (int) position.getY(), (int) (position.getX() + position.getWidth()), (int) (position.getY() + position.getHeight()));
                    d.setBounds(rect);
                    PosDrawable.getMarkers().add(new PosDrawable(d, position));
                }
            } else {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    move = PosDrawable.seeIfTouch(event.getX(), event.getY(), PosDrawable.getMarkers()).getPosition();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.i("wqer","testi "+(int) event.getY()+" "+(int) event.getX());
                    try {
                        PosDrawable.getMarkers().get(move).updateDrawable((int) event.getX(),(int) event.getY());
                        invalidate();
                    }catch(IndexOutOfBoundsException e){

                    }
                } else {
                    move = -1;
                }
            }
        }catch(NullPointerException e){
            invalidate();
        }
        invalidate();
        return true;
    }

}
