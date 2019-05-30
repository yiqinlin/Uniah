package com.uniah.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MineCardTitleData;
import com.uniah.mobile.bean.MineHeadData;
import com.uniah.mobile.bean.MineTeamData;
import com.uniah.mobile.bean.MineToolData;
import com.uniah.mobile.bean.MineUniData;
import com.uniah.mobile.holder.MineCardTitleViewHolder;
import com.uniah.mobile.holder.MineHeadViewHolder;
import com.uniah.mobile.holder.MineCardViewHolder;
import com.uniah.mobile.holder.MineUniViewHolder;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.List;

public class MineAdapter extends BaseAdapter<BaseData> {

    private Context mContext;
    private Activity mActivity;
    private MineHeadViewHolder mMineHeadViewHolder;
    private MineUniViewHolder mMineUniViewHolder;
    private MineCardViewHolder mMineToolViewHolder;
    private MineCardTitleViewHolder mMineCardTitleViewHolder;
    private MineCardViewHolder mMineTeamViewHolder;
    private int mDpRadius;
    private int mDpElevation;
    private float mAlpha = 0.1f;


    public MineAdapter(Context context, List<BaseData> list) {
        super(context, list);
        this.mContext = context;
        if (mContext instanceof Activity) {
            this.mActivity = (Activity) mContext;
        }
        mDpRadius = UniDisplayHelper.dp2px(mContext, 9);
        mDpElevation = UniDisplayHelper.dp2px(mContext, 2);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof MineHeadData) {
            onBindMineHead(holder, position, item);
        } else if (item instanceof MineUniData) {
            onBindMineUni(holder, position, item);
        } else if (item instanceof MineToolData) {
            onBindMineTool(holder, position, item);
        } else if (item instanceof MineCardTitleData) {
            onBindMineCardTitle(holder, position, item);
        } else if (item instanceof MineTeamData) {
            onBindMineTeam(holder, position, item);
        }
    }

    private void onBindMineTeam(BaseViewHolder holder, int position, BaseData item) {
        MineTeamData data = (MineTeamData) item;
        mMineTeamViewHolder = new MineCardViewHolder(holder);

        mMineTeamViewHolder.mTitle.setText(data.getToolTitle());

        if (!UniTextHelper.isEmpty(data.getToolSubTitle())) {
            mMineTeamViewHolder.mSubTitle.setText(data.getToolSubTitle());
            mMineTeamViewHolder.mSubTitle.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mSubTitle.setVisibility(View.GONE);
        }

        if (data.isShowMoreBtn()) {
            mMineTeamViewHolder.mMoreBtn.setOnClickListener(mMineTeamMoreClickListener);
            mMineTeamViewHolder.mMoreBtn.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mMoreBtn.setVisibility(View.GONE);
        }

        if (data.isShowBackBtn()) {
            mMineTeamViewHolder.mBackBtn.setOnClickListener(mMineTeamBackClickListener);
            mMineTeamViewHolder.mBackBtn.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mBackBtn.setVisibility(View.GONE);
        }

        mMineTeamViewHolder.mTitleItem.setOnClickListener(mMineTeamTitleItemClickListener);

        UniImageHelper.displayImage(mContext,data.getFirstLogo(),mMineTeamViewHolder.mFirstBackground);
        mMineTeamViewHolder.mFirst.setOnClickListener(mMineTeamFirstClickListener);
        mMineTeamViewHolder.mFirstImg.setVisibility(View.GONE);
        mMineTeamViewHolder.mFirstText.setText(data.getFirstName());
        if (data.getFirstCount() > 0) {
            mMineTeamViewHolder.mFirstCount.setText(data.firstCountString());
            mMineTeamViewHolder.mFirstCount.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mFirstCount.setVisibility(View.GONE);
        }

        UniImageHelper.displayImage(mContext,data.getSecondLogo(),mMineTeamViewHolder.mSecondBackground);
        mMineTeamViewHolder.mSecond.setOnClickListener(mMineTeamSecondClickListener);
        mMineTeamViewHolder.mSecondImg.setVisibility(View.GONE);
        mMineTeamViewHolder.mSecondText.setText(data.getSecondName());
        if (data.getSecondCount() > 0) {
            mMineTeamViewHolder.mSecondCount.setText(data.secondCountString());
            mMineTeamViewHolder.mSecondCount.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mSecondCount.setVisibility(View.GONE);
        }

        UniImageHelper.displayImage(mContext,data.getThirdLogo(),mMineTeamViewHolder.mThirdBackground);
        mMineTeamViewHolder.mThird.setOnClickListener(mMineTeamThirdClickListener);
        mMineTeamViewHolder.mThirdImg.setVisibility(View.GONE);
        mMineTeamViewHolder.mThirdText.setText(data.getThirdName());
        if (data.getThirdCount() > 0) {
            mMineTeamViewHolder.mThirdCount.setText(data.thirdCountString());
            mMineTeamViewHolder.mThirdCount.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mThirdCount.setVisibility(View.GONE);
        }

        UniImageHelper.displayImage(mContext,data.getFourthLogo(),mMineTeamViewHolder.mFourthBackground);
        mMineTeamViewHolder.mFourth.setOnClickListener(mMineTeamFourthClickListener);
        mMineTeamViewHolder.mFourthImg.setVisibility(View.GONE);
        mMineTeamViewHolder.mFourthText.setText(data.getFourthName());
        if (data.getFourthCount() > 0) {
            mMineTeamViewHolder.mFourthCount.setText(data.fourthCountString());
            mMineTeamViewHolder.mFourthCount.setVisibility(View.VISIBLE);
        } else {
            mMineTeamViewHolder.mFourthCount.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener mMineTeamTitleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click title", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click back", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamFirstClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part first", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamSecondClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part second", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamThirdClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part third", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineTeamFourthClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part fourth", Toast.LENGTH_SHORT).show();
        }
    };


    private void onBindMineCardTitle(BaseViewHolder holder, int position, BaseData item) {
        MineCardTitleData data = (MineCardTitleData) item;
        mMineCardTitleViewHolder = new MineCardTitleViewHolder(holder);

        mMineCardTitleViewHolder.mTitle.setText(data.getTitle());
        if (!UniTextHelper.isEmpty(data.getSubTitle())) {
            mMineCardTitleViewHolder.mSubTitle.setText(data.getSubTitle());
            mMineCardTitleViewHolder.mSubTitle.setVisibility(View.VISIBLE);
        } else {
            mMineCardTitleViewHolder.mSubTitle.setVisibility(View.GONE);
        }

        if (data.isShowMoreBtn()) {
            mMineCardTitleViewHolder.mMoreBtn.setOnClickListener(mMineCardTitleMoreClickListener);
            mMineCardTitleViewHolder.mMoreBtn.setVisibility(View.VISIBLE);
        } else {
            mMineCardTitleViewHolder.mMoreBtn.setVisibility(View.GONE);
        }

        if (data.isShowBackBtn()) {
            mMineCardTitleViewHolder.mBackBtn.setOnClickListener(mMineCardTitleBackClickListener);
            mMineCardTitleViewHolder.mBackBtn.setVisibility(View.VISIBLE);
        } else {
            mMineCardTitleViewHolder.mBackBtn.setVisibility(View.GONE);
        }

        mMineToolViewHolder.mTitleItem.setOnClickListener(mMineCardTitleItemClickListener);
    }

    private View.OnClickListener mMineCardTitleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click title", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineCardTitleMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineCardTitleBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click back", Toast.LENGTH_SHORT).show();
        }
    };


    private void onBindMineTool(BaseViewHolder holder, int position, BaseData item) {
        MineToolData data = (MineToolData) item;
        mMineToolViewHolder = new MineCardViewHolder(holder);

        mMineToolViewHolder.mTitle.setText(data.getToolTitle());

        if (!UniTextHelper.isEmpty(data.getToolSubTitle())) {
            mMineToolViewHolder.mSubTitle.setText(data.getToolSubTitle());
            mMineToolViewHolder.mSubTitle.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mSubTitle.setVisibility(View.GONE);
        }

        if (data.isShowMoreBtn()) {
            mMineToolViewHolder.mMoreBtn.setOnClickListener(mMineToolMoreClickListener);
            mMineToolViewHolder.mMoreBtn.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mMoreBtn.setVisibility(View.GONE);
        }

        if (data.isShowBackBtn()) {
            mMineToolViewHolder.mBackBtn.setOnClickListener(mMineToolBackClickListener);
            mMineToolViewHolder.mBackBtn.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mBackBtn.setVisibility(View.GONE);
        }

        mMineToolViewHolder.mTitleItem.setOnClickListener(mMineToolTitleItemClickListener);

        mMineToolViewHolder.mFirst.setOnClickListener(mMineToolFirstClickListener);
        mMineToolViewHolder.mFirstBackground.setBackgroundResource(data.getFirstBackgroundResource());
        mMineToolViewHolder.mFirstImg.setBackgroundResource(data.getFirstImageResource());
        mMineToolViewHolder.mFirstText.setText(data.getFirstName());
        if (data.getFirstCount() > 0) {
            mMineToolViewHolder.mFirstCount.setText(data.firstCountString());
            mMineToolViewHolder.mFirstCount.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mFirstCount.setVisibility(View.GONE);
        }

        mMineToolViewHolder.mSecond.setOnClickListener(mMineToolSecondClickListener);
        mMineToolViewHolder.mSecondBackground.setBackgroundResource(data.getSecondBackgroundResource());
        mMineToolViewHolder.mSecondImg.setBackgroundResource(data.getSecondImageResource());
        mMineToolViewHolder.mSecondText.setText(data.getSecondName());
        if (data.getSecondCount() > 0) {
            mMineToolViewHolder.mSecondCount.setText(data.secondCountString());
            mMineToolViewHolder.mSecondCount.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mSecondCount.setVisibility(View.GONE);
        }

        mMineToolViewHolder.mThird.setOnClickListener(mMineToolThirdClickListener);
        mMineToolViewHolder.mThirdBackground.setBackgroundResource(data.getThirdBackgroundResource());
        mMineToolViewHolder.mThirdImg.setBackgroundResource(data.getThirdImageResource());
        mMineToolViewHolder.mThirdText.setText(data.getThirdName());
        if (data.getThirdCount() > 0) {
            mMineToolViewHolder.mThirdCount.setText(data.thirdCountString());
            mMineToolViewHolder.mThirdCount.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mThirdCount.setVisibility(View.GONE);
        }

        mMineToolViewHolder.mFourth.setOnClickListener(mMineToolFourthClickListener);
        mMineToolViewHolder.mFourthBackground.setBackgroundResource(data.getFourthBackgroundResource());
        mMineToolViewHolder.mFourthImg.setBackgroundResource(data.getFourthImageResource());
        mMineToolViewHolder.mFourthText.setText(data.getFourthName());
        if (data.getFourthCount() > 0) {
            mMineToolViewHolder.mFourthCount.setText(data.fourthCountString());
            mMineToolViewHolder.mFourthCount.setVisibility(View.VISIBLE);
        } else {
            mMineToolViewHolder.mFourthCount.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener mMineToolTitleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click title", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click back", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolFirstClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part first", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolSecondClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part second", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolThirdClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part third", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMineToolFourthClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click part fourth", Toast.LENGTH_SHORT).show();
        }
    };

    private void onBindMineUni(BaseViewHolder holder, int position, BaseData item) {

        MineUniData data = (MineUniData) item;
        mMineUniViewHolder = new MineUniViewHolder(holder);

        UniImageHelper.displayImage(mContext, data.getUniLogo(), mMineUniViewHolder.mUniLogo);
        mMineUniViewHolder.mUniName.setText(data.getUniName());
        if (!UniTextHelper.isEmpty(data.getFlag())) {
            mMineUniViewHolder.mUniFlag.setText(data.getFlag());
            mMineUniViewHolder.mUniFlag.setVisibility(View.VISIBLE);
        } else {
            mMineUniViewHolder.mUniFlag.setVisibility(View.GONE);
        }
        mMineUniViewHolder.mUniSlogan.setText(data.getUniSlogan());
        mMineUniViewHolder.mUniItem.setOnClickListener(mMineUniClickListener);
    }

    private View.OnClickListener mMineUniClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click mine uni", Toast.LENGTH_SHORT).show();
        }
    };

    private void onBindMineHead(BaseViewHolder holder, int position, BaseData item) {

        MineHeadData data = (MineHeadData) item;
        mMineHeadViewHolder = new MineHeadViewHolder(holder);

        UniImageHelper.displayImage(mContext, data.getUserHead(), mMineHeadViewHolder.mHead);

        int statusBar = UniStatusBarHelper.getTranslucentHeight(mActivity);
        LayoutParams lp = (LayoutParams) mMineHeadViewHolder.mMineHeadItem.getLayoutParams();
        lp.topMargin = statusBar;
        mMineHeadViewHolder.mMineHeadItem.setLayoutParams(lp);

        mMineHeadViewHolder.mNick.setText(data.getUserNick());
        mMineHeadViewHolder.mSlogan.setText(data.getUserSlogan());

        mMineHeadViewHolder.mLeftInfoCount.setText(String.valueOf(data.getLeftCount()));
        mMineHeadViewHolder.mMidInfoCount.setText(String.valueOf(data.getMidCount()));
        mMineHeadViewHolder.mRightInfoCount.setText(String.valueOf(data.getRightCount()));

        mMineHeadViewHolder.mLeftInfoTitle.setText(data.getLeftTitle());
        mMineHeadViewHolder.mMidInfoTitle.setText(data.getMidTitle());
        mMineHeadViewHolder.mRightInfoTitle.setText(data.getRightTitle());

        mMineHeadViewHolder.mMineInfo.setOnClickListener(mUserClickListener);
        mMineHeadViewHolder.mBtnLayout.setOnClickListener(mBtnClickListener);
        mMineHeadViewHolder.mSubBtnLayout.setOnClickListener(mSubBtnClickListener);

        mMineHeadViewHolder.mLeftInfo.setOnClickListener(mLeftInfoClickListener);
        mMineHeadViewHolder.mMidInfo.setOnClickListener(mMidClickListener);
        mMineHeadViewHolder.mRightInfo.setOnClickListener(mRightInfoClickListener);
    }

    private View.OnClickListener mUserClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click user", Toast.LENGTH_SHORT).show();
        }
    };


    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click btn", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mSubBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click sub btn", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mLeftInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click left info", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMidClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click mid info", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mRightInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click right info", Toast.LENGTH_SHORT).show();
        }
    };

}
