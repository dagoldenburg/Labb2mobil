package com.example.micke.labb2mobil.Model.GameObjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;

import com.example.micke.labb2mobil.R;

import java.util.ArrayList;

/**
 * Created by Dag on 11/29/2017.
 */

public class PosDrawable extends Drawable {

    private Drawable proxy;
    private Position position;

    public static Position seeIfTouch(float x, float y,ArrayList<PosDrawable> list){
        for(PosDrawable t: list){
            Position p = t.getPosition();
            if(((x >= p.getX()) && (x <= p.getX()+p.getWidth()))
                    && ((y>=p.getY()) && (y<=p.getY()+p.getHeight()))){
                return p;
            }
        }
        return null;
    }

    public PosDrawable(Drawable proxy, Position pos) {
        this.proxy = proxy;
        this.position = pos;
    }

   public void updateDrawable(int x, int y){
        position.setY(y);
        position.setX(x);
        Rect rect = new Rect((int) position.getX(), (int) position.getY(), (int) (position.getX() + position.getWidth()), (int) (position.getY() + position.getHeight()));
        proxy.setBounds(rect);
    }

    public Drawable getProxy() {
        return proxy;
    }

    public void setProxy(Drawable proxy) {
        this.proxy = proxy;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        proxy.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {
        proxy.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        proxy.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
