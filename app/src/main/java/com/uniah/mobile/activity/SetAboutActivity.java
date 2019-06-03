package com.uniah.mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uniah.mobile.R;
import com.uniah.mobile.util.UniStatusBarHelper;

public class SetAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_about);
        UniStatusBarHelper.translucent(this);
    }
}
