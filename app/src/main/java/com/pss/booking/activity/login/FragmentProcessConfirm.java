package com.pss.booking.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.StringUtils;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.AddBookingModel;
import com.pss.booking.model.HotelModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Manu on 12/16/2016.
 */
public class FragmentProcessConfirm extends Fragment implements View.OnClickListener, DialogUtils.OnDialogSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, ServiceResponseListener {


    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    DialogUtils mDialogUtils;
    private Button mSubmitButton;
    HotelModel.PostsEntity mHotelDetails;
    NetworkManager networkManager;
    private Date fromcalendar;
    private Date tocalendar;

    private static final String TIME_PATTERN = "HH:mm";

    private TextView lblDate;
    private TextView rblDate;
    private TextView lblTime;
    private Calendar calendar;
    private DateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private EditText mNameTextbox;
    private EditText mMobileTextbox;
    int selectedindex = 0;
    UserPefer userPefer;
    private TextView mAmountLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        networkManager = new NetworkManager(mActivity, this);
        userPefer = new UserPefer(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_process_confirm, container, false);
        mSubmitButton = (Button) rootView.findViewById(R.id.SubmitButton);

        mSubmitButton.setOnClickListener(this);
        initUI();
        return rootView;
    }

    private void initUI() {
        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
        mNameTextbox = (EditText) rootView.findViewById(R.id.nameTextbox);
        mMobileTextbox = (EditText) rootView.findViewById(R.id.mobileTextbox);
        lblDate = (TextView) rootView.findViewById(R.id.DateFromLabel);
        rblDate = (TextView) rootView.findViewById(R.id.DatetoLabel);
        lblTime = (TextView) rootView.findViewById(R.id.ServiceTimeLabel);
        mAmountLabel = (TextView) rootView.findViewById(R.id.AmountLABEL);
        double discount = 0;
        if (!mHotelDetails.getDiscount().equals("")) {
            discount = Integer.valueOf(mHotelDetails.getDiscount());
        }

        if (discount > 0) {
            mAmountLabel.setText("" + getPercentageValue(Double.valueOf(mHotelDetails.getAmount()), Double.valueOf(mHotelDetails.getDiscount())));

        } else {
            mAmountLabel.setText(mHotelDetails.getAmount());

        }


        lblDate.setOnClickListener(this);
        rblDate.setOnClickListener(this);
        lblTime.setOnClickListener(this);
        update();
    }

    private double getPercentageValue(double oldAmount, double percent) {
        double newAmount = oldAmount - (oldAmount * percent / 100);
        return newAmount;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.SubmitButton) {
            if (isValidated()) {
                if(fromcalendar.after(tocalendar)){
                    Toast.makeText(getActivity(),"End Date should be greater than start Date", Toast.LENGTH_SHORT).show();
                }
                if(fromcalendar.before(tocalendar)){
                    networkManager.addBooking(userPefer.getUserId(), mHotelDetails.getAmount(), mHotelDetails.getDiscount(), mNameTextbox.getText().toString(), mMobileTextbox.getText().toString(),
                            lblTime.getText().toString(), lblDate.getText().toString(), rblDate.getText().toString());
                }
                if(fromcalendar.equals(tocalendar)){
                    System.out.println("Date1 is euql Date2");
                    Toast.makeText(getActivity(),"End Date should not equal to start Date", Toast.LENGTH_SHORT).show();
                }


            }
        } else if (view.getId() == R.id.ServiceTimeLabel) {
            TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show(mActivity.getFragmentManager(), "timePicker");

        } else if (view.getId() == R.id.DateFromLabel) {
            selectedindex = 1;
            DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(mActivity.getFragmentManager(), "datePicker");

        } else if (view.getId() == R.id.DatetoLabel) {
            selectedindex = 2;
            DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(mActivity.getFragmentManager(), "datePicker");

        }

    }

    private void update() {
        if (selectedindex == 1) {
            fromcalendar =calendar.getTime();
            lblDate.setText(dateFormat.format(calendar.getTime()));
        } else if (selectedindex == 2) {
            tocalendar =calendar.getTime();
            rblDate.setText(dateFormat.format(calendar.getTime()));
        }
        lblTime.setText(timeFormat.format(calendar.getTime()));
    }


    private void datePicekerListener() {
    }


    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        update();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        update();
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.ADD_BOOKING)) {

            AddBookingModel[] addBookingModelArray = (AddBookingModel[]) responseObject;
//                 Intent mIn = new Intent(getActivity(), ProceedBookingActivity.class);
//                  startActivity(mIn);
            mDialogUtils.okFunc(mActivity, "Booking successfully Added", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            mNameTextbox.setText("");
            mMobileTextbox.setText("");
            lblDate.setText("");
            rblDate.setText("");
        }
    }

    public void getData(Object obj) {
        mHotelDetails = (HotelModel.PostsEntity) obj;
    }

    private boolean isValidated() {

        if (StringUtils.isBlank(mNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Name", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mMobileTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Mobile", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(lblTime.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Select service time", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(lblDate.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Select start date ", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(rblDate.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter end date", "PSS", FragmentProcessConfirm.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }

        return true;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int dialogIndex) {

    }
}
