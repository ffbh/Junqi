package com.example.fbh.junqi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameMap extends View {
    public GameMap(Context context){
        this(context,null);
    }


    public GameMap(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        setMeasuredDimension(1080, 750);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int avg = height / 16;

        canvas.drawRect(0, 0, width, height,new Paint());
        for (int i = 1; i < 16; i++) {
            // 画竖线
            canvas.drawLine(avg * i, avg, avg * i, height - avg, new Paint());
            // 画横线
            canvas.drawLine(avg, avg * i, width - avg, avg * i, new Paint());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return true;
    }

}
