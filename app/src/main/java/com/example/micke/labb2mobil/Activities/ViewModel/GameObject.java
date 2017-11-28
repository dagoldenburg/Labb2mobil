package com.example.micke.labb2mobil.Activities.ViewModel;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Micke on 11/28/2017.
 */

public class GameObject {
    private ImageView img;
    private int position;

    public GameObject(ImageView img, int position) {
        this.img = img;
        this.position = position;
    }

    public static int getPositionByViewId(int id,ArrayList al){
        for(GameObject go:(ArrayList<GameObject>) al){
            if(go.getImg().getId()==id){
                return go.position;
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

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
