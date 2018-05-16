package com.example.fbh.junqi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.file.Util;

public class Choose extends Activity{

    private static String Apath = "/storage/emulated/0/junqi/map/map1.jq";
    private static String Bpath = "/storage/emulated/0/junqi/map/map1.jq";
    private static String choose_F = "A";
    private static final int FILE_SELECT_CODE = 0;
    private static Button A,B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        A = (Button)findViewById(R.id.A);
        A.setText(Apath);
        A.invalidate();
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","chooseA");
                choose_F = "A";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/jq");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, FILE_SELECT_CODE);



            }
        });

        B = (Button)findViewById(R.id.B);
        B.setText(Bpath);
        B.invalidate();
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","chooseB");
                choose_F = "B";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/jq");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, FILE_SELECT_CODE);
            }
        });

        Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","ok");

                String[] ch1 = Util.getChess(Apath);//reverse
                ch1 = Util.reverse(ch1);
                String[] ch2 = Util.getChess(Bpath);

                ChessBoard.Init(ch1,ch2);
                StartActivity.mapAll.setMapStart(ch1,ch2);
                Choose.this.finish();

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode != MainActivity.RESULT_OK) {
            Log.e("se", "onActivityResult() error, resultCode: " + resultCode);
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            String path = uri.getPath();
            path = path.replaceAll("external_files","storage\\/emulated\\/0");
            Log.e("se", "------->" + path);
            if(choose_F.equals("A")){
                Apath  = path;
                A.setText(path);
                A.invalidate();
            }
            else{
                Bpath = path;
                B.setText(path);
                B.invalidate();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }





}
