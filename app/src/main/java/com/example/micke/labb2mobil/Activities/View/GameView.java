package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.micke.labb2mobil.Activities.ViewModel.GameObject;

import java.util.ArrayList;


public class GameView extends View {

    ArrayList<GameObject> objects;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas){

    }

}
