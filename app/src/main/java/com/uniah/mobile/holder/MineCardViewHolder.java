package com.uniah.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineCardViewHolder {

    public LinearLayout mTitleItem;
    public TextView mTitle;
    public TextView mSubTitle;

    public UniFrameLayout mMoreBtn;
    public UniFrameLayout mBackBtn;

    public LinearLayout mFirst;
    public UniRadiusView mFirstBackground;
    public ImageView mFirstImg;
    public TextView mFirstText;
    public TextView mFirstCount;

    public LinearLayout mSecond;
    public UniRadiusView mSecondBackground;
    public ImageView mSecondImg;
    public TextView mSecondText;
    public TextView mSecondCount;

    public LinearLayout mThird;
    public UniRadiusView mThirdBackground;
    public ImageView mThirdImg;
    public TextView mThirdText;
    public TextView mThirdCount;

    public LinearLayout mFourth;
    public UniRadiusView mFourthBackground;
    public ImageView mFourthImg;
    public TextView mFourthText;
    public TextView mFourthCount;

    public MineCardViewHolder(BaseViewHolder holder) {

        mTitleItem = holder.findViewById(R.id.card_title_item);
        mTitle = holder.findViewById(R.id.card_title_text);
        mSubTitle = holder.findViewById(R.id.card_title_sub_title);
        mMoreBtn = holder.findViewById(R.id.card_title_btn_layout);
        mBackBtn = holder.findViewById(R.id.card_title_btn_sub_layout);

        mFirst = holder.findViewById(R.id.card_part_four_first);
        mFirstBackground = holder.findViewById(R.id.card_part_four_first_img_bg);
        mFirstImg = holder.findViewById(R.id.card_part_four_first_img);
        mFirstText = holder.findViewById(R.id.card_part_four_first_text);
        mFirstCount = holder.findViewById(R.id.card_part_four_first_count);

        mSecond = holder.findViewById(R.id.card_part_four_second);
        mSecondBackground = holder.findViewById(R.id.card_part_four_second_img_bg);
        mSecondImg = holder.findViewById(R.id.card_part_four_second_img);
        mSecondText = holder.findViewById(R.id.card_part_four_second_text);
        mSecondCount = holder.findViewById(R.id.card_part_four_second_count);

        mThird = holder.findViewById(R.id.card_part_four_third);
        mThirdBackground = holder.findViewById(R.id.card_part_four_third_img_bg);
        mThirdImg = holder.findViewById(R.id.card_part_four_third_img);
        mThirdText = holder.findViewById(R.id.card_part_four_third_text);
        mThirdCount = holder.findViewById(R.id.card_part_four_third_count);

        mFourth = holder.findViewById(R.id.card_part_four_fourth);
        mFourthBackground = holder.findViewById(R.id.card_part_four_fourth_img_bg);
        mFourthImg = holder.findViewById(R.id.card_part_four_fourth_img);
        mFourthText = holder.findViewById(R.id.card_part_four_fourth_text);
        mFourthCount = holder.findViewById(R.id.card_part_four_fourth_count);
    }
}
