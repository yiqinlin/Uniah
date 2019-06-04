package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class DataHomeViewHolder extends BaseViewHolder {

    public ImageView mWall;
    public UniLinearLayout mItem;

    public UniRadiusView mHead;
    public TextView mNick;

    public TextView mFlagLeft;
    public TextView mFlagRight;

    public TextView mAccount;

    public TextView mSlogan;

    public LinearLayout mInfoLayout;

    public TextView mInfoFirst;
    public TextView mInfoSecond;
    public TextView mInfoThird;


    public DataHomeViewHolder(Context context, View itemView) {
        super(context, itemView);
        mWall = itemView.findViewById(R.id.data_home_wall);
        mHead = itemView.findViewById(R.id.data_home_head);
        mNick = itemView.findViewById(R.id.data_home_name);

        mFlagLeft = itemView.findViewById(R.id.data_home_flag_left);
        mFlagRight = itemView.findViewById(R.id.data_home_flag_right);
        mAccount = itemView.findViewById(R.id.data_home_id);

        mSlogan = itemView.findViewById(R.id.data_home_slogan);

        mInfoLayout = itemView.findViewById(R.id.data_home_info);

        mInfoFirst = itemView.findViewById(R.id.data_home_info_first);
        mInfoSecond = itemView.findViewById(R.id.data_home_info_second);
        mInfoThird = itemView.findViewById(R.id.data_home_info_third);
    }
}
