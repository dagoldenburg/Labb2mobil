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

    private  Drawable saveButton;
    private  Drawable newgameButton;

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
        saveButton = getResources().getDrawable(R.drawable.save_game);
        newgameButton = getResources().getDrawable(R.drawable.new_game);

        line.setStrokeWidth(5);
        line.setColor(Color.BLACK);
        fillLines();
    }

    @Override
    public void onDraw(Canvas canvas) {
        newgameButton.setBounds(100, 600 ,300 ,650);
        saveButton.setBounds(400, 600 ,600 ,650);
        saveButton.draw(canvas);
        newgameButton.draw(canvas);
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
            if(GameState.getGameState().checkIfWin()!=0){
                ((Activity)getContext()).finish();
            }
            if (PosDrawable.backtoLevelChoice(event.getX(),event.getY())){
                ((Activity)getContext()).finish();
            }
            if(PosDrawable.saveGame(event.getX(),event.getY())) {
                SaveGame saveGame = new SaveGame(new SaveGame.TaskListener() {
                    @Override
                    public void onFinished(Boolean result) {
                        if (result == true) {
                            ((Activity) getContext()).finish();
                        }
                    }
                }, getContext(), GameState.getGameState().getGameName());
                saveGame.execute();
            }
            try {
                from = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers());
            }catch(NullPointerException e){

            }
            Position position = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions());
            try {
                if (GameState.getGameState().set(position.getPosition())) {
                    Drawable d = null;
                    if (!GameState.getGameState().isWhitePlayersTurn()) {
                        d = getResources().getDrawable(R.drawable.white_circle);
                    } else {
                        d = getResources().getDrawable(R.drawable.black_circle);
                    }
                    Rect rect = new Rect((int) position.getX(), (int) position.getY(), (int) (position.getX() + position.getWidth()), (int) (position.getY() + position.getHeight()));
                    d.setBounds(rect);
                    PosDrawable pos = new PosDrawable(d, new Position((int) position.getX(), (int) position.getY(), (int) position.getWidth(),
                            (int) position.getHeight(), position.getPosition()));

                    ViewState.getViewState().getMarkers().add(pos);
                }else if(GameState.getGameState().areThreeOnRow()) {
                    Position pos = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getMarkers());
                    if (GameState.getGameState().remove(pos.getPosition())) {
                        ViewState.getViewState().getMarkers().remove(ViewState.getViewState().fetchMarker(pos.getPosition()));
                        invalidate();
                    }
                }
            }catch(NullPointerException e){

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
                }
            }catch(NullPointerException |ArrayIndexOutOfBoundsException e){
                try {
                    returnAnimation(from.getPosition());
                }catch(IndexOutOfBoundsException|NullPointerException|ClassCastException e1){
                    from=null;
                }
                from=null;
            }
        }
        invalidate();
        return true;
    }

        private void returnAnimation(int from){
            PosDrawable p = ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from));

            ValueAnimator moveLeft = ValueAnimator.ofInt(p.getProxy().getBounds().left,(int) p.getPosition().getX() );
            ValueAnimator moveRight = ValueAnimator.ofInt(p.getProxy().getBounds().right,  (int)p.getPosition().getX()+(int)p.getPosition().getWidth() );
            ValueAnimator moveTop = ValueAnimator.ofInt(p.getProxy().getBounds().top, (int)p.getPosition().getY() );
            ValueAnimator moveBottom = ValueAnimator.ofInt(p.getProxy().getBounds().bottom, ((int)p.getPosition().getY()+(int)p.getPosition().getHeight()) );

            moveLeft.addUpdateListener(new DrawableAnimation(p,this,0));
            moveTop.addUpdateListener(new DrawableAnimation(p,this,1));
            moveRight.addUpdateListener(new DrawableAnimation(p,this,2));
            moveBottom.addUpdateListener(new DrawableAnimation(p,this,3));

            AnimatorSet as = new AnimatorSet();
            as.setDuration(1000);
            as.playTogether(moveLeft, moveRight,moveTop,moveBottom);
            as.start();
            Log.i("asd","from getting animated");
        }


}
