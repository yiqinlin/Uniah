package com.uniah.mobile.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.uniah.mobile.R;
import com.uniah.mobile.util.UniImageHelper;

import java.util.List;

public class UniImgBox extends FrameLayout {

    private Context mContext;

    private LinearLayout mImgBox;

    private LinearLayout mImgBoxDoubleTop;
    private LinearLayout mImgBoxDoubleBtm;
    private LinearLayout mImgBoxTrebleTop;
    private LinearLayout mImgBoxTrebleMid;
    private LinearLayout mImgBoxTrebleBtm;

    private ImageView mImgBoxSingle;

    private ImageView mImgBoxDoubleTopLeft;
    private ImageView mImgBoxDoubleTopRight;

    private ImageView mImgBoxDoubleBtmLeft;
    private ImageView mImgBoxDoubleBtmRight;

    private ImageView mImgBoxTrebleTopLeft;
    private ImageView mImgBoxTrebleTopMid;
    private ImageView mImgBoxTrebleTopRight;

    private ImageView mImgBoxTrebleMidLeft;
    private ImageView mImgBoxTrebleMidMid;
    private ImageView mImgBoxTrebleMidRight;

    private ImageView mImgBoxTrebleBtmLeft;
    private ImageView mImgBoxTrebleBtmMid;
    private ImageView mImgBoxTrebleBtmRight;

    public UniImgBox(@NonNull Context context) {
        this(context, null);
    }

    public UniImgBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        mImgBox = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.feed_img, null);

        mImgBoxDoubleTop = mImgBox.findViewById(R.id.img_box_double_top);
        mImgBoxDoubleBtm = mImgBox.findViewById(R.id.img_box_double_btm);
        mImgBoxTrebleTop = mImgBox.findViewById(R.id.img_box_treble_top);
        mImgBoxTrebleMid = mImgBox.findViewById(R.id.img_box_treble_mid);
        mImgBoxTrebleBtm = mImgBox.findViewById(R.id.img_box_treble_btm);

        mImgBoxSingle = mImgBox.findViewById(R.id.img_box_single);

        mImgBoxDoubleTopLeft = mImgBox.findViewById(R.id.img_box_double_top_left);
        mImgBoxDoubleTopRight = mImgBox.findViewById(R.id.img_box_double_top_right);

        mImgBoxDoubleBtmLeft = mImgBox.findViewById(R.id.img_box_double_btm_left);
        mImgBoxDoubleBtmRight = mImgBox.findViewById(R.id.img_box_double_btm_right);

        mImgBoxTrebleTopLeft = mImgBox.findViewById(R.id.img_box_treble_top_left);
        mImgBoxTrebleTopMid = mImgBox.findViewById(R.id.img_box_treble_top_mid);
        mImgBoxTrebleTopRight = mImgBox.findViewById(R.id.img_box_treble_top_right);

        mImgBoxTrebleMidLeft = mImgBox.findViewById(R.id.img_box_treble_mid_left);
        mImgBoxTrebleMidMid = mImgBox.findViewById(R.id.img_box_treble_mid_mid);
        mImgBoxTrebleMidRight = mImgBox.findViewById(R.id.img_box_treble_mid_right);

        mImgBoxTrebleBtmLeft = mImgBox.findViewById(R.id.img_box_treble_btm_left);
        mImgBoxTrebleBtmMid = mImgBox.findViewById(R.id.img_box_treble_btm_mid);
        mImgBoxTrebleBtmRight = mImgBox.findViewById(R.id.img_box_treble_btm_right);

        addView(mImgBox);
    }

    public void setImgLayout(List<String> img) {
        ImageLoader imageLoader = UniImageHelper.getDefaultImageLoader(mContext);
        DisplayImageOptions options = UniImageHelper.getSimpleOptions();
        imgBoxReset();
        int size = img.size();

        if (size > 0) {
            Log.e("size", size + ": 0");
            mImgBox.setVisibility(VISIBLE);
        }
        if (size == 1) {
            Log.e("size", size + ": 1");
            mImgBoxSingle.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(0), mImgBoxSingle, options);
        }
        if (size == 2 || size == 4) {
            Log.e("size", size + ": 2");
            mImgBoxDoubleTop.setVisibility(VISIBLE);
            mImgBoxDoubleTopLeft.setVisibility(VISIBLE);
            mImgBoxDoubleTopRight.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(0), mImgBoxDoubleTopLeft, options);
            imageLoader.displayImage(img.get(1), mImgBoxDoubleTopRight, options);
            if (size == 4) {
                Log.e("size", size + ": 4");
                mImgBoxDoubleBtm.setVisibility(VISIBLE);
                mImgBoxDoubleBtmLeft.setVisibility(VISIBLE);
                mImgBoxDoubleBtmRight.setVisibility(VISIBLE);
                imageLoader.displayImage(img.get(2), mImgBoxDoubleBtmLeft, options);
                imageLoader.displayImage(img.get(3), mImgBoxDoubleBtmRight, options);
            }
        }

        if (size >= 3 && size != 4) {
            Log.e("size", size + ": 3");
            mImgBoxTrebleTop.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(0), mImgBoxTrebleTopLeft, options);
            imageLoader.displayImage(img.get(1), mImgBoxTrebleTopMid, options);
            imageLoader.displayImage(img.get(2), mImgBoxTrebleTopRight, options);
        }

        if (size >= 5) {
            Log.e("size", size + ": 5");
            mImgBoxTrebleMid.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(3), mImgBoxTrebleMidLeft, options);
            imageLoader.displayImage(img.get(4), mImgBoxTrebleMidMid, options);
        }
        if (size >= 6) {
            Log.e("size", size + ": 6");
            mImgBoxTrebleMidRight.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(5), mImgBoxTrebleMidRight, options);
        }
        if (size >= 7) {
            Log.e("size", size + ": 7");
            imageLoader.displayImage(img.get(6), mImgBoxTrebleBtmLeft, options);
        }
        if (size >= 8) {
            Log.e("size", size + ": 8");
            mImgBoxTrebleBtmMid.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(7), mImgBoxTrebleBtmMid, options);
        }
        if (size >= 9) {
            Log.e("size", size + ": 9");
            mImgBoxTrebleBtmRight.setVisibility(VISIBLE);
            imageLoader.displayImage(img.get(8), mImgBoxTrebleBtmRight, options);
        }
    }

    void imgBoxReset() {
        mImgBox.setVisibility(GONE);

        mImgBoxSingle.setVisibility(GONE);

        mImgBoxDoubleTop.setVisibility(GONE);
        mImgBoxDoubleBtm.setVisibility(GONE);

        mImgBoxTrebleTop.setVisibility(GONE);
        mImgBoxTrebleMid.setVisibility(GONE);
        mImgBoxTrebleBtm.setVisibility(GONE);

        mImgBoxTrebleMidRight.setVisibility(GONE);
        mImgBoxTrebleBtmMid.setVisibility(GONE);
        mImgBoxTrebleBtmRight.setVisibility(GONE);
    }
}
