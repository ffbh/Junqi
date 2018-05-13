package com.example.fbh.junqi.ClickEvent;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.fbh.junqi.MainActivity;
import com.example.fbh.junqi.MapActivity;

public class mapEvent implements View.OnClickListener {
    private static Button but = null;
    public static int mm = Color.YELLOW;

    @Override
    public void onClick(View v) {
        Log.e("click","adjust map");
        if(but != null){
            String a = but.getText().toString();
            String b = ((Button)v).getText().toString();
            but.setBackgroundColor(mm);
            but.setText(b);
            ((Button) v).setText(a);
       //     but.invalidate();
       //     ((Button) v).invalidate();
            but = null;
            Log.e("swap",a+"<-->"+b);
        }
        else{
            but = (Button)v;
            but.setBackgroundColor(Color.RED);
        }
    }
}
