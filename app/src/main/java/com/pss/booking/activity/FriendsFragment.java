package com.pss.booking.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pss.booking.R;
import com.pss.booking.Utils.DateUtils;
import com.pss.booking.customview.ArrowDirection;
import com.pss.booking.customview.BubbleLayout;
import com.pss.booking.customview.BubblePopupHelper;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import solar.blaz.date.week.WeekDatePicker;


/**
 * Created by Ravi on 29/07/15.
 */
public class FriendsFragment extends Fragment {

    BubbleLayout bubbleLayout;
    // Declare Variables
    private WeekDatePicker datePicker;
    private Button clickButton;
    private PopupWindow popupWindow;
    View.OnClickListener onclcik = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int[] location = new int[2];
            v.getLocationInWindow(location);
            bubbleLayout.setArrowDirection(ArrowDirection.TOP);
            popupWindow.showAtLocation(v, Gravity.TOP | Gravity.LEFT, 46, v.getHeight() + location[1]);
        }
    };
    private TextView mMonthName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        clickButton = (Button) rootView.findViewById(R.id.buttonCalendar);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputDate = simpleDateFormat.format(calendar.getTime());
        Log.e("First Day == > ", outputDate);


        bubbleLayout = (BubbleLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_sample_popup, null);
        popupWindow = BubblePopupHelper.create(getActivity(), bubbleLayout);

        datePicker = (WeekDatePicker) bubbleLayout.findViewById(R.id.date_picker);
        mMonthName = (TextView) bubbleLayout.findViewById(R.id.dateHeader);
        datePicker.setDateIndicator(LocalDate.now().plusDays(0), true);
        datePicker.setLimits(LocalDate.now().minusWeeks(0), null);
        datePicker.setOnDateSelectedListener(new WeekDatePicker.OnDateSelected() {
            @Override
            public void onDateSelected(LocalDate date) {
                Toast.makeText(getActivity(), "" + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        datePicker.setOnWeekChangedListener(new WeekDatePicker.OnWeekChanged() {
            @Override
            public void onItemSelected(LocalDate firstDay) {


                Log.e("OnChanged ======>", firstDay.toString());
                Log.e("firstday ======>", DateUtils.onConvert(firstDay.toString()));
                mMonthName.setText(DateUtils.onConvert(firstDay.toString()));
                //DateUtils.onConvert(firstDay.toString());
            }
        });

        clickButton.setOnClickListener(onclcik);
        return rootView;
    }
}
