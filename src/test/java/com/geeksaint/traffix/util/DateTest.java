package com.geeksaint.traffix.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DateTest {
  @Test
  public void shouldEqualIfSameTime(){
    assertThat(new Date(101l), is(new Date(101l)));
    assertThat(new Date(101l), is(not(new Date(102l))));
  }
}