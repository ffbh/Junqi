package com.example.fbh.junqi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.fbh.junqi.file.Util;


import java.io.File;
import java.io.InputStream;

public class MapActivity extends Activity {



    MapSignal mapSignal = null;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);








        Button map_exit = (Button)findViewById(R.id.map_exit);
        map_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","map_exit");

                Intent map_view = new Intent(MapActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });


        Button map_save = (Button)findViewById(R.id.map_save);
        map_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","map_save");

                Intent map_view = new Intent(MapActivity.this, MainActivity.class);
                startActivity(map_view);
            }
        });


//        ImageView map = findViewById(R.id.map_edit);
//        map.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("touch","map_edit");
//                int x = Math.round(event.getX());
//                int y = Math.round(event.getY());
//                Log.e("X:",x+"");
//                Log.e("Y:",y+"");
//
//
//
//
//                return false;
//            }
//        });


      //  Log.e("Vmap:",map.getVisibility()+"");
        AbsoluteLayout layout = (AbsoluteLayout)findViewById(R.id.edit_layout);
  //      Log.e("layoutH",layout.getHeight()+"");

        Log.e("test","start");


        String ch[] = Util.file_read(getResources().openRawResource(R.raw.moren));


        Log.e("test","ok");

        mapSignal = new MapSignal(layout,getBaseContext(),
                ch,0X00ff00);
        layout.invalidate();
    }


}
