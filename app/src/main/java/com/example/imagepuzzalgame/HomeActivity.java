package com.example.imagepuzzalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tv;
    Animation topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv= findViewById(R.id.textView);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animator);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        tv.setAnimation(topAnim);

    }


    public void callingEasySplashActivity(View view){
        startActivity(new Intent(HomeActivity.this, SplashEasyActivity.class));
    }
    public void callingHardSplashActivity(View view){
        startActivity(new Intent(HomeActivity.this, SplashHardActivity.class));
    }

}