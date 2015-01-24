package com.geeksaint.traffix.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class Date {
  private final long time;

  public Date(long timeInMillis) {
    this.time = timeInMillis;
  }

  public Date addOneDay() {
    return new Date(time + 24*60*60*1000);
  }

  public Date addMillis(long milliSecs) {
    return new Date(time + milliSecs);
  }
}
