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
  public void shouldMakeASignal() {
    Signal sensorASignal = of(new Date(0l), ENTRY);
    assertThat(sensorASignal.isEntryLane(), is(true));

    Signal sensorBSignal = of(new Date(0l), EXIT);
    assertThat(sensorBSignal.isEntryLane(), is(false));
  }
}
