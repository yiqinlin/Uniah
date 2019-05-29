package com.uniah.mobile.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uniah.mobile.R;
import com.uniah.mobile.view.UniLoadView;
import com.uniah.mobile.view.UniRefreshView;
import com.uniah.mobile.widget.pullRefreshLayout.UniPullRefreshLayout;

public class UniPullLayout extends UniPullRefreshLayout {

    Context mContext;

    public UniPullLayout(Context context) {
        this(context, null);
    }

    public UniPullLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.UniPullRefreshLayoutStyle);
    }

    public UniPullLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }
    @Override
    protected View createRefreshView(Context context) {
        return new UniRefreshView(context);
    }

    @Override
    protected View createLoadView(Context context) {
        return new UniLoadView(context);
    }
}
