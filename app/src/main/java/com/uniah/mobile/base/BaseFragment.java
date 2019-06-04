package com.uniah.mobile.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.SetFeedbackActivity;
import com.uniah.mobile.bean.MoreDialogData;
import com.uniah.mobile.util.UniDisplayHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UniDisplayHelper.setCustomDensity(getActivity(),getActivity().getApplication());
    }

    protected List<BaseData> getMoreDialogList() {
        List<BaseData> list = new ArrayList<>();

        MoreDialogData item1 = new MoreDialogData();
        item1.setIconId(R.drawable.ic_scan);
        item1.setText("扫一扫");
        item1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        list.add(item1);

        MoreDialogData item2 = new MoreDialogData();
        item2.setIconId(R.drawable.ic_add_friend_theme);
        item2.setText("找朋友");
        item2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        list.add(item2);

        MoreDialogData item3 = new MoreDialogData();
        item3.setIconId(R.drawable.ic_create_team_theme);
        item3.setText("创组织");
        item3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        list.add(item3);

        MoreDialogData item4 = new MoreDialogData();
        item4.setIconId(R.drawable.ic_feedback);
        item4.setText("提建议");
        item4.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetFeedbackActivity.class);
                startActivity(intent);
            }
        });
        list.add(item4);

        return list;
    }
}
