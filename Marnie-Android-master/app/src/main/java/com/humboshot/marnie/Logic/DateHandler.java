package com.humboshot.marnie.Logic;

import android.icu.util.Calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by martin on 19-05-2017.
 */

public class DateHandler {
    private DateFormat format;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private String dateFormatString ="yyyy-MM-dd kk:mm:ss";

    public DateHandler() {
        format  = new SimpleDateFormat(dateFormatString);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("kk:mm:ss");
    }

    public String getDateFormatString() {
        return dateFormatString;
    }

    public Date ParseString(String date, String time) {
        String dateTimeString = date + " " + time;
        Date thisDate = null;
        try {
            thisDate = format.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return thisDate;
    }

    public String ParseDateToDateTimeString(Date date) {
        String dateTimeString;
        try{
            dateTimeString = dateFormat.format(date) + "T" + timeFormat.format(date);
            return dateTimeString;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int CalculateAge(Date birthday){
        Calendar birthDay = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        birthDay.setTime(birthday);
        int age = today.get(java.util.Calendar.YEAR) - birthDay.get(java.util.Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    public String ParseDateToDateString(Date date) {
        return dateFormat.format(date);
    }
}
