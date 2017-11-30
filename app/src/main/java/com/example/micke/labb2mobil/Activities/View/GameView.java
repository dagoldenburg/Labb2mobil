package com.example.micke.labb2mobil.Activities.View;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


import com.example.micke.labb2mobil.Activities.GameActivity;
import com.example.micke.labb2mobil.Activities.LevelChoiceActivity;
import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;
import com.example.micke.labb2mobil.Tasks.SaveGame;


public class GameView extends View {
    
    Paint paint = new Paint();
    Paint winPaint = new Paint();

    private  Drawable saveButton;
    private  Drawable newgameButton;
    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.CYAN);
        paint.setTextSize(50);
        winPaint.setTextSize(60);
        saveButton = getResources().getDrawable(R.drawable.save_game);
        newgameButton = getResources().getDrawable(R.drawable.new_game);

    }

    @Override
    public void onDraw(Canvas canvas) {
        newgameButton.setBounds(100, 600 ,300 ,650);
        saveButton.setBounds(400, 600 ,600 ,650);
        saveButton.draw(canvas);
        newgameButton.draw(canvas);
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

    Position from;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (PosDrawable.backtoLevelChoice(event.getX(),event.getY())){
                        ((Activity)getContext()).finish();
                    }
                    if(PosDrawable.saveGame(event.getX(),event.getY())){
                        SaveGame saveGame = new SaveGame(new SaveGame.TaskListener() {
                            @Override
                            public void onFinished(Boolean result) {
                                if(result ==true){
                                    ((Activity)getContext()).finish();
                                }
                            }
                        }

                    ,getContext(), GameState.getGameState().getGameName());
                        saveGame.execute();

                    }
                    if(GameState.getGameState().checkIfWin()!=0){
                        ((Activity)getContext()).finish();
                     }
                     try {
                         from = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers());
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
                        ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from.getPosition())).updateDrawable((int) event.getX(),(int) event.getY());
                        invalidate();
                    }catch(IndexOutOfBoundsException|NullPointerException e){
                    }
                }else{
                    try{
                    Position to = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions());
                    if(GameState.getGameState().move(from.getPosition(),to.getPosition())){
                        ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from.getPosition())).updatePosDrawable(to);
                    }else{
                        returnAnimation(from.getPosition());
                        Log.i("asd","drop on illegal");

                    }
                }catch(NullPointerException |ArrayIndexOutOfBoundsException e){
                        Log.i("asd","bad event");
                        try {
                            returnAnimation(from.getPosition());
                        }catch(IndexOutOfBoundsException|NullPointerException e1){
                            Log.i("asd","from nullibulli");
                            from=null;
                        }
                        Log.i("asd","from getting nulled ");
                        from=null;
                        }
                }
        invalidate();
        return true;
    }

    private void returnAnimation(int from){
        PosDrawable p = ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from));

        ObjectAnimator moveLeft = ObjectAnimator.ofFloat(p.getProxy().getBounds().left, "left", p.getPosition().getX() );
        ObjectAnimator moveRight = ObjectAnimator.ofFloat(p.getProxy().getBounds().right, "right", p.getPosition().getX()+p.getPosition().getWidth() );
        ObjectAnimator moveTop = ObjectAnimator.ofFloat(p.getProxy().getBounds().top, "top", p.getPosition().getY() );
        ObjectAnimator moveBottom = ObjectAnimator.ofFloat(p.getProxy().getBounds().bottom, "bottom", p.getPosition().getY()+p.getPosition().getHeight() );

        AnimatorSet as = new AnimatorSet();
        as.setDuration(10);
        as.playTogether(moveLeft, moveRight,moveTop,moveBottom);
        as.start();
        Log.i("asd","from getting animated");
    }


}
