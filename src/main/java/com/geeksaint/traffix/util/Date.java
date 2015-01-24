package com.geeksaint.traffix.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;

@EqualsAndHashCode
@Getter
@ToString
public class Date {
  private java.util.Date time;

  public Date(long timeInMillis) {
    this.time = new java.util.Date(timeInMillis);
  }

  public Date addOneDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time.getTime());
    calendar.add(DAY_OF_MONTH, 1);
    java.util.Date nextDay = calendar.getTime();
    return new Date(nextDay.getTime());
  }

  public Date addMillis(long milliSecs) {
    return new Date(time.getTime() + milliSecs);
  }
}
