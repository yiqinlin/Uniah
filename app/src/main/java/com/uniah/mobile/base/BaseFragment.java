package com.uniah.mobile.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.uniah.mobile.util.UniDisplayHelper;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UniDisplayHelper.setCustomDensity(getActivity(),getActivity().getApplication());
    }
}
