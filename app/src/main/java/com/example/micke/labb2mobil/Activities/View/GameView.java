package com.example.micke.labb2mobil.Activities.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



import com.example.micke.labb2mobil.R;


public class GameView extends View {
    private Drawable drawable;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Resources resources = context.getResources();
        drawable = (Drawable)
                resources.getDrawable(R.drawable.loadgamedrawable);
    }

    @Override
    public void onDraw(Canvas canvas){
        int x = 100, y = 200;
        int iw = drawable.getIntrinsicWidth();
        int ih = drawable.getIntrinsicHeight();
        Rect bounds = new Rect(x, y, x+iw, y+ih);
        drawable.setBounds(bounds);
        drawable.draw(canvas);

    }
}
