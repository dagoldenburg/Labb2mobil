package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;

import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dag on 11/30/2017.
 */

public class ViewState implements Serializable {

    private static ArrayList<PosDrawable> emptyPositions;
    private static ArrayList<PosDrawable> markers;
    private static Point textPos;
    private static Point winTextPos;

    public static ArrayList<PosDrawable> getEmptyPositions() {
        return emptyPositions;
    }

    public static void setEmptyPositions(ArrayList<PosDrawable> emptyPositions) {
        ViewState.emptyPositions = emptyPositions;
    }

    public static int fetchMarker(int move){
        for(int i=0;i<getMarkers().size();i++){
            if(markers.get(i).getPosition().getPosition()==move){
                return i;
            }
        }
        return -1;
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

    public static void onTilt(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int w = size.x;
        int h = size.y;
        winTextPos = new Point(0, h/2);
        int orentation = context.getResources().getConfiguration().orientation;
        if(orentation == context.getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            textPos = new Point(w/14* 9, h/7*2);
        }else if(orentation == context.getResources().getConfiguration().ORIENTATION_PORTRAIT){
            textPos = new Point(w/7*2, h/14*9);
        }
    }

    public static void initPositions(Context context,int gameSize){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int orentation = context.getResources().getConfiguration().orientation;
        emptyPositions = new ArrayList<>();
        Point size = new Point();
        display.getSize(size);
        int w = size.x;
        int h = size.y;
        winTextPos = new Point(0, h/2);
        int posWidth=0;
        int posHeight=0;
        int topMargin=0;
        int leftMargin=0;
        int[] xpos =new int[8];
        int[] ypos =new int[8];
        if(orentation == context.getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            posWidth= w/40;
            posHeight=h/20;
            topMargin= posHeight;//(h/8 -posHeight)/2;
            leftMargin= posWidth;//(w/14-posWidth)/2;
            for(int i=0;i<7;i++){
                xpos[i] = (w/14)*i+leftMargin;
                ypos[i] = (h/8)*i+topMargin;
            }
            textPos = new Point(w/14* 9, h/7*2);
        }else  if(orentation== context.getResources().getConfiguration().ORIENTATION_PORTRAIT){
            posWidth = w/20;
            posHeight = h/40;
            topMargin = posHeight;//(h/16- posHeight)/2;
            leftMargin  = posWidth;//(w/7-posWidth)/2;
            for(int i=0;i<7;i++){
                xpos[i] = (w/7)*i+leftMargin;
                ypos[i] = (h/16)*i+topMargin;
            }
            textPos = new Point(w/7*2, h/14*9);
        }

        int i = 0;
        if(gameSize>2) {
            emptyPositions.add(new PosDrawable(null, new Position(xpos[2], ypos[2], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[2], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[4], ypos[2], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[4], ypos[3], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[4], ypos[4], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[4], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[2], ypos[4], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[2], ypos[3], posWidth, posHeight, i++)));
        }
        if(gameSize>5) {
            emptyPositions.add(new PosDrawable(null, new Position(xpos[1], ypos[1], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[1], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[5], ypos[1], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[5], ypos[3], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[5], ypos[5], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[5], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[1], ypos[5], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[1], ypos[3], posWidth, posHeight, i++)));
        }
        if(gameSize>8) {
            emptyPositions.add(new PosDrawable(null, new Position(xpos[0], ypos[0], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[0], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[6], ypos[0], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[6], ypos[3], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[6], ypos[6], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[3], ypos[6], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[0], ypos[6], posWidth, posHeight, i++)));
            emptyPositions.add(new PosDrawable(null, new Position(xpos[0], ypos[3], posWidth, posHeight, i++)));
        }
        initPosDraw(context);
    }

    public static Point getTextPos() {
        return textPos;
    }

    public static void setTextPos(Point textPos) {
        ViewState.textPos = textPos;
    }


    public static ArrayList<PosDrawable> getMarkers() {
        return markers;
    }

    public static void setMarkers(ArrayList<PosDrawable> markers) {
        ViewState.markers = markers;
    }

    public static Point getWinTextPos() {
        return winTextPos;
    }

    public static void setWinTextPos(Point winTextPos) {
        ViewState.winTextPos = winTextPos;
    }


}
