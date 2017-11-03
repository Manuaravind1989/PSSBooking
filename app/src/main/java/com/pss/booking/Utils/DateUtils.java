package com.pss.booking.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Manu on 9/9/2016.
 */
public class DateUtils {

    public static String toDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");
        return dateFormat.format(date);
    }

    public static String toDateInYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String toWeek(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("E");
        return dateFormat.format(date);
    }

    public static String toWeek(Date date, int distance) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, distance);
        return toWeek(calendar.getTime());
    }

    //??????????,?????
    public static long getDistanceWeek(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            long week = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000 * 7);
            return week;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //???????
    public static String getTheDateInYear(Date date, int distance) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, distance);
        return toDateInYear(calendar.getTime());
    }

    //???????
    public static String getTheDate(Date date, int distance) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, distance);
        return toDate(calendar.getTime());
    }


//    public static List<String> getTheWeekDate(int weekDistance) {
//        List<String> dateInWeek = new ArrayList<String>();
//
////        Calendar calendar = Calendar.getInstance();
//        Date curDate = new Date();
//        String theWeek = toWeek(curDate);
//        int i = 0;
//        while(! theWeek.equals("??") ){
//            i --;
//            theWeek = toWeek(curDate,i);
//        }
//        for (int j = 0 ;j < 7 ;j ++){
//            dateInWeek.add(getTheDate(curDate,i + j + weekDistance * 7));
//        }
//        return dateInWeek;
//    }

    //???????????
    public static int getDayInWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 1) {
            w = 7;
        }
        return w;
    }

    //?????????,???????,??? weekDistance ?? ???????,?????0,???-1
    public static List<String> getTheWeekDate(int weekDistance) {
        List<String> dateInWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        //??????????
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 1) {
            w = 7;
        }
        for (int j = 0; j < 7; j++) {
            dateInWeek.add(getTheDate(date, j - w + 1 + weekDistance * 7));
        }
        return dateInWeek;
    }

    /**
     * ???? ??? "yyyy-MM-dd"?????? ???????? ?? true
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            return d1.after(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ??????????
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getSecondSpace(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        long diff = date2.getTime() - date1.getTime();
        return (diff % nd % nh % nm);
    }


    public static String onConvert(String dateStr) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
        fromFormat.setLenient(false);
        SimpleDateFormat toFormat = new SimpleDateFormat("MMM  yyyy");
        toFormat.setLenient(false);

        Date date = null;
        try {
            date = fromFormat.parse(dateStr);
        } catch (Exception e) {

        }
        System.out.println(toFormat.format(date));
        return toFormat.format(date);
    }

}
