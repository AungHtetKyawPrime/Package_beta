package com.example.package_beta;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int splashTimeOut=1400;
    private static int logo_time=700;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView logo = (ImageView) findViewById(R.id.logo_image);
        final ImageView logo_text=(ImageView)findViewById(R.id.package_logo_text);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo_text.setVisibility(View.VISIBLE);
            }
        },logo_time);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,MainFragment.class);
                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.logo_rotate);
        logo.startAnimation(myanim);
    }

}
