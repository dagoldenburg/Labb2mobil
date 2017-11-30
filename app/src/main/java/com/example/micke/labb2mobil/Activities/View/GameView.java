package com.example.micke.labb2mobil.Activities.View;

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


import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;


public class GameView extends View {
    
    Paint paint = new Paint();
    Paint winPaint = new Paint();
    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.CYAN);
        paint.setTextSize(50);
        winPaint.setTextSize(60);

    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Drawable d : ViewState.getEmptyPositions()) {
            d.draw(canvas);
        }
        for (Drawable d : ViewState.getMarkers()) {
            d.draw(canvas);
        }
        switch(GameState.getGameState().checkIfWin()){
            case -1: canvas.drawText("BLACK PLAYER WON"  , ViewState.getWinTextPos().x,ViewState.getWinTextPos().y,winPaint);
                canvas.drawText("PRESS ANYWHERE TO RETURN TO MENU"  , ViewState.getWinTextPos().x,ViewState.getWinTextPos().y+125,winPaint);
                break;
            case 1: canvas.drawText("WHITE PLAYER WON"  , ViewState.getWinTextPos().x,ViewState.getWinTextPos().y+80,winPaint);
                canvas.drawText("PRESS ANYWHERE TO RETURN TO MENU"  , ViewState.getWinTextPos().x,ViewState.getWinTextPos().y+125,winPaint);
                break;
            default:break;
        }
        if(GameState.getGameState().isWhitePlayersTurn()){
            canvas.drawText("Turn : player white"  , ViewState.getTextPos().x,ViewState.getTextPos().y,paint);
            canvas.drawText("Number of marker :" +GameState.getGameState().getWhiteMarker()   , ViewState.getTextPos().x,ViewState.getTextPos().y+ 40,paint);
        }else {
            canvas.drawText("Turn : player black"  , ViewState.getTextPos().x,ViewState.getTextPos().y,paint);
            canvas.drawText("Number of marker :" +GameState.getGameState().getBlackMarker()   , ViewState.getTextPos().x,ViewState.getTextPos().y+ 40,paint);
        }

    }

    int from;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(GameState.getGameState().checkIfWin()!=0){
                        ((Activity)getContext()).finish();
                     }
                     try {
                         from = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getMarkers()).getPosition();
                     }catch(NullPointerException e){

                    }
                    Position position = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getEmptyPositions());
                     try {
                         if (GameState.getGameState().set(position.getPosition())) {
                             Log.i("asd","position "+position.getPosition());
                             Drawable d = null;
                             if (!GameState.getGameState().isWhitePlayersTurn()) {
                                 d = getResources().getDrawable(R.drawable.white_circle);
                                 Log.i("PositionDrawable", "white");
                             } else {
                                 Log.i("PositionDrawable", "black");
                                 d = getResources().getDrawable(R.drawable.black_circle);
                             }
                             Rect rect = new Rect((int) position.getX(), (int) position.getY(), (int) (position.getX() + position.getWidth()), (int) (position.getY() + position.getHeight()));
                             d.setBounds(rect);
                             PosDrawable pos = new PosDrawable(d, new Position((int) position.getX(), (int) position.getY(), (int) position.getWidth(),
                                     (int) position.getHeight(), position.getPosition()));

                             ViewState.getMarkers().add(pos);
                         }else if(GameState.getGameState().areThreeOnRow()) {
                             Log.i("asd","remove");
                             Position pos = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getMarkers());
                             if (GameState.getGameState().remove(pos.getPosition())) {
                                 ViewState.getMarkers().remove(ViewState.fetchMarker(pos.getPosition()));
                                 invalidate();
                             }
                         }
                     }catch(NullPointerException e){
                         Log.i("asd","asdassad");
                     }
                }else if (event.getAction() == MotionEvent.ACTION_MOVE && (GameState.getGameState().getBlackMarker()==0 || GameState.getGameState().getWhiteMarker()==0)) {
                   try {
                        ViewState.getMarkers().get(ViewState.fetchMarker(from)).updateDrawable((int) event.getX(),(int) event.getY());
                        invalidate();
                    }catch(IndexOutOfBoundsException e){
                    }
                }else{
                    try{
                    int to = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getEmptyPositions()).getPosition();

                    if(GameState.getGameState().move(from,to)){
                        ViewState.getMarkers().get(ViewState.fetchMarker(from)).updateDrawable(
                                (int) ViewState.getEmptyPositions().get(to).getPosition().getX(),
                                (int) ViewState.getEmptyPositions().get(to).getPosition().getY());
                    }else{
                            ViewState.getMarkers().get(ViewState.fetchMarker(from)).updateDrawable(
                                    (int) ViewState.getEmptyPositions().get(from).getPosition().getX(),
                                    (int) ViewState.getEmptyPositions().get(from).getPosition().getY());

                    }
                }catch(NullPointerException |ArrayIndexOutOfBoundsException e){
                        try {
                            ViewState.getMarkers().get(ViewState.fetchMarker(from)).updateDrawable(
                                    (int) ViewState.getEmptyPositions().get(from).getPosition().getX(),
                                    (int) ViewState.getEmptyPositions().get(from).getPosition().getY());
                        }catch(ArrayIndexOutOfBoundsException e1){
                            from=-1;
                        }
                        from=-1;
                        }
                }
        invalidate();
        return true;
    }

}
