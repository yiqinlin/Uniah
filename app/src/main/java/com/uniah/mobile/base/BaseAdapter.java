/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uniah.mobile.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uniah.mobile.util.UniDisplayHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mData;
    public final Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public BaseAdapter(Context context, List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View convertView = mInflater.inflate(getLayoutId(position), parent, false);
        final BaseViewHolder holder = getViewHolder(position, convertView);
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, position, mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setBottomMargin(BaseViewHolder holder, int position) {
        this.setBottomMargin(holder, position, 12);
    }

    private void setBottomMargin(BaseViewHolder holder, int position, int dpValue) {
        if (position == getItemCount() - 1) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), params.bottomMargin + UniDisplayHelper.dp2px(mContext, dpValue));
        }
    }

    private BaseViewHolder getViewHolder(int position, View convertView) {
        BaseData data = (BaseData) getItem(position);
        return data.getViewHolder(mContext, convertView);
    }

    private int getLayoutId(int position) {
        BaseData data = (BaseData) getItem(position);
        return (data != null) ? data.getLayoutId() : -1;
    }

    public abstract void convert(BaseViewHolder holder, int position, T item);

    public void setData(List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        notifyDataSetChanged();
    }

    public T getItem(int pos) {
        return mData.get(pos);
    }

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
        notifyItemInserted(pos);
    }

    public void notifyItemChangedWithObject(Object object) {
        int index = 0;
        for (Object data : mData) {
            if (data == object) {
                notifyItemChanged(index);
            }
            index++;
        }
    }

    public void remove(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int pos);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int pos);
    }
}
