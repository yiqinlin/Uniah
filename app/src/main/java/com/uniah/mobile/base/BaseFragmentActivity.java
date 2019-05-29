package com.uniah.mobile.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.uniah.mobile.util.UniDisplayHelper;

public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UniDisplayHelper.setCustomDensity(this,getApplication());
    }
}
