package com.uniah.mobile.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.simplezxing.Hepler.QRCodeHelper;
import com.uniah.mobile.util.UniCookie;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;
import com.uniah.mobile.view.UniRadiusView;

public class UserQRCodeActivity extends AppCompatActivity {
    private UniCommonTopBar mTopBar;
    private UniRadiusView mHead;
    private TextView mNick;
    private TextView mFlag;
    private ImageView mImg;
    private int mUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_qrcode);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mHead = findViewById(R.id.user_qrcode_head);
        mNick = findViewById(R.id.user_qrcode_nick);
        mFlag = findViewById(R.id.user_qrcode_flag);
        mImg = findViewById(R.id.user_qrcode_img);
    }

    private void initData() {
        UniImageHelper.displayImage(this, UniCookie.getString(this, "userHead"), mHead);
        mNick.setText(UniCookie.getString(this, "userNick"));
        mUserId = UniCookie.getInt(this, "userId", -1);
    }

    public void initTopBar() {
        mTopBar.setTitle("我的二维码");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setShadow(0, 0);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doCreateQrCode() {
        //if (mUserId != -1) {
        final String text = "http://uniah.com/user/" + 10001;
        final Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        final QRCodeHelper.Builder builder = new QRCodeHelper.Builder();
        builder.setText(text);
        builder.setSize(mImg.getWidth(), mImg.getHeight());
        builder.setLogoBitmap(logo);

        final Bitmap bitmap = builder.bulid().createQrCodeBitmap();
        if (bitmap != null) {
            mImg.setImageBitmap(bitmap);
        }
        //}
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            doCreateQrCode();
        }
    }
}
