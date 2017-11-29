package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.R;

import java.util.ArrayList;

/**
 * Created by Dag on 11/29/2017.
 */

public class PosDrawable extends Drawable {

    private Drawable proxy;
    private Position position;

    private static ArrayList<PosDrawable> emptyPositions;
    private static ArrayList<PosDrawable> markers;

    public static ArrayList<PosDrawable> getEmptyPositions() {
        return emptyPositions;
    }
    public static void setEmptyPositions(ArrayList<PosDrawable> emptyPositions) {
        PosDrawable.emptyPositions = emptyPositions;
    }

    public static Position seeIfTouch(float x, float y,ArrayList<PosDrawable> list){
        for(PosDrawable t: list){
            Position p = t.getPosition();
            Log.i("asda","vvvvvvvvvvvvvvvv "+p.getY());
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

    private static void initPosDraw(Context context){
        markers = new ArrayList<>();
        for (PosDrawable pd : emptyPositions) {
            Position p = pd.getPosition();
            Drawable rectangle = context.getResources().getDrawable(R.drawable.rectangle);
            Rect rect = new Rect((int) p.getX(), (int) p.getY(), (int) (p.getX() + p.getWidth()), (int) (p.getY() + p.getHeight()));
            rectangle.setBounds(rect);
            pd.setProxy(rectangle);
        }
    }

    public static void initPositions(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        emptyPositions = new ArrayList<>();
        Point size = new Point();
        display.getSize(size);
        int w = size.x;
        int h = size.y;

        int[] xpos = new int[8];
        int[] ypos = new int[8];
        for(int i=0;i<8;i++){
            xpos[i] = (w/7)*i;
            ypos[i] = (h/16)*i;
            Log.i("hej",xpos[i]+" "+ypos[i]);
        }
        int posWidth = w/20;
        int posHeight = h/40;

        int i = 0;
        emptyPositions.add(new PosDrawable(null,new Position(xpos[2],ypos[2],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[2],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[4],ypos[2],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[4],ypos[3],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[4],ypos[4],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[4],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[2],ypos[4],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[2],ypos[3],posWidth,posHeight,i++)));

        emptyPositions.add(new PosDrawable(null,new Position(xpos[1],ypos[1],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[1],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[5],ypos[1],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[5],ypos[3],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[5],ypos[5],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[5],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[1],ypos[5],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[1],ypos[3],posWidth,posHeight,i++)));

        emptyPositions.add(new PosDrawable(null,new Position(xpos[0],ypos[0],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[0],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[6],ypos[0],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[6],ypos[3],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[6],ypos[6],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[3],ypos[6],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[0],ypos[6],posWidth,posHeight,i++)));
        emptyPositions.add(new PosDrawable(null,new Position(xpos[0],ypos[3],posWidth,posHeight,i++)));
        initPosDraw(context);
    }

    public static ArrayList<PosDrawable> getMarkers() {
        return markers;
    }

    public static void setMarkers(ArrayList<PosDrawable> markers) {
        PosDrawable.markers = markers;
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
