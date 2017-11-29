package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.R;

import java.util.ArrayList;
import java.util.concurrent.DelayQueue;


public class GameView extends View {
    private ArrayList<Drawable> drawables;
    private Drawable drawable;
    private ImageView eMarker0;
    private ImageView circle;
    private  Paint paint;
    public GameView(Context context) {
        super(context);
        drawables = new ArrayList<>();


        for(Position p: Position.getPositions()){
            Drawable rectangle = getResources().getDrawable(R.drawable.rectangle);

            Rect rect = new Rect((int)p.getX(),(int)p.getY(),(int)(p.getX()+p.getWidth()),(int)(p.getY()+p.getHeight()));

            rectangle.setBounds(rect);
            drawables.add(rectangle);

            Log.i("xposypos","left "+rect.left+" right "+rect.right+" bottom "+rect.bottom+" top "+rect.top);
        }


    }

    @Override
    public void onDraw(Canvas canvas){

        for(Drawable d: drawables){

            d.draw(canvas);
        }


    }

}
