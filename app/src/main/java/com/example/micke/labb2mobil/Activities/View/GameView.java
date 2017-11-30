package com.example.micke.labb2mobil.Activities.View;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;


import com.example.micke.labb2mobil.Activities.Controller.TouchController;
import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;
import com.example.micke.labb2mobil.Tasks.SaveGame;

import java.util.ArrayList;


public class GameView extends View {

    Paint line = new Paint();
    Paint paint = new Paint();
    Paint winPaint = new Paint();



    ArrayList<Line> lines = new ArrayList<>();

    private class Line{
        float startX, startY, stopX, stopY;
        public float getStartX() {return startX;}
        public float getStartY() {return startY;}
        public float getStopX() {return stopX;}
        public float getStopY() {return stopY;}

        public Line(float startX, float startY, float stopX, float stopY) {
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
        }
    }

    private void fillLines(){
        for (PosDrawable d : ViewState.getViewState().getEmptyPositions()) {
        for(PosDrawable pd:ViewState.getViewState().getEmptyPositions())
            if(GameState.getGameState().isPossibleMove(d.getPosition().getPosition(),pd.getPosition().getPosition(),true)){
                lines.add(new Line(d.getProxy().getBounds().centerX(),d.getProxy().getBounds().centerY(),
                        pd.getProxy().getBounds().centerX(),pd.getProxy().getBounds().centerY()));
            }
        }
    }

    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.CYAN);
        paint.setTextSize(50);
        winPaint.setTextSize(60);


        line.setStrokeWidth(5);
        line.setColor(Color.BLACK);
        fillLines();
    }

    /**
     * Redraws the view
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {

        for(Line l : lines){
            canvas.drawLine(l.getStartX(),l.getStartY(),l.getStopX(),l.getStopY(),line);
        }
        for (PosDrawable d : ViewState.getViewState().getEmptyPositions()) {
            d.draw(canvas);
        }
        for (Drawable d : ViewState.getViewState().getMarkers()) {
            d.draw(canvas);
        }
        switch(GameState.getGameState().checkIfWin()){
            case -1: canvas.drawText("BLACK PLAYER WON"  , ViewState.getViewState().getWinTextPos().x,ViewState.getViewState().getWinTextPos().y,winPaint);
                canvas.drawText("PRESS ANYWHERE TO RETURN TO MENU"  , ViewState.getViewState().getWinTextPos().x,ViewState.getViewState().getWinTextPos().y+125,winPaint);
                break;
            case 1: canvas.drawText("WHITE PLAYER WON"  , ViewState.getViewState().getWinTextPos().x,ViewState.getViewState().getWinTextPos().y+80,winPaint);
                canvas.drawText("PRESS ANYWHERE TO RETURN TO MENU"  , ViewState.getViewState().getWinTextPos().x,ViewState.getViewState().getWinTextPos().y+125,winPaint);
                break;
            default:break;
        }
        if(GameState.getGameState().isWhitePlayersTurn()){
            canvas.drawText("Turn : player white"  , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y,paint);
            canvas.drawText("Number of markers :" +GameState.getGameState().getWhiteMarker()   , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y+ 40,paint);
        }else {
            canvas.drawText("Turn : player black"  , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y,paint);
            canvas.drawText("Number of markers :" +GameState.getGameState().getBlackMarker()   , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y+ 40,paint);
        }



    }


    /**
     *  Handles touch events from the player
     * @param event
     * @return
     */
    Position from;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(GameState.getGameState().checkIfWin()!=0){
                ((Activity)getContext()).finish();
            }
            try {
                from = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers());
            }catch(NullPointerException e){

            }
            TouchController.set(this,event);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE && (GameState.getGameState().getBlackMarker()==0 || GameState.getGameState().getWhiteMarker()==0)) {
            try {
                ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from.getPosition())).updateDrawable((int) event.getX(),(int) event.getY());
                invalidate();
            }catch(IndexOutOfBoundsException|NullPointerException e){

            }
        }else{
            TouchController.move(from,event,this);
        }
        invalidate();
        return true;
    }


}
