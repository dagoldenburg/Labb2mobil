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
import android.view.View;
import android.widget.ImageView;


import com.example.micke.labb2mobil.R;

import java.util.concurrent.DelayQueue;


public class GameView extends View {
    private Drawable drawable;
    private ImageView rectangle;
    private ImageView circle;
    private  Paint paint;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Resources resources = context.getResources();
        rectangle = (ImageView) findViewById(R.id.rectangle);
        circle = findViewById(R.id.circle);

        drawable =rectangle.getDrawable();


    }

    @Override
    public void onDraw(Canvas canvas){
        Rect bound = new Rect(10,10,40,40);
        drawable.setBounds(bound);
         drawable.draw(canvas);
    }

}
