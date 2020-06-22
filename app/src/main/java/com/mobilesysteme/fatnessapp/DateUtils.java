package com.mobilesysteme.fatnessapp;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

    public static final long DAY_IN_MILLI_SECS = 86400000;
    public static final int WEEK_IN_MILLI_SECS = 604800000;
    public static final long YEAR_IN_MILLI_SECS = 31536000000L;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY);
    private static final DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);

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

    public static Date getTodayMorning() {

        long dateInMilliSecs = new Date().getTime();
        return new Date(dateInMilliSecs - dateInMilliSecs % DAY_IN_MILLI_SECS);
    }
}
