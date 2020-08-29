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

public class SplashHardActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView hardSplashImageView;
    TextView hardSplashTextView;
    private static int SPLASH_SCREEN = 5000;
    static MediaPlayer myTrack3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_hard);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animator);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animator);

        hardSplashImageView = findViewById(R.id.hardSplashImageView);
        hardSplashTextView = findViewById(R.id.hardSplashTextView);

        hardSplashImageView.setAnimation(topAnim);
        hardSplashTextView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashHardActivity.this, PlayHardActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);





    }



}