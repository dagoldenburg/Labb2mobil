package com.example.micke.labb2mobil.Activities.Controller;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.micke.labb2mobil.Activities.View.DrawableAnimation;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;

/**
 * Created by Dag on 11/30/2017.
 */

public class TouchController {


    /**
     * Sets a new object on screen
     * @param v
     * @param event
     */
    public static void set(View v,MotionEvent event){
        Position position = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions());
        try {
            if (GameState.getGameState().set(position.getPosition())) {
                Drawable d = null;
                if (!GameState.getGameState().isWhitePlayersTurn()) {
                    d = v.getResources().getDrawable(R.drawable.white_circle);
                } else {
                    d = v.getResources().getDrawable(R.drawable.black_circle);
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
                    v.invalidate();
                }
            }
        }catch(NullPointerException e){

        }
    }

    /**
     * Realises a move by the player
     * @param from
     * @param event
     * @param v
     */
    public static void move(Position from,MotionEvent event,View v){
        try{
            Position to = PosDrawable.seeIfTouch(event.getX(), event.getY(), ViewState.getViewState().getEmptyPositions());
            if(GameState.getGameState().move(from.getPosition(),to.getPosition())){
                ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from.getPosition())).updatePosDrawable(to);
            }else{
                returnAnimation(from.getPosition(),v);
            }
        }catch(NullPointerException |ArrayIndexOutOfBoundsException e){
            try {
                returnAnimation(from.getPosition(),v);
            }catch(IndexOutOfBoundsException|NullPointerException|ClassCastException e1){
                from=null;
            }
            from=null;
        }
    }


    /**
     * Return animation for when you make an illegal move
     * @param from
     * @param v
     */
    private static void returnAnimation(int from,View v){
        PosDrawable p = ViewState.getViewState().getMarkers().get(ViewState.getViewState().fetchMarker(from));

        ValueAnimator moveLeft = ValueAnimator.ofInt(p.getProxy().getBounds().left,(int) p.getPosition().getX() );
        ValueAnimator moveRight = ValueAnimator.ofInt(p.getProxy().getBounds().right,  (int)p.getPosition().getX()+(int)p.getPosition().getWidth() );
        ValueAnimator moveTop = ValueAnimator.ofInt(p.getProxy().getBounds().top, (int)p.getPosition().getY() );
        ValueAnimator moveBottom = ValueAnimator.ofInt(p.getProxy().getBounds().bottom, ((int)p.getPosition().getY()+(int)p.getPosition().getHeight()) );

        moveLeft.addUpdateListener(new DrawableAnimation(p,v,0));
        moveTop.addUpdateListener(new DrawableAnimation(p,v,1));
        moveRight.addUpdateListener(new DrawableAnimation(p,v,2));
        moveBottom.addUpdateListener(new DrawableAnimation(p,v,3));

        AnimatorSet as = new AnimatorSet();
        as.setDuration(1000);
        as.playTogether(moveLeft, moveRight,moveTop,moveBottom);
        as.start();
        Log.i("asd","from getting animated");
    }
}
