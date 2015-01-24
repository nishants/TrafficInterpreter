package com.geeksaint.traffix.util;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class SDateTest {
  @Test
  public void shouldEqualIfSameTime(){
    assertThat(new SDate(101l), is(new SDate(101l)));
    assertThat(new SDate(new Date(101l)), is(new SDate(new Date(101l))));
    assertThat(new SDate(new Date(101l)), is(new SDate(101l)));
    assertThat(new SDate(101l), is(not(new SDate(102l))));
  }
}