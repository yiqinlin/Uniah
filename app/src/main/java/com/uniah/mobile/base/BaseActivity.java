package com.uniah.mobile.base;

import android.app.Activity;
import android.os.Bundle;

import com.uniah.mobile.util.UniDisplayHelper;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UniDisplayHelper.setCustomDensity(this, getApplication());
    }
}
