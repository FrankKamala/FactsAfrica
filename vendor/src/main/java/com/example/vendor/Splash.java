package com.example.vendor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.example.vendor.ui.LoginActivity;

public class Splash extends AppCompatActivity {
    ConstraintLayout myVendorConstraint;
    AnimationDrawable myVendorAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myVendorConstraint=(ConstraintLayout)findViewById(R.id.vendorSplash);
        myVendorAnimator=(AnimationDrawable)myVendorConstraint.getBackground();
        myVendorAnimator.setEnterFadeDuration(2000);
        myVendorAnimator.setExitFadeDuration(2000);
        myVendorAnimator.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class)); //starts main Activity
                finish();

            }
        },  3000);
    }
}
