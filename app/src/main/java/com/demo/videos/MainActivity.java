package com.demo.videos;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.videos.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
