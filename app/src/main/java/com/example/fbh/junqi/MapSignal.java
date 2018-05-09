package com.example.fbh.junqi;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.fbh.junqi.view.Chess;
import com.example.fbh.junqi.view.ChessView;

public class MapSignal {

    private Button[][] chess = new Button[6][5];
    private Context context = null;
    AbsoluteLayout father = null;
    public MapSignal(AbsoluteLayout father, Context context, String[] ch, int color) {

        this.father = father;
        this.context = context;
        Init(ch,color);
    }




    void Init(String[] ch,int color){
        int start_x = 35;
        int start_y = 35;
        int v_p = 200;
        int h_p = 300;
        int[] adjust = new int[6];
        adjust[0] = 10;
        adjust[1] = -2;
        adjust[2] = -20;
        adjust[3] = -42;
        adjust[4] = -67;
        adjust[5] = -87;



        int size_w = 200;
        int size_h = 155;
        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j){


                int sx = start_x + v_p * j;
                int sy = start_y + h_p * i + adjust[i];
             //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
                chess[i][j] = new Button(context);
                chess[i][j].setText(ch[0]);
                chess[i][j].setTextColor(0Xff0000);
                Log.e("sx",sx+"");
                Log.e("sy",sy+"");
                chess[i][j].setX(sx);
                chess[i][j].setY(sy);
              //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
                chess[i][j].setVisibility(View.VISIBLE);
            //    Log.e("sizew",size_w+"");
                chess[i][j].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
                father.addView(chess[i][j]);
           //     Log.e("V:",chess[i][j].getVisibility()+"");
                chess[i][j].invalidate();
            }


    }


}
