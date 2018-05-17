package com.example.fbh.junqi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.ClickEvent.StartEvent;
import com.example.fbh.junqi.file.Util;

public class StartActivity extends Activity {
    public static  MapAll mapAll;
    public static Thread thread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        if(thread != null){
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(StartEvent.gameover){
//                        new AlertDialog.Builder(StartActivity.this)
//                                .setTitle("游戏结束")
//                                .setMessage("军旗被抗,游戏结束")
//                                .setPositiveButton("确定", null)
//                                .show();

                        Log.e("thread test","game end");
                        ChessBoard.saveToFile();
                        try {
                            Thread.sleep(1700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        StartActivity.this.finish();
                        break;
                    }
                    else{
                        Log.e("thread test","game not end");
                    }
                }
            }
        });
        thread.start();

        Button map_exit = (Button)findViewById(R.id.start_exit);
        map_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_exit");

       //         Intent map_view = new Intent(StartActivity.this, MainActivity.class);
        //        startActivity(map_view);
                StartActivity.this.finish();
            }
        });


        Button map_save = (Button)findViewById(R.id.start_regret);
        map_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_regret");

                ChessBoard.goBack();
            }
        });

        Button start_fail = (Button)findViewById(R.id.start_fail);
        start_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_fail");


                new AlertDialog.Builder(StartActivity.this)
                        .setTitle("游戏结束")
                        .setMessage("玩家投降,游戏结束")
                        .setPositiveButton("确定", null)
                        .show();

                ChessBoard.saveToFile();
//                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(map_view);

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //      Intent map_view = new Intent(MapActivity.this, MainActivity.class);
                        StartActivity.this.finish();
                        //       startActivity(map_view);
                    }
                }.start();
            }
        });

        Button start_map = (Button)findViewById(R.id.start_map);
        start_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_map");


                Intent map_view = new Intent(StartActivity.this, Choose.class);
                startActivity(map_view);
            }
        });






        String ch1[] = Util.file_read(getResources().openRawResource(R.raw.moren));
        String ch2[] = new String[30];

        Init(ch1,ch2);

    }

    public  void Init(String[] ch1,String[] ch2){
        AbsoluteLayout layout = (AbsoluteLayout)findViewById(R.id.edit_layout);

        for(int i=0;i<30;++i)
            ch2[i] = ch1[29-i];

        ChessBoard.Init(ch2,ch1);

        mapAll = new MapAll(layout,getBaseContext(),
                ch2,ch1);
        layout.invalidate();


    }

}
