package com.pss.booking.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Manu on 9/9/2016.
 */
public class DimensUtils {

    private DimensUtils() {
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Converts a dp value to a px value
     *
     * @param context The context
     * @param dp      the dp value
     * @return value in pixels
     */
    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    /**
     * Converts pixel in dp
     *
     * @param context The context
     * @param px      the pixel value
     * @return value in dp
     */
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5);
    }

    /**
     * Convertes pixels to sp
     */
    public static float pxToSp(Context context, Float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }
//    public static float dpToPx(Context context, float dp) {
//        if (context == null) {
//            return -1;
//        }
//        return dp * context.getResources().getDisplayMetrics().density;
//    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpCeilInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    /**
     * Converts a dp value to a px value
     *
     * @param context The context
     * @param dp the dp value
     * @return value in pixels
     */
//    public static int dpToPx(Context context, float dp) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        return (int) ((dp * displayMetrics.density) + 0.5);
//    }
//
//    /**
//     * Converts pixel in dp
//     *
//     * @param context The context
//     * @param px the pixel value
//     * @return  value in dp
//     */
//    public static int pxToDp(Context context, int px) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        return (int) ((px / displayMetrics.density) + 0.5);
//    }
//
//    /**
//     * Convertes pixels to sp
//     */
//    public static float pxToSp(Context context, Float px) {
//        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
//        return px / scaledDensity;
//    }


    /**
     * Dp to pixels
     *
     * @param context the context
     * @param dp      the dp
     * @return the int
     */
    public static int dpToPixels(Activity context, int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) ((dp * metrics.density) + 0.5);
    }

    /**
     * Pixels to dp int.
     *
     * @param context the context
     * @param px      the px
     * @return the int
     */
    public static int pixelsToDp(Activity context, int px) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) ((px / metrics.density) + 0.5);
    }

    /**
     * Sp to px
     *
     * @param context the context
     * @param sp      the sp
     * @return the int
     */
    public static int spToPx(Context context, float sp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    /**
     * Px to sp
     *
     * @param context the context
     * @param px      the px
     * @return the int
     */
    public static int pxToSp(Context context, float px) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }
}
