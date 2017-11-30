package com.example.micke.labb2mobil.Activities.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;

/**
 * Created by Dag on 11/30/2017.
 */

public class DrawableAnimation implements ValueAnimator.AnimatorUpdateListener {
    private PosDrawable p;
    private View view;
    private int who;

    public DrawableAnimation(PosDrawable p, View v, int who) {
        this.p = p;
        this.view = v;
        this.who = who;
    }

    /**
     * Animates a drawable
     * @param animation
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int left = p.getProxy().getBounds().left;
        int right = p.getProxy().getBounds().right;
        int top = p.getProxy().getBounds().top;
        int bottom = p.getProxy().getBounds().bottom;

        if(who==0)
            p.getProxy().setBounds(new Rect((int) animation.getAnimatedValue(),top,right,bottom));
        if(who==1)
            p.getProxy().setBounds(new Rect(left,(int) animation.getAnimatedValue(),right,bottom));
        if(who==2)
            p.getProxy().setBounds(new Rect(left,top,(int) animation.getAnimatedValue(),bottom));
        if(who==3)
            p.getProxy().setBounds(new Rect(left, top, right, (int) animation.getAnimatedValue()));
        view.invalidate();
    }
}
