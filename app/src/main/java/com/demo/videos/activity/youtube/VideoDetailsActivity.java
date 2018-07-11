package com.demo.videos.activity.youtube;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

import com.demo.videos.R;
import com.demo.videos.activity.BaseActivity;
import com.demo.videos.utils.Utility;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class VideoDetailsActivity extends BaseActivity {
    public static final String EXTRA_MODEL_SEARCH = "model_search";
    public static final String EXTRA_MODEL_SONGS = "model";
    OrientationEventListener A;
    private YouTubePlayer yPlayer = null;
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    PlayerStateChangeListener z = new PlayerStateChangeListener() {
        public void onLoading() {
        }

        public void onLoaded(String str) {
        }

        public void onAdStarted() {
        }

        public void onVideoStarted() {
            Log.d("MyVideo", "started");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }

        public void onVideoEnded() {
            Log.d("MyVideo", "onVideoEnded");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            if (A != null && A.canDetectOrientation()) {
                try {
                    A.disable();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        public void onError(ErrorReason errorReason) {
        }
    };

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 1) {
            if (this.yPlayer != null) {
                this.yPlayer.setFullscreen(false);
                Log.d("MyFullScreenMode", "False");
            }
        } else if (configuration.orientation == 2 && this.yPlayer != null) {
            this.yPlayer.setFullscreen(true);
            Log.d("MyFullScreenMode", "true");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            setContentView(R.layout.activity_video_details);
            initData();
        } catch (Throwable e) {
            e.printStackTrace();
            finish();
        }
    }

    private void initData() {
        initYoutubeFragment();
    }


    private void initYoutubeFragment() {
        Log.d("videoId:::", "" + Utility.getVideoId("https://www.youtube.com/watch?v=EkpyZYwjzvk"));
        this.youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        this.youTubePlayerFragment.initialize(Utility.DEVELOPER_KEY, new OnInitializedListener() {
            public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("videoId:::", "" + b);
                if (!b) {
                    yPlayer = youTubePlayer;
                    yPlayer.loadVideo(Utility.getVideoId("https://www.youtube.com/watch?v=EkpyZYwjzvk"));
                    yPlayer.setPlayerStateChangeListener(z);
                    yPlayer.setPlayerStyle(PlayerStyle.DEFAULT);
                }
            }

            public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youTubeInitializationResult.getErrorDialog(VideoDetailsActivity.this, 0).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    private boolean isPortrait(int i) {
        if (i < 45 || i > 315) {
            return true;
        }
        return false;
    }

    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == 2) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        if (this.yPlayer != null) {
            this.yPlayer.release();
        }
        this.yPlayer = null;
        super.onDestroy();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public Bitmap resizeImage(int i) {
        double width = (double) getWindowManager().getDefaultDisplay().getWidth();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(i);
        return getResizedBitmap(BitmapFactory.decodeResource(getResources(), i), (int) ((width / ((double) bitmapDrawable.getBitmap().getWidth())) *
                ((double) bitmapDrawable.getBitmap().getHeight())), (int) width);
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i2) / ((float) width);
        float f2 = ((float) i) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }
}
