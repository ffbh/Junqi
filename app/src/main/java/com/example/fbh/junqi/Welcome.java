package com.example.fbh.junqi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by husheng on 2018/5/27.
 */

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        ImageView image = (ImageView)findViewById(R.id.welcome);
       image.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
               Intent main = new Intent(Welcome.this, MainActivity.class);
               Welcome.this.finish();
               startActivity(main);
           }
       });




    }

}
