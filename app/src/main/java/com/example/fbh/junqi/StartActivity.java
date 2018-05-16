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
    public static  MapAll mapAll;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

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

//                Intent map_view = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(map_view);
                StartActivity.this.finish();
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
                ch2,ch1,0X00ff00);
        layout.invalidate();


    }

}
