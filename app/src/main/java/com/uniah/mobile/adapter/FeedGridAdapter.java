package com.uniah.mobile.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseListAdapter;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.FeedGridData;
import com.uniah.mobile.engine.UniversalImageEngine;
import com.uniah.mobile.holder.FeedGridViewHolder;
import com.uniah.mobile.imagebrowserlibrary.ImageEngine;
import com.uniah.mobile.imagebrowserlibrary.MNImageBrowser;
import com.uniah.mobile.imagebrowserlibrary.listeners.OnClickListener;
import com.uniah.mobile.imagebrowserlibrary.listeners.OnLongClickListener;
import com.uniah.mobile.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.uniah.mobile.imagebrowserlibrary.model.ImageBrowserConfig;
import com.uniah.mobile.util.UniImageHelper;

import java.util.List;

public class FeedGridAdapter extends BaseListAdapter<BaseData> {


    private int mNumColumn;
    private List<String> mImgList;
    FeedGridViewHolder viewHolder;

    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.ScreenOrientation_Default;
    private ImageEngine imageEngine = new UniversalImageEngine();
    private int openAnim = R.anim.mn_browser_enter_anim;
    private int exitAnim = R.anim.mn_browser_exit_anim;

    //显示自定义遮盖层
    private boolean showCustomShadeView = false;
    //显示ProgressView
    private boolean showCustomProgressView = false;
    //是不是全屏模式
    private boolean isFulScreenMode = true;



    public FeedGridAdapter(Context context, List<BaseData> dataList, int numColumn) {
        super(context, dataList);
        this.mNumColumn = numColumn;
    }

    @Override
    public void convert(BaseViewHolder holder, final int position, BaseData item) {
        if (item instanceof FeedGridData) {
            FeedGridData data = (FeedGridData) item;
            viewHolder = (FeedGridViewHolder) holder;


            viewHolder.setImgSize(mContext, mNumColumn);
            UniImageHelper.displayImage(mContext, data.getImgUrl(), viewHolder.mImg);

            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MNImageBrowser.with(mContext)
                            //页面切换效果
                            .setTransformType(transformType)
                            //指示器效果
                            .setIndicatorType(indicatorType)
//                            //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
//                            .setCustomShadeView(null)
                            //自定义ProgressView，不设置默认默认没有
                            //.setCustomProgressViewLayoutID(showCustomProgressView ? R.layout.layout_custom_progress_view : 0)
                            //当前位置
                            .setCurrentPosition(position)
                            //图片引擎
                            .setImageEngine(imageEngine)
                            //图片集合
                            .setImageList(mImgList)
                            //方向设置
                            .setScreenOrientationType(screenOrientationType)
                            //点击监听
                            .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(FragmentActivity activity, ImageView view, int position, String url) {

                                }
                            })
                            //长按监听
                            .setOnLongClickListener(new OnLongClickListener() {
                                @Override
                                public void onLongClick(final FragmentActivity activity, final ImageView imageView, int position, String url) {
                                    //showListDialog(activity, imageView);
                                }
                            })
                            //页面切换监听
                            .setOnPageChangeListener(new OnPageChangeListener() {
                                @Override
                                public void onPageSelected(int position) {
                                    Log.i("GridView", "onPageSelected:" + position);
//                            if (tv_number_indicator != null) {
//                                tv_number_indicator.setText((position + 1) + "/" + MNImageBrowser.getImageList().size());
//                            }
                                }
                            })
                            //显示：传入当前View
                            .show(viewHolder.mImg);
                }
            });
        }
    }

    public List<String> getImgList() {
        return mImgList;
    }

    public void setImgList(List<String> mImgList) {
        this.mImgList = mImgList;
    }
}
