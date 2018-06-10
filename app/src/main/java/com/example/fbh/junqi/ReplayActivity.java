package com.example.fbh.junqi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.example.fbh.junqi.Board.Chess;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.file.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class ReplayActivity extends Activity {
    public static  MapAll mapAll;
    private static int ID = 0;
    Thread thread = null;
    private boolean start = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay_activity);

        Button replay_exit = (Button)findViewById(R.id.replay_exit);
        replay_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","replay_exit");

                ReplayActivity.this.finish();

            }
        });


        ToggleButton change = (ToggleButton)findViewById(R.id.change);
        change.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("button_status",isChecked+"");
                start = !isChecked;




            }
        });
        if(path == null) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, FILE_SELECT_CODE);
        }




    }

    private static String op = null;

    void Init(){
        Log.e("in_path",path);
        String str = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            str = Util.Read(fis);
        } catch (FileNotFoundException e) {
            Log.e("read_file_error",e.toString());
            e.printStackTrace();
        }
        AbsoluteLayout layout = (AbsoluteLayout)findViewById(R.id.replay_layout);

        String[] ch1 = new String[30];
        String[] ch2 = new String[30];
        Scanner sc = new Scanner(str);
        for(int i=0;i<30;++i){
            ch1[i] = sc.next();
        }

        for(int i=0;i<30;++i){
            ch2[i] = sc.next();
        }
        op = "";
        while(sc.hasNext()){
            op += sc.next()+" ";
        }
        Log.e("mess1",ch1.toString());
        Log.e("mess2",ch2.toString());

        ChessBoard.replay_Init(str);
        mapAll = new MapAll(layout,getBaseContext(),
                ch1,ch2);
        layout.invalidate();

        thread = new Thread(new Runnable() {
            int ID ;
            @Override
            public void run() {
                ID = ++ReplayActivity.ID;
                Scanner sc = new Scanner(op);
                while(ID == ReplayActivity.ID && sc.hasNext()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(start) {
                        int sx = sc.nextInt();
                        int sy = sc.nextInt();

                        int ex = sc.nextInt();
                        int ey = sc.nextInt();
                        mapAll.moveTo(sx, sy, ex, ey);
                    }

                }
            }
        });
        thread.start();



        path = null;
    }

    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "ReplayActivity";
    private static String path = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode != MainActivity.RESULT_OK) {
            Log.e("ses", "onActivityResult() error, resultCode: " + resultCode);
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            path = uri.getPath();
            path = path.replaceAll("external_files","storage\\/emulated\\/0");
            Log.e("ses", "------->" + path);
            Init();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}