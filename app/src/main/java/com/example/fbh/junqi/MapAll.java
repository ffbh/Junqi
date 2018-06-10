package com.example.fbh.junqi;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import com.example.fbh.junqi.Board.Chess;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.ClickEvent.StartEvent;
import com.example.fbh.junqi.ClickEvent.mapEvent;

public class MapAll {
    private Button[][] chessUp = new Button[6][5];
    private Button[] chessM = new Button[3];
    private Button[][] chessDown = new Button[6][5];
    private TextView text;
    private Context context = null;
    AbsoluteLayout father = null;
    public MapAll(AbsoluteLayout father, Context context, String[] ch1,String[] ch2) {

        this.father = father;
        this.context = context;
        Init(ch1,ch2);
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


    void Init(String[] ch1,String[] ch2){

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

        text  = new TextView(context);
        text.setText("军旗被抗,游戏结束");
        text.setX(start_x);
        text.setY(770);
        text.setLayoutParams(new AbsListView.LayoutParams(size_w * 5,size_h * 2));
        text.getPaint().setFakeBoldText(true);
        text.setPadding(0,0,0,0);
        father.addView(text);
      //  text.setBackgroundColor(Color.WHITE);
        text.setTextColor(Color.RED);
        text.setTextSize(40);
        text.setVisibility(View.INVISIBLE);
        text.invalidate();
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


    void setMapStart(String[] ch1,String[] ch2){
        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j){
                chessUp[i][j].setText(ch1[i*5+j]);
                chessUp[i][j].invalidate();
                chessDown[i][j].setText(ch2[i*5+j]);
                chessDown[i][j].invalidate();
            }
    }


    public boolean check(){
        int num = 0;
        if(chessUp[0][1].getText().toString().equals("军旗")){
            num++;
        }
        if(chessUp[0][3].getText().toString().equals("军旗")){
            num++;
        }
        if(chessDown[5][1].getText().toString().equals("军旗")){
            num++;
        }
        if(chessDown[5][3].getText().toString().equals("军旗")){
            num++;
        }
        if(num != 2){
            text.setVisibility(View.VISIBLE);
        }
        return num == 2;
    }

    private Button getChess(int x,int y){
        if(x<6){
           return chessUp[x][y];
        }
        else if(x ==6){
            return chessM[y];
        }
        else{
           return chessDown[x-7][y];
        }
    }

    private Handler handler=new Handler();
    private static String a,b;
    private static Button A,B;
  public void moveTo(int sx,int sy,int ex,int ey){
        A = getChess(sx,sy);
        B = getChess(ex,ey);
        ChessBoard.click(new Pair(sx,sy));
        Pair<Boolean,Integer> ans = ChessBoard.move(new Pair(ex,ey));
        a = A.getText().toString();
        b = B.getText().toString();

      if(ans.second == 1){
          handler.post(new Runnable() {
          @Override
          public void run() {
              B.setText(a);
              A.setText("");
              B.getBackground().setAlpha(255);
              A.getBackground().setAlpha(0);
              if (ChessBoard.getFlag())
                  B.setBackgroundColor(ChessBoard.m2);
              else
                  B.setBackgroundColor(ChessBoard.m1);
          }
      });

      }
      else if(ans.second == 0){
          handler.post(new Runnable() {
              @Override
              public void run() {
                  B.setText("");
                  A.setText("");

                  B.getBackground().setAlpha(0);
                  A.getBackground().setAlpha(0);
              }
          });

      }
      else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    B.setText("");
                    B.getBackground().setAlpha(0);
                }
            });

      }
  }


}
