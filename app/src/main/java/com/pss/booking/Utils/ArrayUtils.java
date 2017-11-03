package com.pss.booking.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mdev3 on 10/13/16.
 */
public class ArrayUtils {

    public static ArrayList<String> getArrivalTime() {
        String[] arrivalArray = {"1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 AM",
                "1 PM", "2 PM", "3 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM", "12 PM"};
        return new ArrayList<String>(Arrays.asList(arrivalArray));
    }

    public static ArrayList<String> getLengthOfStay() {
        String[] lengthStayArray = {"1H", "2H", "3H", "4H", "5H", "Night"};
        return new ArrayList<String>(Arrays.asList(lengthStayArray));
    }

    public static ArrayList<String> getLengthBudget() {
        String[] budgetArray = {"10$", "20$", "50$", "100$", "200$"};
        return new ArrayList<String>(Arrays.asList(budgetArray));
    }

    public static ArrayList<String> getLengthRating() {
        String[] ratingArray = {"1*", "2*", "3*", "4*", "5*"};
        return new ArrayList<String>(Arrays.asList(ratingArray));
    }

}
