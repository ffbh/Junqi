package com.example.fbh.junqi;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.fbh.junqi.ClickEvent.mapEvent;
import com.example.fbh.junqi.view.Chess;
import com.example.fbh.junqi.view.ChessView;

public class MapSignal {

    private Button[][] chessUp = new Button[6][5];
    private Button[] chessM = new Button[3];
    private Button[][] chessDown = new Button[6][5];
    private Context context = null;
    AbsoluteLayout father = null;
    public MapSignal(AbsoluteLayout father, Context context, String[] ch, int color) {

        this.father = father;
        this.context = context;
        Init(ch,color);
    }




    void Init(String[] ch,int color){

        int size_w = 200;
        int size_h = 100;
        int start_x = 40;
        int start_y = 0;
        int v_p = 210;
        int h_p = 120;
        int[] adjust = new int[6];
        adjust[0] = 0;
        adjust[1] = 0;
        adjust[2] = 0;
        adjust[3] = 10;
        adjust[4] = 20;
        adjust[5] = 30;


   /*     for(int i=0;i<6;++i)
            for(int j=0;j<5;++j){
                int sx = start_x + v_p * j;
                int sy = start_y + h_p * i + adjust[i];
             //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
                chessUp[i][j] = new Button(context);
                chessUp[i][j].setText(ch[0]);
                chessUp[i][j].setTextColor(0Xff0000);
        //        chess[i][j].setBackgroundColor(0Xffff00);
        //        Log.e("sx",sx+"");
         //       Log.e("sy",sy+"");
                chessUp[i][j].setX(sx);
                chessUp[i][j].setY(sy);
              //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
                chessUp[i][j].setVisibility(View.VISIBLE);
            //    Log.e("sizew",size_w+"");
                chessUp[i][j].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
                father.addView(chessUp[i][j]);
           //     Log.e("V:",chess[i][j].getVisibility()+"");
                chessUp[i][j].invalidate();
            }


        for(int i=0;i<3;++i){
            int sx = start_x + v_p * i * 2;
            int sy = 800;
            //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
            chessM[i] = new Button(context);
            chessM[i].setText(ch[0]);
            chessM[i].setTextColor(0Xff0000);
            //        chess[i][j].setBackgroundColor(0Xffff00);
            //        Log.e("sx",sx+"");
            //       Log.e("sy",sy+"");
            chessM[i].setX(sx);
            chessM[i].setY(sy);
            //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
            chessM[i].setVisibility(View.VISIBLE);
            //    Log.e("sizew",size_w+"");
            chessM[i].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
            father.addView(chessM[i]);
            //     Log.e("V:",chess[i][j].getVisibility()+"");
            chessM[i].invalidate();


        }

*/


        start_x = 30;
        start_y = 970;

        adjust[0] = 0;
        adjust[1] = 10;
        adjust[2] = 30;
        adjust[3] = 20;
        adjust[4] = 20;
        adjust[5] = 30;

        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j){
                if(i==1&&j==1||i==1&&j==3||i==3&&j==1||i==3&&j==3||i==2&&j==2)
                    continue;

                int sx = start_x + v_p * j;
                int sy = start_y + h_p * i + adjust[i];
                //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
                chessDown[i][j] = new Button(context);
                chessDown[i][j].setText(ch[i*5+j]);
                chessDown[i][j].getPaint().setFakeBoldText(true);
        //        chessDown[i][j].setTextColor(0Xff0000);
                //        chess[i][j].setBackgroundColor(0Xffff00);
                //        Log.e("sx",sx+"");
                //       Log.e("sy",sy+"");
                chessDown[i][j].setX(sx);
                chessDown[i][j].setY(sy);
                chessDown[i][j].setBackgroundColor(mapEvent.mm);
                //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
                chessDown[i][j].setVisibility(View.VISIBLE);
                chessDown[i][j].setPadding(0,0,0,0);
                //    Log.e("sizew",size_w+"");
                chessDown[i][j].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
                chessDown[i][j].setOnClickListener(new mapEvent());

                father.addView(chessDown[i][j]);

                //     Log.e("V:",chess[i][j].getVisibility()+"");
                chessDown[i][j].invalidate();
            }
    }



}
