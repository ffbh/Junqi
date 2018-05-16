package com.example.fbh.junqi.file;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.content.FileProvider;
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

    public static Intent getTextFileIntent(Uri uri2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

     //   Uri uri2 = Uri.fromFile(new File(param ));
     //   Uri uri2 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider");
        intent.setDataAndType(uri2, "text/plain");

        return intent;
    }


    public static String Read(FileInputStream fis){
        String ans = "";
        try {
            int len = 0;
            byte[] buf = new byte[4096];
            while ((len = fis.read(buf)) != -1) {
                ans += new String(buf, 0, len);
            }
            fis.close();
        }
        catch(Exception e){
            Log.e("read_error",e.toString());
        }

       return ans;
    }

    public static void  Write(FileOutputStream fos,String data){
        Log.e("write","start");
        try {
            byte[] buffer = data.getBytes();
            // 开始写入数据到这个文件。
            fos.write(buffer, 0, buffer.length);
            fos.flush();
            fos.close();
        }
        catch(Exception e){
            Log.e("write_error",e.toString());
        }
        Log.e("write","end");
    }

}
