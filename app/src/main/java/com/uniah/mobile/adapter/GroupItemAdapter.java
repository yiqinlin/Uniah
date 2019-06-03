package com.uniah.mobile.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.GroupBaseData;
import com.uniah.mobile.bean.GroupEditData;
import com.uniah.mobile.bean.GroupHeadData;
import com.uniah.mobile.bean.GroupHintData;
import com.uniah.mobile.bean.GroupRadioData;
import com.uniah.mobile.bean.GroupSwitchData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.GroupUserData;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.holder.GroupBaseViewHolder;
import com.uniah.mobile.holder.GroupEditViewHolder;
import com.uniah.mobile.holder.GroupHeadViewHolder;
import com.uniah.mobile.holder.GroupHintViewHolder;
import com.uniah.mobile.holder.GroupRadioViewHolder;
import com.uniah.mobile.holder.GroupSwitchViewHolder;
import com.uniah.mobile.holder.GroupTextViewHolder;
import com.uniah.mobile.holder.GroupUserViewHolder;
import com.uniah.mobile.holder.HintViewHolder;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.List;

public class GroupItemAdapter extends BaseAdapter<BaseData> {

    private GroupSwitchData mGroupSwitchData;

    public GroupItemAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof GroupTextData) {
            onBindGroupText(holder, position, item);
        } else if (item instanceof GroupEditData) {
            onBindGroupEdit(holder, position, item);
        } else if (item instanceof GroupSwitchData) {
            onBindGroupSwitch(holder, position, item);
        } else if (item instanceof GroupRadioData) {
            onBindGroupRadio(holder, position, item);
        } else if (item instanceof GroupHeadData) {
            onBindGroupHead(holder, position, item);
        } else if (item instanceof GroupUserData) {
            onBindGroupUser(holder, position, item);
        } else if (item instanceof GroupHintData) {
            onBindGroupHint(holder, position, item);
        }
    }

    private void onBindGroupHint(BaseViewHolder holder, int position, BaseData item) {
        GroupHintData data = (GroupHintData) item;
        GroupHintViewHolder viewHolder = (GroupHintViewHolder) holder;
        viewHolder.mHintText.setText(UniTextHelper.isEmpty(data.getHint()) ? "" : data.getHint());
    }

    private void onBindGroupUser(BaseViewHolder holder, int position, BaseData item) {
        GroupUserViewHolder viewHolder = (GroupUserViewHolder) holder;
        GroupUserData data = (GroupUserData) item;

        UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);
        viewHolder.mArrow.setVisibility(data.isShowArrow() ? View.VISIBLE : View.GONE);
        viewHolder.mNick.setText(data.getNick());

        if (data.isShowSubBtn()) {
            viewHolder.mSubBtn.setVisibility(View.VISIBLE);
            viewHolder.mSubImg.setBackgroundResource(data.getSubBackgroundResourceId());
        } else {
            viewHolder.mSubBtn.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getFlag())) {
            viewHolder.mFlag.setVisibility(View.VISIBLE);
            viewHolder.mFlag.setText(data.getFlag());
        } else {
            viewHolder.mFlag.setVisibility(View.GONE);
        }

        viewHolder.mSlogan.setText(data.getSlogan());

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }

    private void onBindGroupHead(BaseViewHolder holder, int position, BaseData item) {
        GroupHeadViewHolder viewHolder = (GroupHeadViewHolder) holder;
        GroupHeadData data = (GroupHeadData) item;

        UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);
        viewHolder.mHeadHint.setText(data.getHint());

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }


    private void onBindGroupRadio(BaseViewHolder holder, int position, BaseData item) {
        GroupRadioViewHolder viewHolder = (GroupRadioViewHolder) holder;
        GroupRadioData data = (GroupRadioData) item;

        viewHolder.mText.setText(data.getText());
        viewHolder.mRadio.setChecked(data.isChecked());

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }

    private void onBindGroupSwitch(BaseViewHolder holder, int position, BaseData item) {
        GroupSwitchViewHolder viewHolder = (GroupSwitchViewHolder) holder;
        mGroupSwitchData = (GroupSwitchData) item;

        viewHolder.mText.setText(mGroupSwitchData.getText());
        viewHolder.mSwitch.setChecked(mGroupSwitchData.isChecked());
        viewHolder.mSwitch.setOnCheckedChangeListener(mSwitchCheckedChangeListener);

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }

    private CompoundButton.OnCheckedChangeListener mSwitchCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mGroupSwitchData.setChecked(isChecked);
        }
    };

    private void onBindGroupEdit(BaseViewHolder holder, int position, BaseData item) {
        GroupEditViewHolder viewHolder = (GroupEditViewHolder) holder;
        GroupEditData mGroupEditData = (GroupEditData) item;

        viewHolder.mText.setText(mGroupEditData.getText());
        viewHolder.mEdit.setText(!TextUtils.isEmpty(mGroupEditData.getEditText()) ? "" : mGroupEditData.getEditText());
        viewHolder.mEdit.setHint(!TextUtils.isEmpty(mGroupEditData.getHint()) ? "" : mGroupEditData.getHint());

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }

    private void onBindGroupText(BaseViewHolder holder, int position, BaseData item) {

        GroupTextViewHolder viewHolder = (GroupTextViewHolder) holder;
        GroupTextData data = (GroupTextData) item;

        viewHolder.mText.setText(data.getText());
        if (!UniTextHelper.isEmpty(data.getSubText())) {
            viewHolder.mSubText.setVisibility(View.VISIBLE);
            viewHolder.mSubText.setText(data.getSubText());
        } else {
            viewHolder.mSubText.setVisibility(View.GONE);
        }
        viewHolder.mBtn.setVisibility(data.isShowBtn() ? View.VISIBLE : View.GONE);
        viewHolder.mSubBtn.setVisibility(data.isShowSubBtn() ? View.VISIBLE : View.GONE);

        viewHolder.mBtn.setOnClickListener(mBtnClickListener);
        viewHolder.mSubBtn.setOnClickListener(mSubBtnClickListener);

        setItemCommon((GroupBaseViewHolder) holder, (GroupBaseData) item);
    }


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

    private void setItemCommon(GroupBaseViewHolder holder, GroupBaseData data) {
        holder.mItem.setHideRadiusSide(data.getHideRadiusSide());
        if (data.getHideRadiusSide() != IUniLayout.HIDE_RADIUS_SIDE_TOP && data.getHideRadiusSide() != IUniLayout.HIDE_RADIUS_SIDE_NONE) {
            holder.mLine.setVisibility(View.VISIBLE);
        } else {
            holder.mLine.setVisibility(View.GONE);
        }

        if (data.getHideRadiusSide() == IUniLayout.HIDE_RADIUS_SIDE_BOTTOM || data.getHideRadiusSide() == IUniLayout.HIDE_RADIUS_SIDE_NONE) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.mItem.getLayoutParams();
            params.setMargins(params.getMarginStart(), UniDisplayHelper.dp2px(mContext, 12), params.getMarginEnd(), 0);
        }

        holder.mItem.setOnClickListener(data.getOnItemClickListener());
    }
}
