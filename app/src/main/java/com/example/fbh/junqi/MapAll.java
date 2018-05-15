package com.example.fbh.junqi;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import com.example.fbh.junqi.Board.Chess;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.ClickEvent.StartEvent;
import com.example.fbh.junqi.ClickEvent.mapEvent;

public class MapAll {
    private Button[][] chessUp = new Button[6][5];
    private Button[] chessM = new Button[3];
    private Button[][] chessDown = new Button[6][5];
    private Context context = null;
    AbsoluteLayout father = null;
    public MapAll(AbsoluteLayout father, Context context, String[] ch1,String[] ch2, int color) {

        this.father = father;
        this.context = context;
        Init(ch1,ch2,color);
    }


    public void setChess(Pair<Integer,Integer> p , Chess chess){
        Log.e("set",p.toString()+" "+chess.toString());
        int alp = 255;
        if(chess.getName().length() == 0){
            alp = 0;
        }
        if(p.first<6){
            chessUp[p.first][p.second].setText(chess.getName());
            chessUp[p.first][p.second].setBackgroundColor(chess.getColor());
            chessUp[p.first][p.second].getBackground().setAlpha(alp);
            chessUp[p.first][p.second].invalidate();
        }
        else if(p.first ==6){
            chessM[p.second].setText(chess.getName());
            chessM[p.second].setBackgroundColor(chess.getColor());
            chessM[p.second].getBackground().setAlpha(alp);
            chessM[p.second].invalidate();
        }
        else{
            chessDown[p.first-7][p.second].setText(chess.getName());
            chessDown[p.first-7][p.second].setBackgroundColor(chess.getColor());
            chessDown[p.first-7][p.second].getBackground().setAlpha(alp);
            chessDown[p.first-7][p.second].invalidate();
        }
        father.invalidate();
    }


    void Init(String[] ch1,String[] ch2,int color){

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


        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j){
                int sx = start_x + v_p * j;
                int sy = start_y + h_p * i + adjust[i];
             //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
                chessUp[i][j] = new Button(context);
                chessUp[i][j].setText(ch1[i*5+j]);
                chessUp[i][j].setBackgroundColor(ChessBoard.m2);
                chessUp[i][j].getPaint().setFakeBoldText(true);
        //        chess[i][j].setBackgroundColor(0Xffff00);
        //        Log.e("sx",sx+"");
         //       Log.e("sy",sy+"");
                chessUp[i][j].setX(sx);
                chessUp[i][j].setY(sy);
              //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
                if(i==2&&j==1||i==2&&j==3||i==4&&j==1||i==4&&j==3||i==3&&j==2) {
                    chessUp[i][j].getBackground().setAlpha(0);
                    chessUp[i][j].setText("");
                }
                chessUp[i][j].setOnClickListener(new StartEvent());
                chessUp[i][j].setPadding(0,0,0,0);
            //    Log.e("sizew",size_w+"");
                chessUp[i][j].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
                father.addView(chessUp[i][j]);
                chessUp[i][j].setTag(new Pair(i,j));
           //     Log.e("V:",chess[i][j].getVisibility()+"");
                chessUp[i][j].invalidate();
            }


        for(int i=0;i<3;++i){
            int sx = start_x + v_p * i * 2;
            int sy = 800;
            //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
            chessM[i] = new Button(context);
            chessM[i].getPaint().setFakeBoldText(true);
            chessM[i].setPadding(0,0,0,0);
            //        chess[i][j].setBackgroundColor(0Xffff00);
            //        Log.e("sx",sx+"");
            //       Log.e("sy",sy+"");
            chessM[i].setX(sx);
            chessM[i].setY(sy);
            chessM[i].setOnClickListener(new StartEvent());
            //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
         //   chessM[i].setVisibility(View.INVISIBLE);
            chessM[i].getBackground().setAlpha(0);
            //    Log.e("sizew",size_w+"");
            chessM[i].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
            father.addView(chessM[i]);
            //     Log.e("V:",chess[i][j].getVisibility()+"");
            chessM[i].setTag(new Pair(6,i*2));
            chessM[i].invalidate();


        }




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


                int sx = start_x + v_p * j;
                int sy = start_y + h_p * i + adjust[i];
                //   chess[i][j] = new ChessView(context,new Chess(ch[i*5+j],color));
                chessDown[i][j] = new Button(context);
                chessDown[i][j].setText(ch2[i*5+j]);
                chessDown[i][j].getPaint().setFakeBoldText(true);
                //        chessDown[i][j].setTextColor(0Xff0000);
                //        chess[i][j].setBackgroundColor(0Xffff00);
                //        Log.e("sx",sx+"");
                //       Log.e("sy",sy+"");
                chessDown[i][j].setX(sx);
                chessDown[i][j].setY(sy);
                chessDown[i][j].setBackgroundColor(ChessBoard.m1);
                //  chess[i][j].setPadding(sx, sy, size_w + sx,size_h+sy);
                if(i==1&&j==1||i==1&&j==3||i==3&&j==1||i==3&&j==3||i==2&&j==2) {
                    chessDown[i][j].getBackground().setAlpha(0);
                    chessDown[i][j].setText("");
                }
                chessDown[i][j].setPadding(0,0,0,0);
                //    Log.e("sizew",size_w+"");
                chessDown[i][j].setLayoutParams(new AbsListView.LayoutParams(size_w,size_h));
                chessDown[i][j].setOnClickListener(new StartEvent());
                chessDown[i][j].setTag(new Pair(7+i,j));
                father.addView(chessDown[i][j]);

                //     Log.e("V:",chess[i][j].getVisibility()+"");
                chessDown[i][j].invalidate();
            }
    }


}
