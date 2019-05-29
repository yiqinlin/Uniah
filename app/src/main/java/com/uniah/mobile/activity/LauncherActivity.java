package com.uniah.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uniah.mobile.R;
import com.uniah.mobile.util.UniCookie;

public class LauncherActivity extends Activity {

    private int time = 0;
    private Button skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        skip = (Button) findViewById(R.id.start_page_btn_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.removeCallbacks(Count);
                turnTo();
            }
        });
        handle.postDelayed(Count, 0);
    }

    private void turnTo() {
        handle.removeCallbacks(Count);
        Class<?> temp = LoginActivity.class;
        if (UniCookie.getBoolean(LauncherActivity.this, "login", true)) {
            temp = MainActivity.class;
        }
        Intent intent = new Intent(LauncherActivity.this, temp);
        startActivity(intent);
        finish();
    }

    Handler handle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handle.removeCallbacks(Count);
            return false;
        }
    });

    Runnable Count = new Runnable() {
        @Override
        public void run() {
            if (time == 0) {
                turnTo();
                return;
            }
            if (skip != null) {
                skip.setText(String.valueOf("跳过" + time--));
            }
            handle.postDelayed(Count, 1000);
        }
    };
}
