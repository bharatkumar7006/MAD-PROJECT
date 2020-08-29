package com.example.imagepuzzalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashEasyActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView easySplashImageView;
    TextView easySplashTextView;
    private static int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_easy);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animator);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animator);

        easySplashImageView = findViewById(R.id.easySplashImageView);
        easySplashTextView = findViewById(R.id.easySplashTextView);

        easySplashImageView.setAnimation(topAnim);
        easySplashTextView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashEasyActivity.this, PlayEasyActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }
}