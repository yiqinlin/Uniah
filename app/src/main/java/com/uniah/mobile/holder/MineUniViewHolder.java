package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineUniViewHolder extends BaseViewHolder {

    public UniLinearLayout mUniItem;
    public UniRadiusView mUniLogo;
    public TextView mUniName;
    public TextView mUniFlag;
    public TextView mUniSlogan;


    public MineUniViewHolder(Context context, View convertView) {
        super(context, convertView);
        mUniItem = convertView.findViewById(R.id.mine_uni_item);
        mUniLogo = convertView.findViewById(R.id.mine_uni_logo);
        mUniName = convertView.findViewById(R.id.mine_uni_name);
        mUniFlag = convertView.findViewById(R.id.mine_uni_flag);
        mUniSlogan = convertView.findViewById(R.id.mine_uni_slogan);
    }
}
