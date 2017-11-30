package com.example.micke.labb2mobil.Model.GameObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Micke on 11/28/2017.
 */

public class Position implements Serializable {
    private float x;
    private float y;
    private float width;
    private float height;
    private int position;
    
    private static ArrayList<Position> pos;

    public static ArrayList<Position> getPos() {
        return pos;
    }

    public static void setPos(ArrayList<Position> pos) {
        Position.pos = pos;
    }

    public Position(float x, float y, float width, float height, int position) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.position = position;
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
