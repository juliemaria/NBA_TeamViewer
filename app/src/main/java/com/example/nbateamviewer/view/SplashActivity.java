package com.example.nbateamviewer.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.view.team.TeamsActivity;

public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, TeamsActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);

    }
}
