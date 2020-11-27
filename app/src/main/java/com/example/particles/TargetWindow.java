package com.example.particles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


public class TargetWindow extends View {

    GameEngine gameEngine;

    Path path = new Path();
    Paint paint;

    public TargetWindow(Context context, GameEngine _gameEngine) {
        super(context);

        gameEngine = _gameEngine;

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        //setOnTouchListener(this);
    }

    public TargetWindow(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }


    public TargetWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TargetWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        gameEngine.DrawEntities(canvas);
        super.onDraw(canvas);
    }
}
