package com.example.fbh.junqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import com.example.fbh.junqi.Board.ChessBoard;
import com.example.fbh.junqi.file.Util;

public class StartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Button map_exit = (Button)findViewById(R.id.start_exit);
        map_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_exit");

                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });


        Button map_save = (Button)findViewById(R.id.start_regret);
        map_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_regret");

                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });

        Button start_fail = (Button)findViewById(R.id.start_fail);
        start_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_fail");

                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });

        Button start_map = (Button)findViewById(R.id.start_map);
        start_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start_map");

                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });

        AbsoluteLayout layout = (AbsoluteLayout)findViewById(R.id.edit_layout);




        String ch1[] = Util.file_read(getResources().openRawResource(R.raw.moren));
        String ch2[] = new String[30];
        for(int i=0;i<30;++i)
            ch2[i] = ch1[29-i];

        ChessBoard.Init(ch2,ch1);

        MapAll mapAll = new MapAll(layout,getBaseContext(),
                ch2,ch1,0X00ff00);
        layout.invalidate();




    }



}
