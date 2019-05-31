package com.uniah.mobile.imagebrowserlibrary.listeners;

import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * desc   : 长按监听
 * version: 1.0
 */
public interface OnLongClickListener {

    void onLongClick(FragmentActivity activity, ImageView view, int position, String url);

}
