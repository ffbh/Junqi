package com.example.fbh.junqi.ClickEvent;

import android.graphics.Color;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import com.example.fbh.junqi.Board.Chess;
import com.example.fbh.junqi.Board.ChessBoard;

public class StartEvent implements View.OnClickListener {
    private static Button but = null;

    @Override
    public void onClick(View v) {
        Log.e("click","start move");
        Log.e("postition",v.getTag().toString());
        if(but != null){
            Pair<Boolean,Integer> ans = ChessBoard.move((Pair)v.getTag());
            Log.e("move_message",ans.toString());
            if(ans.first) {
                String a = but.getText().toString();
                String b = ((Button) v).getText().toString();

                if(ans.second == 1){
                    ((Button) v).setText(a);
                    but.setText("");
                    v.getBackground().setAlpha(255);
                    but.getBackground().setAlpha(0);
                    if (ChessBoard.getFlag())
                        ((Button) v).setBackgroundColor(ChessBoard.m1);
                    else
                        ((Button) v).setBackgroundColor(ChessBoard.m2);
                }
                else if(ans.second == 0){
                    ((Button) v).setText("");
                    but.setText("");

                    v.getBackground().setAlpha(0);
                    but.getBackground().setAlpha(0);
                }
                else{
                    but.setText("");
                    but.getBackground().setAlpha(0);
                }



                but.invalidate();
                v.invalidate();
                but = null;
                Log.e("move", a + "<-->" + b);
            }
            else{
                if (!ChessBoard.getFlag())
                    but.setBackgroundColor(ChessBoard.m1);
                else
                    but.setBackgroundColor(ChessBoard.m2);
                but = null;

            }
        }
        else{
            if(ChessBoard.click((Pair)v.getTag())) {
                but = (Button) v;
                but.setBackgroundColor(Color.RED);
            }
            else{
                Log.e("click","out_range");
            }
        }
    }
}



