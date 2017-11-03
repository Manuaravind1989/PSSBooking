package com.pss.booking.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.adapter.DialListAdapter;
import com.pss.booking.adapter.DialogListAdapter;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.model.DialCodeModel;

import java.util.ArrayList;


/**
 * Created by Manu on 9/9/2016.
 */
public class DialogUtils {


        public void okFunc(Context context, String message, String title, final OnDialogSelectedListener mOnDialogSelectedListener,final int dialogIndex) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ok_dialog_layout);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        /* My Cancel Button , and its listener */
        TextView mTitleView = (TextView) dialog.findViewById(R.id.ok_dialog_title);
        mTitleView.setText(title);
        TextView mMessageView = (TextView) dialog.findViewById(R.id.ok_dialog_message);
        mMessageView.setText(message);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDialogSelectedListener.onDialogClick(DialogConstants.DIALOG_BUTTON_OK, null,dialogIndex);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void doubleFunc(Context context, String message, String title, final OnDialogSelectedListener mOnDialogSelectedListener,final int dialogIndex) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.double_dialog_layout);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        /* My Cancel Button , and its listener */
        TextView mTitleView = (TextView) dialog.findViewById(R.id.ok_dialog_title);
        mTitleView.setText(title);
        TextView mMessageView = (TextView) dialog.findViewById(R.id.ok_dialog_message);
        mMessageView.setText(message);
        Button yesButton = (Button) dialog.findViewById(R.id.YesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDialogSelectedListener.onDialogClick(DialogConstants.DIALOG_BUTTON_OK, null,dialogIndex);
                dialog.dismiss();
            }
        });
        Button NoButton = (Button) dialog.findViewById(R.id.NoButton);
        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void listFunc(Activity context, String title, final String[] mStringArrays, final OnDialogSelectedListener mOnDialogSelectedListener,final int dialogIndex) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = (int) (metrics.heightPixels * 0.6);
        final Dialog dialog = new Dialog(context, R.style.DialogSheet);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_layout);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, height);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        /* My Cancel Button , and its listener */
        TextView mTitleView = (TextView) dialog.findViewById(R.id.DialogTitle);
        mTitleView.setText(title);
//        TextView mMessageView = (TextView)dialog.findViewById(R.id.ok_dialog_message);
//        mMessageView.setText(message);
//        Button okButton = (Button) dialog.findViewById(R.id.okButton);
//        okButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnDialogSelectedListener.onDialogClick(DialogConstants.DIALOG_BUTTON_OK);
//                dialog.dismiss();
//            }
//        });

        ListView mListView = (ListView) dialog.findViewById(R.id.listView_dialog);
        DialogListAdapter adapter = new DialogListAdapter(context, mStringArrays);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                mOnDialogSelectedListener.onDialogClick(DialogConstants.DIALOG_BUTTON_LIST, mStringArrays[position],dialogIndex);
            }
        });
        dialog.show();
    }


    public void dialogDialCode(Activity context, final ArrayList<DialCodeModel> mArrayList, final OnDialogSelectedListener mOnDialogSelectedListener,final int dialogIndex) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = (int) (metrics.heightPixels * 0.6);
        final Dialog dialog = new Dialog(context, R.style.DialogSheet);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_layout);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, height);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        /* My Cancel Button , and its listener */
        TextView mTitleView = (TextView) dialog.findViewById(R.id.DialogTitle);
        mTitleView.setText("Select a Dial Code");
        ListView mListView = (ListView) dialog.findViewById(R.id.listView_dialog);
        DialListAdapter adapter = new DialListAdapter(context, mArrayList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                mOnDialogSelectedListener.onDialogClick(DialogConstants.DIALOG_DIAL_LIST, mArrayList.get(position),dialogIndex);
            }
        });
        dialog.show();
    }

    public static void showAlert(Context context, String message) {
        final Dialog dialog = new Dialog(context);
        //dialog.getWindow().setWindowAnimations(R.style.DropVertical);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_box);

        // set the custom dialog components - text, image and button

        TextView messageTV = (TextView) dialog.findViewById(R.id.alert_single_message);
        messageTV.setText(message);

        Button okButton = (Button) dialog.findViewById(R.id.okBtn);
        // if button is clicked, close the custom dialogAlertCallback
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setDimAmount(0.7f);
        dialog.show();
    }
    public interface OnDialogSelectedListener {
        void onDialogClick(int selectedIndex, Object mObj,int dialogIndex);
    }
}
