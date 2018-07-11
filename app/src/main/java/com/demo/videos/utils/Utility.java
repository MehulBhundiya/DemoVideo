package com.demo.videos.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable.ShaderFactory;
import android.graphics.drawable.shapes.RectShape;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.demo.videos.R;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utility {
    public static final String BITLY = "http://bit.ly/GujjuTubeApp";
    public static final String DEVELOPER_KEY = "AIzaSyA2mU3EFsofpgVZ29bWGSsxD9GDI8DdiUI";
    public static final String HOME_STATIC_ID = "-1";
    public static final int LANGUAGE_ENGLISH = 2;
    public static final int LANGUAGE_GUJARATI = 1;
    public static final int LIMIT = 20;
    public static final int MASTER_VIEW_TYPE_MOVIE = 4;
    public static final int MASTER_VIEW_TYPE_PLAY_LIST = 5;
    public static final int MASTER_VIEW_TYPE_SINGER = 3;
    public static final int MASTER_VIEW_TYPE_SLIDER = 1;
    public static final int MASTER_VIEW_TYPE_SONGS = 2;
    public static final int OVERFLO_MENU_ID_SHARE = 2;
    public static final int OVERFLO_MENU_ID_WATCH_LATER = 1;
    public static final String TYPE_CAST = "cast";
    public static final String TYPE_PLAY_LIST = "playlists";
    public static final String TYPE_SINGERS = "singers";
    public static final String TYPE_SLIDER = "slider";
    public static final String TYPE_VIDEOS = "videos";
    public static final int VIEW_TYPE_LIST = 1;
    public static final int VIEW_TYPE_LOADING = 2;
    private static final String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|" +
            "watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%‌​2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    public static Typeface font = null;
    public static Typeface tf;
    private static Toast toast;

    public static String getVideoId(String str) {
        String str2 = null;
        if (str != null && str.trim().length() > 0) {
            Matcher matcher = Pattern.compile(expression).matcher(str);
            try {
                if (matcher.find()) {
                    str2 = matcher.group();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int convertDpToPixel(int i, Context context) {
        return (int) ((((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f) * ((float) i));
    }

    public static int convertPixelsToDp(int i, Context context) {
        return (int) (((float) i) / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public static int convertSpToPixels(int i, Context context) {
        return (int) TypedValue.applyDimension(2, (float) i, context.getResources().getDisplayMetrics());
    }

    public static int getClickableBG(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int getClickableBGBorderLessIfGreaterThen21(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(VERSION.SDK_INT >= 21 ? new int[]{R.attr.selectableItemBackgroundBorderless} : new int[]{R.attr.selectableItemBackground});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean z = connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getDateRegularForm(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
        try {
            str = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(simpleDateFormat.parse(str));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Drawable getColorDrawableBg(Context context, int i) {
        final int[] iArr = new int[]{ContextCompat.getColor(context, R.color.one), ContextCompat.getColor(context, R.color.two)};
        ShaderFactory anonymousClass1 = new ShaderFactory() {
            public Shader resize(int i, int i2) {
                return new LinearGradient(0.0f, 0.0f, (float) i, (float) i2, iArr, new float[]{0.1f, 1.0f}, TileMode.REPEAT);
            }
        };
        int convertDpToPixel = convertDpToPixel(i, context);
        PaintDrawable paintDrawable = new PaintDrawable();
        paintDrawable.setShape(new RectShape());
        paintDrawable.setCornerRadii(new float[]{(float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel, (float) convertDpToPixel});
        paintDrawable.setShaderFactory(anonymousClass1);
        return paintDrawable;
    }

    private static NavigableMap<Long, String> getSuffixes() {
        NavigableMap<Long, String> treeMap = new TreeMap();
        treeMap.put(Long.valueOf(1000), "K");
        treeMap.put(Long.valueOf(1000000), "M");
        treeMap.put(Long.valueOf(1000000000), "B");
        treeMap.put(Long.valueOf(1000000000000L), "T");
        treeMap.put(Long.valueOf(1000000000000000L), "P");
        treeMap.put(Long.valueOf(1000000000000000000L), "E");
        return treeMap;
    }

    public static String coolFormat(long j) {
        NavigableMap suffixes = getSuffixes();
        if (j == Long.MIN_VALUE) {
            return coolFormat(-9223372036854775807L);
        }
        if (j < 0) {
            return "-" + coolFormat(-j);
        }
        if (j < 1000) {
            return Long.toString(j);
        }
        Entry floorEntry = suffixes.floorEntry(Long.valueOf(j));
        Long l = (Long) floorEntry.getKey();
        String str = (String) floorEntry.getValue();
        long longValue = j / (l.longValue() / 10);
        Object obj = (longValue >= 100 || ((double) longValue) / 10.0d == ((double) (longValue / 10))) ? null : 1;
        return obj != null ? (((double) longValue) / 10.0d) + str : (longValue / 10) + str;
    }

    public static void showToast(Context context, String str) {
        try {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            toast.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

//    public static void showSnackBar(Context context, String str) {
//        try {
//            if (context instanceof Activity) {
//                if (tf == null) {
//                    tf = ResourcesCompat.getFont(context, R.font.muktavani_medium);
//                }
//                Snackbar make = Snackbar.make(((Activity) context).findViewById(16908290), str, -1);
//                ((TextView) ((SnackbarLayout) make.getView()).findViewById(R.id.snackbar_text)).setTypeface(tf);
//                make.show();
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void shareApp(Context context) {
//        context.getPackageName();
//        Intent intent = new Intent("android.intent.action.SEND");
//        intent.setType(HTTP.PLAIN_TEXT_TYPE);
//        intent.putExtra("android.intent.extra.SUBJECT", LanguagesStrings.SHARE_SUBJECT);
//        String str = "android.intent.extra.TEXT";
//        intent.putExtra(str, (LanguagesStrings.SHARE_TEXT + "\n") + BITLY);
//        context.startActivity(Intent.createChooser(intent, "choose one"));
//    }
//
//    public static void rateApp(Context context) {
//        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
//        if (VERSION.SDK_INT >= 21) {
//            intent.addFlags(1208483840);
//        } else {
//            intent.addFlags(1476427776);
//        }
//        try {
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
//        }
//    }
//
//    public static void shareContentVideo(Context context, ModelSongs modelSongs) {
//        try {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.SEND");
//            intent.putExtra("android.intent.extra.TEXT", modelSongs.name + "-\n" + modelSongs.youtubeUrl + "\n\n" + LanguagesStrings.SHARE_CONTENT_TEXT + "\n" + BITLY);
//            intent.setType(HTTP.PLAIN_TEXT_TYPE);
//            context.startActivity(intent);
//        } catch (Throwable e) {
//            ThrowableExtension.printStackTrace(e);
//        }
//    }
//
//    public static SpannableString typeface(Context context, CharSequence charSequence) {
//        SpannableString spannableString = new SpannableString(charSequence);
//        spannableString.setSpan(new CustomTypefaceSpan("", getMediumTypeface(context)), 0, spannableString.length(), 33);
//        return spannableString;
//    }
//
//    public static Typeface getMediumTypeface(Context context) {
//        if (font == null) {
//            font = ResourcesCompat.getFont(context, R.font.muktavani_medium);
//        }
//        return font;
//    }
//
//    public static void showPopupOnVideoItem(Context context, View view, int i, ModelSongs modelSongs, OnAdapterListener onAdapterListener) {
//        CharSequence spannableStringBuilder;
//        if (customTypefaceSpan == null) {
//            customTypefaceSpan = new CustomTypefaceSpan("", getMediumTypeface(context));
//        }
//        PopupMenu popupMenu = new PopupMenu(context, view, GravityCompat.END);
//        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
//        if (databaseHandler.checkIfWatchLater(modelSongs.id)) {
//            spannableStringBuilder = new SpannableStringBuilder(LanguagesStrings.POPUP_PACHI_VIDEO_JUVO_MATHI_DUR_KARO);
//            spannableStringBuilder.setSpan(customTypefaceSpan, 0, spannableStringBuilder.length(), 0);
//            popupMenu.getMenu().add(0, 1, 0, spannableStringBuilder);
//        } else {
//            spannableStringBuilder = new SpannableStringBuilder(LanguagesStrings.POPUP_PACHI_VIDEO_JUVO_MA_ADD_KARO);
//            spannableStringBuilder.setSpan(customTypefaceSpan, 0, spannableStringBuilder.length(), 0);
//            popupMenu.getMenu().add(0, 1, 0, spannableStringBuilder);
//        }
//        spannableStringBuilder = new SpannableStringBuilder(LanguagesStrings.POPUP_SHARE_KARO);
//        spannableStringBuilder.setSpan(customTypefaceSpan, 0, spannableStringBuilder.length(), 0);
//        popupMenu.getMenu().add(0, 2, 2, spannableStringBuilder);
//        final ModelSongs modelSongs2 = modelSongs;
//        final Context context2 = context;
//        final OnAdapterListener onAdapterListener2 = onAdapterListener;
//        final int i2 = i;
//        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                if (menuItem.getItemId() != 1) {
//                    Utility.shareContentVideo(context2, modelSongs2);
//                } else if (databaseHandler.checkIfWatchLater(modelSongs2.id)) {
//                    databaseHandler.deleteWatchLater(modelSongs2.id);
//                    Utility.showSnackBar(context2, LanguagesStrings.MESSAGE_PACHI_VIDEO_JUVO_DUR_KARYU);
//                    if (onAdapterListener2 != null) {
//                        onAdapterListener2.onRemovedFromWatchLater(i2);
//                    }
//                } else {
//                    databaseHandler.addToWatchLater(modelSongs2);
//                    Utility.showSnackBar(context2, LanguagesStrings.MESSAGE_PACHI_VIDEO_JUVO);
//                }
//                return true;
//            }
//        });
//        popupMenu.show();
//    }
//
//    public static void showLanguageChangeDialog(final Activity activity, final PrefManager prefManager, boolean z) {
//        int i = 1;
//        String str = "ગુજરાતી";
//        str = "English";
//        CharSequence typeface = typeface(activity, LanguagesStrings.CHANGE_LANGUAGE_DIALOG_TITLE);
//        if (z) {
//            typeface = typeface(activity, "એપ ની ભાષા પસંદ કરો");
//        }
//        Builder positiveText = new Builder(activity).title(typeface).customView(R.layout.change_language_dialog, true).autoDismiss(false).positiveText(typeface(activity, LanguagesStrings.CHANGE_LANGUAGE_DIALOG_BTN_CHANGE));
//        MaterialDialog build = positiveText.build();
//        View customView = build.getCustomView();
//        build.getActionButton(DialogAction.POSITIVE).setTextSize(16.0f);
//        int convertDpToPixel = convertDpToPixel(24, activity);
//        build.findViewById(R.id.md_titleFrame).setPadding(convertDpToPixel, convertDpToPixel, convertDpToPixel, 0);
//        build.findViewById(R.id.md_customViewFrame);
//        final RadioGroup radioGroup = (RadioGroup) customView.findViewById(R.id.radioGroupChangeLanguage);
//        if (z) {
//            customView.findViewById(R.id.txtChangeLanguageMessage).setVisibility(0);
//        } else {
//            customView.findViewById(R.id.txtChangeLanguageMessage).setVisibility(8);
//        }
//        int convertDpToPixel2 = convertDpToPixel(5, activity);
//        View radioButton = new RadioButton(activity);
//        radioButton.setPadding(convertDpToPixel2, convertDpToPixel2 * 2, convertDpToPixel2, convertDpToPixel2 * 2);
//        radioButton.setId(1);
//        radioButton.setTextSize(16.0f);
//        radioButton.setText("ગુજરાતી");
//        radioButton.setTextColor(ContextCompat.getColor(activity, R.color.black));
//        radioButton.setTypeface(getMediumTypeface(activity));
//        radioButton.setLayoutParams(new LayoutParams(-1, -2));
//        radioGroup.addView(radioButton);
//        radioButton = new RadioButton(activity);
//        radioButton.setPadding(convertDpToPixel2, convertDpToPixel2 * 2, convertDpToPixel2, convertDpToPixel2 * 2);
//        radioButton.setId(2);
//        radioButton.setTextSize(16.0f);
//        radioButton.setText("English");
//        radioButton.setTextColor(ContextCompat.getColor(activity, R.color.black));
//        radioButton.setTypeface(getMediumTypeface(activity));
//        radioButton.setLayoutParams(new LayoutParams(-1, -2));
//        radioGroup.addView(radioButton);
//        if (prefManager.getLanguage() != 1) {
//            i = 2;
//        }
//        radioGroup.check(i);
//        positiveText.onPositive(new SingleButtonCallback() {
//            public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
//                materialDialog.cancel();
//                if (radioGroup.getCheckedRadioButtonId() != i) {
//                    if (prefManager.getLanguage() == 2) {
//                        prefManager.setLanguage(1);
//                    } else {
//                        prefManager.setLanguage(2);
//                    }
//                    activity.finishAffinity();
//                    activity.startActivity(new Intent(activity, SplashScreenActivity.class));
//                }
//            }
//        });
//        build.show();
//    }
}
