package com.geeksaint.traffix.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;

@AllArgsConstructor
@Getter
public class SDate {
  private Date time;

  public SDate(long timeInMillis) {
    this.time = new Date(timeInMillis);
  }

  public SDate addOneDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time.getTime());
    calendar.add(DAY_OF_MONTH, 1);
    Date nextDay = calendar.getTime();
    return new SDate(nextDay);
  }

  public SDate addTime(long millisToAdd) {
    return new SDate(time.getTime() + millisToAdd);
  }
}
