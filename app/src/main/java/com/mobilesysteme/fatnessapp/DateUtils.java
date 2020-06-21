package com.mobilesysteme.fatnessapp;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

    public static final long DAY_IN_MILLI_SECS = 86400000;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private static final DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static Date getDateFromString(String date) {

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getSqlDateFromString(String date) {

        try {
            return sqlDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateAsString(Date date) {
        return dateFormat.format(date);
    }

    public static String getSqlDateAsString(Date date) {
        return sqlDateFormat.format(date);
    }

    /**
     * extracts a Date from the given DatePicker
     * @return the Date matching the picke Date within the DatePicker
     */
    public static Date getDateFromDatePicker(DatePicker datePicker) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(datePicker.getDayOfMonth())
                .append(".")
                .append(datePicker.getMonth() + 1)
                .append(".")
                .append(datePicker.getYear());

        return DateUtils.getDateFromString(stringBuilder.toString());
    }

    public static Date getNextDayMorning(Date date) {

        long dateInMilliSecs = date.getTime();
        return new Date(dateInMilliSecs + (DAY_IN_MILLI_SECS - dateInMilliSecs % DAY_IN_MILLI_SECS));
    }
}
