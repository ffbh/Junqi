package com.example.fbh.junqi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.fbh.junqi.file.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by husheng on 2018/5/27.
 */

public class RankActivity extends Activity {
    public  static String path = "/storage/emulated/0/junqi/rank";


    private  String get(int n){
        String s = "";
        for(int i=0;i<n;++i)
            s+=" ";
        return s;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank);

        String ans = null;

        try {
            FileInputStream fis = new FileInputStream(path);
            //      ans = Util.Read(openFileInput(path));
            ans = Util.Read(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Scanner sc = new Scanner(ans);

        int[] score = new int[4];
        int pos = 0;
        while(sc.hasNext()&&pos<4){
            score[pos++]=sc.nextInt();
        }

        for(int i=0;i<score.length;++i){
            Log.e("jj"+i,score[i]+"");
        }
        Arrays.sort(score);

        String[] s = new String[4];
        for(int i=0;i<4;++i) {
            s[i] = score[i] + "";
            if(s[i].length()<3){
                s[i] = get((3-s[i].length()) * 2)+s[i];
            }
        }

        ((Button)findViewById(R.id.e1)).setText("1   "+s[3]);
        ((Button)findViewById(R.id.e2)).setText("2   "+s[2]);
        ((Button)findViewById(R.id.e3)).setText("3   "+s[1]);
        ((Button)findViewById(R.id.e4)).setText("4   "+s[0]);

    }





}
