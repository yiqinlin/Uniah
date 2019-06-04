package com.uniah.mobile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.MoreDialogAdapter;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.util.UniDisplayHelper;

import java.util.ArrayList;
import java.util.List;

public class MoreDialog extends Dialog {

    private RecyclerView mRecyclerView;
    private boolean isAllowClose = true;
    private Context mContext;
    private MoreDialogAdapter mAdapter;

    public MoreDialog(Context context) {
        this(context, R.style.More_Dialog);
    }

    public MoreDialog(final Context context, int theme) {
        super(context, theme);
        this.mContext = context;

        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.TOP | Gravity.RIGHT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = UniDisplayHelper.dp2px(mContext, 32);
        dialogWindow.setAttributes(lp);

        mAdapter = new MoreDialogAdapter(mContext, new ArrayList<BaseData>());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_more);

        mRecyclerView = findViewById(R.id.dialog_more_recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setData(List<BaseData> list) {
        mAdapter.setData(list);
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    public MoreDialog setOnKeyDownClose(boolean isAllowClose) {
        this.isAllowClose = isAllowClose;
        return this;
    }

    public MoreDialog setOnTouchClose(boolean temp) {
        setCanceledOnTouchOutside(temp);
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isAllowClose) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
