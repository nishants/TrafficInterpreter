package com.geeksaint.traffix.util;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DateTest {
  @Test
  public void shouldEqualIfSameTime(){
    assertThat(new Date(101l), is(new Date(101l)));
    assertThat(new Date(101l), is(not(new Date(102l))));
  }

  @Test
  public void shouldIncrementByDay(){
      java.util.Date date = toDate(2015, 1, 1);
      java.util.Date oneDayLater = toDate(2015, 1, 2);

      Date actual = new Date(date.getTime()).addOneDay();
      Date expected = new Date(oneDayLater.getTime());

      assertThat(actual, is(expected));
  }

  @Test
  public void shouldIncrementByMilliSeconds(){
      java.util.Date date = toDate(2015, 1, 1);

      Date actual = new Date(date.getTime()).addMillis(101l);
      Date expected = new Date(date.getTime() + 101l);

      assertThat(actual, is(expected));
  }

  private java.util.Date toDate(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, day, 0, 0);
    return calendar.getTime();
  }

}