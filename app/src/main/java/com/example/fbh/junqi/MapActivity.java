package com.example.fbh.junqi;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.fbh.junqi.file.Util;


import java.io.*;

public class MapActivity extends Activity {



    MapSignal mapSignal = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);


        Button map_exit = (Button) findViewById(R.id.map_exit);
        map_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "map_exit");
                file_choose = false;
            //    Intent map_view = new Intent(MapActivity.this, MainActivity.class);
                MapActivity.this.finish();
            //    startActivity(map_view);

            }
        });


        Button map_save = (Button) findViewById(R.id.map_save);
        map_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "map_save");





                new AlertDialog.Builder(MapActivity.this)
                        .setTitle("保存")
                        .setMessage("正在保存到:"+path+"...")
                        .setPositiveButton("确定", null)
                        .show();


                file_choose = false;
                String ttt = mapSignal.getMaptext();
                Log.e("chess_mess",ttt);
                try {
                    Log.e("save_path",path);
                    FileOutputStream fos = new FileOutputStream(path);
                    //      ans = Util.Read(openFileInput(path));
                   Util.Write(fos,ttt);
                } catch (FileNotFoundException e) {
                    Log.e("error",e.toString());
                    e.printStackTrace();
                }


                new Thread(){
                    @Override
                    public void run() {
                        try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                  //      Intent map_view = new Intent(MapActivity.this, MainActivity.class);
                        MapActivity.this.finish();
                 //       startActivity(map_view);
                    }
                }.start();


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


        AbsoluteLayout layout = (AbsoluteLayout) findViewById(R.id.edit_layout);

        Log.e("file_status",file_choose+"");
        if (!file_choose) {
            file_choose = true;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/jq");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//            Uri textURI = FileProvider.getUriForFile(MapActivity.this.getBaseContext(),
//                        MapActivity.this.getBaseContext().getApplicationContext().getPackageName() + ".provider",
//                    new File("/storage/emulated/junqi/map/"));
//            intent.setData(textURI);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, FILE_SELECT_CODE);
        }



    //    String ch[] = Util.file_read(getResources().openRawResource(R.raw.moren));

        String ans = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            //      ans = Util.Read(openFileInput(path));
            ans = Util.Read(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String ch[] = ans.split("\\s+");

        mapSignal = new MapSignal(layout,getBaseContext(),
                ch,0X00ff00);
        layout.invalidate();

    }

    private static boolean file_choose = false;

    private static final int FILE_SELECT_CODE = 0;
    private static String path = "/storage/emulated/0/junqi/map/map1.jq";
    ///external_files/junqi/map/map5.jq


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
            path = uri.getPath();
            path = path.replaceAll("external_files","storage\\/emulated\\/0");
            Log.e("se", "------->" + path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
