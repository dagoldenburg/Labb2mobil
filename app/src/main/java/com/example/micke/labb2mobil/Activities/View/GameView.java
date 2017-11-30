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
        for (Drawable d : ViewState.getViewState().getEmptyPositions()) {
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
            canvas.drawText("Number of marker :" +GameState.getGameState().getWhiteMarker()   , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y+ 40,paint);
        }else {
            canvas.drawText("Turn : player black"  , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y,paint);
            canvas.drawText("Number of marker :" +GameState.getGameState().getBlackMarker()   , ViewState.getViewState().getTextPos().x,ViewState.getViewState().getTextPos().y+ 40,paint);
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
                         from = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers()).getPosition();
                     }catch(NullPointerException e){

                    }
                    Position position = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions());
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

                             ViewState.getViewState().getMarkers().add(pos);
                         }else if(GameState.getGameState().areThreeOnRow()) {
                             Log.i("asd","remove");
                             Position pos = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers());
                             if (GameState.getGameState().remove(pos.getPosition())) {
                                 ViewState.getViewState().getMarkers().remove(ViewState.getViewState().fetchMarker(pos.getPosition()));
                                 invalidate();
                             }
                         }
                     }catch(NullPointerException e){
                         Log.i("asd","asdassad");
                     }
                }else if (event.getAction() == MotionEvent.ACTION_MOVE && (GameState.getGameState().getBlackMarker()==0 || GameState.getGameState().getWhiteMarker()==0)) {
                   try {
                        ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from)).updateDrawable((int) event.getX(),(int) event.getY());
                        invalidate();
                    }catch(IndexOutOfBoundsException e){
                    }
                }else{
                    try{
                    int to = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions()).getPosition();

                    if(GameState.getGameState().move(from,to)){
                        ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from)).updateDrawable(
                                (int) ViewState.getViewState().getEmptyPositions().get(to).getPosition().getX(),
                                (int) ViewState.getViewState().getEmptyPositions().get(to).getPosition().getY());
                    }else{
                            ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from)).updateDrawable(
                                    (int) ViewState.getViewState().getEmptyPositions().get(from).getPosition().getX(),
                                    (int) ViewState.getViewState().getEmptyPositions().get(from).getPosition().getY());

                    }
                }catch(NullPointerException |ArrayIndexOutOfBoundsException e){
                        try {
                            ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from)).updateDrawable(
                                    (int) ViewState.getViewState().getEmptyPositions().get(from).getPosition().getX(),
                                    (int) ViewState.getViewState().getEmptyPositions().get(from).getPosition().getY());
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
