package com.example.fbh.junqi.file;

import android.content.res.Resources;
import android.util.Log;
import com.example.fbh.junqi.R;

import java.io.*;

public class Util {
    public static String[] file_read(InputStream file){
        String[] ans = new String[30];
        int pos = 0;
        try {


            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] chesss = lineTxt.split("\\s+");
                for(String s : chesss){
                    ans[pos++] = s;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        return ans;
    }

}
