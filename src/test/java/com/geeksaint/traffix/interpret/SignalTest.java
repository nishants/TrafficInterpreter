package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import org.junit.Test;

import java.util.Date;

import static com.geeksaint.traffix.Lane.*;
import static com.geeksaint.traffix.Signal.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SignalTest {

  @Test
  public void shouldMakeAReading() {
    Signal pointASignal = of(new Date(0l), ENTRY);
    assertThat(pointASignal.isEntryLane(), is(true));

    Signal pointBSignal = of(new Date(0l), EXIT);
    assertThat(pointBSignal.isEntryLane(), is(false));
  }
}
