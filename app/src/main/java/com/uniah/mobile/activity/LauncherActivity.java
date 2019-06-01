package com.uniah.mobile.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uniah.mobile.R;
import com.uniah.mobile.dialog.UniDialog;
import com.uniah.mobile.util.UniCookie;

public class LauncherActivity extends Activity {

    private int time = 0;
    private Button skip;
    private static int MY_PERMISSIONS_REQUEST_CAMERA_GALLERY = 4;
    private static int GOTO_APP_SETTING = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        skip = (Button) findViewById(R.id.start_page_btn_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.removeCallbacks(Count);
                turnTo();
            }
        });
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&& ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限用于缓存图片", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA_GALLERY);
        }else{
            handle.postDelayed(Count,0);
        }
    }

    private void turnTo() {
        handle.removeCallbacks(Count);
        Class<?> temp = LoginActivity.class;
        if (UniCookie.getBoolean(LauncherActivity.this, "login", true)) {
            temp = MainActivity.class;
        }
        Intent intent = new Intent(LauncherActivity.this, temp);
        startActivity(intent);
        finish();
    }

    Handler handle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handle.removeCallbacks(Count);
            return false;
        }
    });

    Runnable Count = new Runnable() {
        @Override
        public void run() {
            if (time == 0) {
                turnTo();
                return;
            }
            if (skip != null) {
                skip.setText(String.valueOf("跳过" + time--));
            }
            handle.postDelayed(Count, 1000);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOTO_APP_SETTING) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    PermissionRequest();
                } else {
                    handle.postDelayed(Count,0);
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== MY_PERMISSIONS_REQUEST_CAMERA_GALLERY){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handle.postDelayed(Count,0);
                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
            } else {
                PermissionRequest();
            }
        }
    }
    private void PermissionRequest() {
        UniDialog dialog = new UniDialog.Builder(this)
                .setTitle("权限不可用")
                .setMessage("缓存图片需要文件权限，请在-应用设置-权限中，获取权限。")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                }).setNegativeButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, GOTO_APP_SETTING);
                    }
                }).setCancelable(false).show();
    }
}
