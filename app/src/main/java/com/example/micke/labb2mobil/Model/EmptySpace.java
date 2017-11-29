package com.example.micke.labb2mobil.Model;

import android.app.Activity;
import android.widget.ImageView;

import com.example.micke.labb2mobil.R;

import java.util.ArrayList;

/**
 * Created by Micke on 11/28/2017.
 */

public class EmptySpace {
 /**  private static int[] emptyMarkerIds = {
            R.id.eMarker1,R.id.eMarker1,
            R.id.eMarker2,R.id.eMarker3,
            R.id.eMarker4,R.id.eMarker5,
            R.id.eMarker6,R.id.eMarker7,
            R.id.eMarker8,R.id.eMarker9,
            R.id.eMarker10,R.id.eMarker11,
            R.id.eMarker12,R.id.eMarker13,
            R.id.eMarker14,R.id.eMarker15,
            R.id.eMarker16,R.id.eMarker17,
            R.id.eMarker18,R.id.eMarker19,
            R.id.eMarker20,R.id.eMarker21,
            R.id.eMarker22,R.id.eMarker23,
    };**/
    private static ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
    private ImageView img;
    private int position;

    public EmptySpace(ImageView img, int position) {
        this.img = img;
        this.position = position;
    }

    public static void createEmptySpaces(Activity act){
        for(int i=0;i<GameState.getGameState().getGameBoard().length;i++){
           // EmptySpace.getEmptySpaces().add(new EmptySpace((ImageView) act.findViewById(emptyMarkerIds[i]),i));
        }
    }

    public static int getPositionByViewId(int id){
        for(EmptySpace es:emptySpaces){
            if(es.getImg().getId()==id){
                return es.position;
            }
        }
        return -1;
    }

    public static ArrayList<EmptySpace> getEmptySpaces() {
        return emptySpaces;
    }

    public static void setEmptySpaces(ArrayList<EmptySpace> emptySpaces) {
        EmptySpace.emptySpaces = emptySpaces;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
