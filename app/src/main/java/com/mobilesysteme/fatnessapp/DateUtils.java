package com.mobilesysteme.fatnessapp;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A Utils class handling the parsing of Dates and containing different time units in shape of milli seconds
 * @author Hoffmann
 */
public abstract class DateUtils {

    public static final long DAY_IN_MILLI_SECS = 86400000;
    public static final int WEEK_IN_MILLI_SECS = 604800000;
    public static final long YEAR_IN_MILLI_SECS = 31536000000L;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY);
    private static final DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);

    /**
     * Extracts the date from a given String using the format dd/MM/yyyy
     * @param date the String to extract the date from
     * @return the extracted date
     */
    public static Date getDateFromString(String date) {

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts the date from a given String using the format yyyy-MM-dd HH:mm:ss
     * @param date the String to extract the date from
     * @return the extracted date
     */
    public static Date getSqlDateFromString(String date) {

        try {
            return sqlDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses the date to a String in format dd/MM/yyyy
     * @param date the date used to parse a String from
     * @return the String containing the date
     */
    public static String getDateAsString(Date date) {
        return dateFormat.format(date);
    }

    /**
     * Parses the date to a String in format dd/MM/yyyy
     * @param date the date used to parse a String from
     * @return the String containing the date
     */
    public static String getSqlDateAsString(Date date) {
        return sqlDateFormat.format(date);
    }

    /**
     * extracts a Date from the given DatePicker
     * @return the Date matching the picke Date within the DatePicker
     */
    public static Date getDateFromDatePicker(DatePicker datePicker) {

        String date = String.format(Locale.GERMANY, "%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());
        return DateUtils.getDateFromString(date);
    }

    /**
     * @return the Date of today morning - meaning the actual first second of today in form of a Date
     */
    public static Date getTodayMorning() {

        long dateInMilliSecs = new Date().getTime();
        return new Date(dateInMilliSecs - dateInMilliSecs % DAY_IN_MILLI_SECS);
    }
}
