package com.example.micke.labb2mobil.Activities.View;

import android.content.ClipData;
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
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.example.micke.labb2mobil.Model.EmptySpace;
import com.example.micke.labb2mobil.Model.GameObjects.Position;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;

import java.util.ArrayList;
import java.util.concurrent.DelayQueue;


public class GameView extends View {
    private ArrayList<Drawable> drawables;
    private ArrayList<Drawable> markers;

    public GameView(Context context) {
        super(context);
        drawables = new ArrayList<>();
        markers = new ArrayList<>();
        for (Position p : Position.getPositions()) {
            Drawable rectangle = getResources().getDrawable(R.drawable.rectangle);
            Rect rect = new Rect((int) p.getX(), (int) p.getY(), (int) (p.getX() + p.getWidth()), (int) (p.getY() + p.getHeight()));
            rectangle.setBounds(rect);
            drawables.add(rectangle);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Drawable d : drawables) {
            d.draw(canvas);
        }
        for (Drawable d : markers) {
            d.draw(canvas);
        }
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
        /*int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                break;
            case DragEvent.ACTION_DRAG_ENDED:
            default:
                break;
        }
        return true;*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Position position = Position.seeIfTouch(event.getX(),event.getY());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(this);
            startDrag(data, shadowBuilder, this, 0);
            //setVisibility(View.INVISIBLE);
        }
        if(position!=null) {
            if(GameState.getGameState().set(position.getPosition())) {
                Drawable d=null;
                if (GameState.getGameState().isWhitePlayersTurn()) {
                    d = getResources().getDrawable(R.drawable.white_circle);
                    Log.i("test","white");
                } else {
                    Log.i("test","black");
                    d = getResources().getDrawable(R.drawable.black_circle);
                }
                Rect rect = new Rect((int)position.getX(),(int)position.getY(),(int)(position.getX()+position.getWidth()),(int)(position.getY()+position.getHeight()));
                d.setBounds(rect);
                markers.add(d);
            }
            Log.i("hej","pos "+position);
            invalidate();
            return true;
        }else return false;
    }

}
