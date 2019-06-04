package com.uniah.mobile.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.AddFriendActivity;
import com.uniah.mobile.activity.MainActivity;
import com.uniah.mobile.activity.SetFeedbackActivity;
import com.uniah.mobile.activity.WebViewActivity;
import com.uniah.mobile.bean.MoreDialogData;
import com.uniah.mobile.dialog.MoreDialog;
import com.uniah.mobile.simplezxing.activity.CaptureActivity;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniSiteHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class BaseFragment extends Fragment {

    private static final int REQ_CODE_PERMISSION = 0x1111;
    private MoreDialog mMoreDialog = null;
    private boolean isShowDialog = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UniDisplayHelper.setCustomDensity(getActivity(), getActivity().getApplication());
    }

    protected List<BaseData> getMoreDialogList(MoreDialog dialog) {
        mMoreDialog = dialog;
        isShowDialog = true;
        List<BaseData> list = new ArrayList<>();

        MoreDialogData item1 = new MoreDialogData();
        item1.setIconId(R.drawable.ic_scan);
        item1.setText("扫一扫");
        item1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Scan Activity
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Do not have the permission of camera, request it.
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
                } else {
                    // Have gotten the permission
                    startCaptureActivityForResult();
                }
                isShowDialog = false;
            }
        });
        list.add(item1);

        MoreDialogData item2 = new MoreDialogData();
        item2.setIconId(R.drawable.ic_add_friend_theme);
        item2.setText("找朋友");
        item2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                startActivity(intent);
                isShowDialog = false;
            }
        });
        list.add(item2);

        MoreDialogData item3 = new MoreDialogData();
        item3.setIconId(R.drawable.ic_create_team_theme);
        item3.setText("创组织");
        item3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowDialog = false;
            }
        });
        list.add(item3);

        MoreDialogData item4 = new MoreDialogData();
        item4.setIconId(R.drawable.ic_feedback);
        item4.setText("提建议");
        item4.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetFeedbackActivity.class);
                startActivity(intent);
                isShowDialog = false;
            }
        });
        list.add(item4);

        return list;
    }

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // User agree the permission
                    startCaptureActivityForResult();
                } else {
                    // User disagree the permission
                    Toast.makeText(getActivity(), "You must agree the camera permission request before you use the code scan function", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                String result = null;
                switch (resultCode) {
                    case RESULT_OK:
                        result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);  //or do sth
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            // for some reason camera is not working correctly
                            result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                        }
                        break;
                }
                if (UniSiteHelper.isValidUrl(result)) {
                    String localUrl = "http://uniah.com/user/";
                    if (result.startsWith(localUrl)) {

                        Toast.makeText(getActivity(), "id=" + result.replace(localUrl, ""), Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getActivity(), UserHomeActivity.class);
//                        intent.putExtra("userId", result.replace(localUrl, ""));
//                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", result);
                        startActivity(intent);
                    }
                } else if (result != null) {
                    Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMoreDialog != null && !isShowDialog) {
            mMoreDialog.dismiss();
        }
    }
}
