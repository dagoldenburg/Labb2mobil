package com.example.micke.labb2mobil.Model.GameObjects;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by Micke on 11/28/2017.
 */

public class Position {
    private float x;
    private float y;
    private float width;
    private float height;
    private int position;

    private  static Point textPos;
    private static ArrayList<Position> emptyPositions;
    private static ArrayList<Position> markerPositions;

    public Position(float x, float y,float width,float height, int position) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public static ArrayList<Position> getEmptyPositions() {
        return emptyPositions;
    }

    public static void setEmptyPositions(ArrayList<Position> emptyPositions) {
        Position.emptyPositions = emptyPositions;
    }

    public static Position seeIfTouchEmpty(float x, float y){
        for(Position p: emptyPositions){
            if(((x >= p.getX()) && (x <= p.getX()+p.getWidth()))
                    && ((y>=p.getY()) && (y<=p.getY()+p.getHeight()))){
                return p;
            }
        }
        return null;
    }

    public static Position seeIfTouchMarker(float x, float y){
        for(Position p: emptyPositions){
            if(((x >= p.getX()) && (x <= p.getX()+p.getWidth()))
                    && ((y>=p.getY()) && (y<=p.getY()+p.getHeight()))){
                return p;
            }
        }
        return null;
    }

    public static Point getTextPos() {
        return textPos;
    }

    public static void setTextPos(Point textPos) {
        Position.textPos = textPos;
    }

    public static void initemptyPositions(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int w =0;
        int h=0;
       int orientation= context.getResources().getConfiguration().orientation;
        Point size = new Point();
        display.getSize(size);
        textPos = new Point();

        emptyPositions = new ArrayList<>();
        int topMargin=0;
        int leftMargin=0;
        int posHeight=0;
        int posWidth=0;
        int[] xpos = new int[7];
        int[] ypos = new int[7];

        w=size.x;
        h= size.y;

        if(orientation==context.getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            size.y = size.y-100;
           posWidth=w/40;
           posHeight=h/20;

            textPos.set(w/16*8, h/7*2);
            topMargin =  (h/7- posHeight)/2;
            leftMargin  = (w/16-posWidth)/2;
            for(int i=0;i<7;i++){
                xpos[i] = (w/16)*i+leftMargin;
                ypos[i] = (h/7)*i+topMargin;
                Log.i("hej",xpos[i]+" "+ypos[i]);
            }

        }else if(orientation==context.getResources().getConfiguration().ORIENTATION_PORTRAIT) {


            textPos.set(w/7*2,h/16*8);
            posWidth = w/20;
            posHeight = h/40;
            topMargin =  (w/7- posHeight)/2;
            leftMargin  = (h/16-posWidth)/2;
            for(int i=0;i<7;i++){
                xpos[i] = (w/7)*i+topMargin;
                ypos[i] = (h/16)*i+leftMargin;
                Log.i("hej",xpos[i]+" "+ypos[i]);
            }
        }












        int i = 0;
        emptyPositions.add(new Position(xpos[2],ypos[2],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[2],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[4],ypos[2],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[4],ypos[3],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[4],ypos[4],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[4],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[2],ypos[4],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[2],ypos[3],posWidth,posHeight,i++));

        emptyPositions.add(new Position(xpos[1],ypos[1],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[1],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[5],ypos[1],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[5],ypos[3],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[5],ypos[5],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[5],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[1],ypos[5],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[1],ypos[3],posWidth,posHeight,i++));

        emptyPositions.add(new Position(xpos[0],ypos[0],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[0],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[6],ypos[0],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[6],ypos[3],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[6],ypos[6],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[3],ypos[6],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[0],ypos[6],posWidth,posHeight,i++));
        emptyPositions.add(new Position(xpos[0],ypos[3],posWidth,posHeight,i++));

    }

    public static int fetchPosition(float x,float y){
        for(Position p : emptyPositions){
            if((p.getX()==x) && (p.getY()==y)){
                return p.getPosition();
            }
        }
        return -1;
    }

    public static ArrayList<Position> getMarkerPositions() {
        return markerPositions;
    }

    public static void setMarkerPositions(ArrayList<Position> markerPositions) {
        Position.markerPositions = markerPositions;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
