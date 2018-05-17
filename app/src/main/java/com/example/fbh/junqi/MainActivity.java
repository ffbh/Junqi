package com.example.fbh.junqi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.fbh.junqi.ClickEvent.StartEvent;
import com.example.fbh.junqi.file.Util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        Log.e(metric.widthPixels+"",metric.heightPixels+"");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        Button start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","start");
                StartEvent.gameover = false;
               Intent start_view = new Intent(MainActivity.this, StartActivity.class);
               startActivity(start_view);


            }
        });

        Button map = (Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","map");


                Intent map_view = new Intent(MainActivity.this, MapActivity.class);
                startActivity(map_view);

            }
        });

        Button replay = (Button)findViewById(R.id.replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","replay");

//                String filePath = "/storage/emulated/0/junqi/map/map1.jq";
//                File outputFile = new File(filePath);
//                if (!outputFile.getParentFile().exists()) {
//                     outputFile.getParentFile().mkdir();
//
//                }
//                Log.e("p_path",outputFile.getParent());
//                try {
////                    if(!outputFile.exists()) {
////                        try {
////                            outputFile.createNewFile();
////                        } catch (IOException e) {
////                            Log.e("create_file",e.toString());
////                            e.printStackTrace();
////                            return;
////                        }
////
////
////                    }
//                   // FileOutputStream fos  = openFileOutput(filePath,Context.MODE_PRIVATE);
//                    FileOutputStream fos  = new FileOutputStream(outputFile);
//                    String data = "hello,world! Zhang Phil @ CSDN";
//                    byte[] buffer = data.getBytes();
//                    // 开始写入数据到这个文件。
//                    fos.write(buffer, 0, buffer.length);
//                    fos.flush();
//                    fos.close();
//                } catch (IOException e) {
//                    Log.e("fail",e.toString());
//                    e.printStackTrace();
//                }

//                Log.e("path",outputFile.toString());
//                Uri textURI = FileProvider.getUriForFile(MainActivity.this.getBaseContext(),
//                        MainActivity.this.getBaseContext().getApplicationContext().getPackageName() + ".provider",
//                        outputFile);
//                Log.e("uri",textURI.toString());

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent,FILE_SELECT_CODE);
                Intent replay_view = new Intent(MainActivity.this, ReplayActivity.class);
                startActivity(replay_view);
            }
        });

        Button quit = (Button)findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","quit");
                System.exit(0);
            }
        });


    }


    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "MainActivity";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode != MainActivity.RESULT_OK) {
            Log.e(TAG, "onActivityResult() error, resultCode: " + resultCode);
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            Log.e(TAG, "------->" + uri.getPath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
