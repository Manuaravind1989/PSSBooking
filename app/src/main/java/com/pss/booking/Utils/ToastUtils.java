package com.pss.booking.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Manu on 9/9/2016.
 */
public class ToastUtils {

    private ToastUtils() {
        //Empty
    }

    public static void showError(final String message, final Context context) {
        getToast(message, context).show();
    }

    public static void showShortMessage(String message, Context context) {
        getToast(message, context, Toast.LENGTH_SHORT).show();
    }

    private static Toast getToast(String message, Context context) {
        return getToast(message, context, Toast.LENGTH_LONG);
    }

    private static Toast getToast(String message, Context context, int length) {
        return Toast.makeText(context, message, length);
    }


    /*****
     *
     *    public static void show(Context context, int resId) {
     show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
     }

     public static void show(Context context, int resId, int duration) {
     show(context, context.getResources().getText(resId), duration);
     }

     public static void show(Context context, CharSequence text) {
     show(context, text, Toast.LENGTH_SHORT);
     }

     public static void show(Context context, CharSequence text, int duration) {
     Toast.makeText(context, text, duration).show();
     }

     public static void show(Context context, int resId, Object... args) {
     show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
     }

     public static void show(Context context, String format, Object... args) {
     show(context, String.format(format, args), Toast.LENGTH_SHORT);
     }

     public static void show(Context context, int resId, int duration, Object... args) {
     show(context, String.format(context.getResources().getString(resId), args), duration);
     }

     public static void show(Context context, String format, int duration, Object... args) {
     show(context, String.format(format, args), duration);
     }
     }
     */
}

