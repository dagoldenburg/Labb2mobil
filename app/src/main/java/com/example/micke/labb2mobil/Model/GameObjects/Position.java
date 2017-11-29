package com.example.micke.labb2mobil.Model.GameObjects;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.micke.labb2mobil.Model.GameState;

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

    private static ArrayList<Position> positions;

    public Position(float x, float y, int position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public static void initPositions(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int w = size.x;
        int h = size.y;

        //iamnrleamgieoargkeaerg
        int[] xpos = new int[10];
        int[] ypos = new int[10];
        for(int i=0;i<9;i++){
            xpos[i] = (w/8)*i;
            ypos[i] = (h/8)*i;
        }


        int i = 0;
        positions.add(new Position(ypos[2],ypos[2],i++));
        positions.add(new Position(ypos[2],ypos[3],i++));
        positions.add(new Position(ypos[2],ypos[4],i++));
        positions.add(new Position(ypos[3],ypos[4],i++));
        positions.add(new Position(ypos[4],ypos[4],i++));
        positions.add(new Position(ypos[4],ypos[3],i++));
        positions.add(new Position(ypos[4],ypos[2],i++));
        positions.add(new Position(ypos[3],ypos[3],i++));

        positions.add(new Position(ypos[1],ypos[1],i++));
        positions.add(new Position(ypos[1],ypos[3],i++));
        positions.add(new Position(ypos[1],ypos[5],i++));
        positions.add(new Position(ypos[3],ypos[5],i++));
        positions.add(new Position(ypos[5],ypos[5],i++));
        positions.add(new Position(ypos[5],ypos[3],i++));
        positions.add(new Position(ypos[5],ypos[1],i++));
        positions.add(new Position(ypos[3],ypos[1],i++));

        positions.add(new Position(ypos[0],ypos[0],i++));
        positions.add(new Position(ypos[0],ypos[3],i++));
        positions.add(new Position(ypos[0],ypos[7],i++));
        positions.add(new Position(ypos[3],ypos[7],i++));
        positions.add(new Position(ypos[7],ypos[7],i++));
        positions.add(new Position(ypos[7],ypos[3],i++));
        positions.add(new Position(ypos[7],ypos[0],i++));
        positions.add(new Position(ypos[3],ypos[0],i++));
    }

    public static int fetchPosition(float x,float y){
        for(Position p : positions){
            if((p.getX()==x) && (p.getY()==y)){
                return p.getPosition();
            }
        }
        return -1;
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
}
