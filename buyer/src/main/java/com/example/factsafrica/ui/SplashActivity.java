package com.example.factsafrica.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.example.factsafrica.R;

public class SplashActivity extends AppCompatActivity {
    ConstraintLayout myConstraint;
    AnimationDrawable myAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myConstraint=(ConstraintLayout)findViewById(R.id.screenSplash);
        myAnimator=(AnimationDrawable)myConstraint.getBackground();
        myAnimator.setEnterFadeDuration(2000);
        myAnimator.setExitFadeDuration(2000);
        myAnimator.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class)); //starts main Activity
                finish();

            }
        },  3000);
    }



}
