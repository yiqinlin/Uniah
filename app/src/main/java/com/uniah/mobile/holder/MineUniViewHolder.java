package com.uniah.mobile.holder;

import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineUniViewHolder {

    public UniLinearLayout mUniItem;
    public UniRadiusView mUniLogo;
    public TextView mUniName;
    public TextView mUniFlag;
    public TextView mUniSlogan;


    public MineUniViewHolder(BaseViewHolder holder) {
        mUniItem = holder.findViewById(R.id.mine_uni_item);
        mUniLogo = holder.findViewById(R.id.mine_uni_logo);
        mUniName = holder.findViewById(R.id.mine_uni_name);
        mUniFlag = holder.findViewById(R.id.mine_uni_flag);
        mUniSlogan = holder.findViewById(R.id.mine_uni_slogan);
    }
}
