package com.demo.videos.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.demo.videos.R;

/**
 * Created by Creators Corp 9 on 1/3/2018.
 */

public class BaseActivity extends AppCompatActivity {

    private static BaseActivity instance;
    ProgressDialog dialog;
    public boolean showLockScreen = false;

    public static BaseActivity getInstance() {
        return instance;
    }

    protected int d() {
        TypedValue typedValue = new TypedValue();
        if (getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return 0;
    }

    public boolean isInternetOn() {
        try {
            NetworkInfo activeNetwork = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (activeNetwork == null || !activeNetwork.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    protected void onResume() {
        super.onResume();
//        if (PreferenceMan.getInstance().isUserLogin() &&
//                PreferenceMan.getInstance().isPinEnabled()
//                && !AEJewelApp.isScreenResume &&
//                !AEJewelApp.isFromApp) {
//            startActivity(new Intent(this, AppLockActivity.class));
//        } else {
//            AEJewelApp.isFromApp = false;
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        AEJewelApp.isScreenResume = false;
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        AEJewelApp.isScreenResume = powerManager.isScreenOn();
//    }

    public void showNetworkAlert() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_no_internet)
                .setMessage(R.string.title_internet_message)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
    }


    public String getVersionName() {
        String version = null;
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view == null) {
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void removeRequestFocus() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showProgressDialog(int messageID) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
            }
            if (!dialog.isShowing()) {
                dialog.setMessage(getString(messageID));
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    private BroadcastReceiver mLangaugeChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            Log.d("intent::::", "" + intent);
            startActivity(getIntent());
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLangaugeChangedReceiver != null) {
            try {
                unregisterReceiver(mLangaugeChangedReceiver);
                mLangaugeChangedReceiver = null;
            } catch (final Exception e) {
            }
        }
    }
}
