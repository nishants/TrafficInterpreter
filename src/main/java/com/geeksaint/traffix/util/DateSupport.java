package com.geeksaint.traffix.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

//Abstraction for hiding the nuances of java date api
public class DateSupport {
  public static Date addDaysTo(Date day, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(day.getTime());
    calendar.add(DAY_OF_MONTH, days);
    return calendar.getTime();
  }

  public static int timeOfDayInSeconds(Date time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);
    return calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 +
        calendar.get(Calendar.MINUTE) * 60 +
        calendar.get(Calendar.SECOND);
  }

  //Converts the time into minutes past since 00:00
  public static int timeOfDayInMinutes(Date time) {
    return timeOfDayInSeconds(time) / 60;
  }

  // Return date stamp of format ddmmyy eg. 030114 for 3rd Jan 2012
  public static String toDateStamp(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
    return dateFormat.format(date);
  }

  public static Date toDateOfYear(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    resetTimeComponent(calendar);
    return calendar.getTime();
  }

  private static void resetTimeComponent(Calendar calendar) {
    calendar.set(MILLISECOND, 0);
    calendar.set(SECOND, 0);
    calendar.set(MINUTE, 0);
    calendar.set(HOUR_OF_DAY, 0);
  }
}
