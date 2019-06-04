package com.uniah.mobile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.uniah.mobile.R;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Stark on 2017/2/10.
 */
public class UniListDialog extends Dialog {

    public UniListDialog(Context context, int theme) {
        super(context, theme);
    }

    public UniListDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;
        private String title;

        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;

        private CharSequence[] mItems;
        private String[] mItemColors;
        private DialogInterface.OnClickListener mOnClickListener;
        private int mCheckedItem = -1;

        private boolean cancelable;

        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setChoiceItems(CharSequence[] items, final OnClickListener listener) {
            mItems = items;
            mOnClickListener = listener;
            return this;
        }

        public UniListDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final UniListDialog dialog = new UniListDialog(context, R.style.NoFrameNoDim_Dialog);
            dialog.setCancelable(cancelable);
            if (cancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            View layout = inflater.inflate(R.layout.dialog_list, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            TextView titleView = (TextView) layout.findViewById(R.id.title);
            if (title != null) {
                titleView.setVisibility(View.VISIBLE);
                titleView.setText(title);
            } else {
                titleView.setVisibility(View.GONE);
            }
            // set the confirm button
            Button positiveButton = (Button) layout.findViewById(R.id.positiveButton);
            View midLine = layout.findViewById(R.id.mid_line);
            if (positiveButtonText != null) {
                positiveButton.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                positiveButton.setVisibility(View.GONE);
                midLine.setVisibility(View.GONE);
            }

            // set the cancel button
            Button negativeButton = (Button) layout.findViewById(R.id.negativeButton);
            if (negativeButtonText != null) {
                negativeButton.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                negativeButton.setVisibility(View.GONE);
                midLine.setVisibility(View.GONE);
            }

            View topLine = layout.findViewById(R.id.top_line);
            topLine.setVisibility(positiveButtonText == null && negativeButtonText == null ? View.GONE : View.VISIBLE);

            // set the content message
            if (mItems != null) {
                final ListView listView = (ListView) layout.findViewById(R.id.listView);
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                final ListAdapter adapter;
                if (mItemColors != null) {
                    adapter = new CheckedItemAdapter(context, R.layout.dialog_list_item, mItems, mItemColors);
                } else {
                    adapter = new CheckedItemAdapter(context, R.layout.dialog_list_item, mItems);
                }
                listView.setAdapter(adapter);
                listView.setItemChecked(mCheckedItem, true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        if (mOnClickListener != null) {
                            mOnClickListener.onClick(dialog, position);
                            dialog.dismiss();
                        }
                    }
                });
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((FrameLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((FrameLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }

        public UniListDialog show() {
            final UniListDialog dialog = create();
            dialog.show();
            return dialog;
        }

        private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
            Context context;
            List<String> colors;

            public CheckedItemAdapter(Context context, int resource, CharSequence[] objects) {
                super(context, resource, objects);
                this.context = context;
            }

            public CheckedItemAdapter(Context context, int resource, CharSequence[] objects, String[] colors) {
                super(context, resource, objects);
                this.context = context;
                this.colors = Arrays.asList(colors);
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup viewGroup) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.dialog_list_item, null);
                }
                TextView textView = convertView.findViewById(R.id.dialog_list_text);
                textView.setText(String.valueOf(getItem(position)));
                if (colors != null) {
                    textView.setTextColor(Color.parseColor(colors.get(position)));
                }
                return convertView;
            }
        }
    }
}
