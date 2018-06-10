package com.example.fbh.junqi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.example.fbh.junqi.Board.Chess;

public class ChessView extends View {
    private Chess chess = null;

    public ChessView(Context context,Chess chess) {
        this(context,null,chess);
    }

    public ChessView(Context context, AttributeSet attrs,Chess chess) {
        super(context, attrs);
        this.chess = chess;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        Paint bgPaint = new Paint();
        bgPaint.setColor(chess.getColor());
        canvas.drawRect(0, 0, width, height, bgPaint);
        canvas.drawText(chess.getName(),getX(),getY(),new Paint());

    }




}

