package com.uniah.mobile.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    private List<T> mData;

    public BaseListAdapter(Context context, List<T> dataList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = dataList;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        BaseData data = (BaseData) getItem(position);
        return (data != null) ? data.getLayoutId() : -1;
    }

    private BaseViewHolder getViewHolder(int position, View convertView) {
        BaseData data = (BaseData) getItem(position);
        return data.getViewHolder(mContext, convertView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getItemViewType(position), parent, false);
            viewHolder = getViewHolder(position, convertView);
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag();
        }
        convert(viewHolder, position, getItem(position));
        return convertView;
    }

    public abstract void convert(BaseViewHolder holder, int position, T item);

    public void add(T item) {
        add(mData.size(), item);
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
    }

    public void addAndNotify(T item) {
        addAndNotify(mData.size(), item);
    }

    public void addAndNotify(int pos, T item) {
        mData.add(pos, item);
        notifyDataSetChanged();
    }

    public void remove(int pos) {
        mData.remove(pos);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
