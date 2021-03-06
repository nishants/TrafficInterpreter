package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;
import org.junit.Test;

import static com.geeksaint.traffix.Lane.*;
import static com.geeksaint.traffix.Signal.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SignalTest {

  @Test
  public void shouldMakeASignal() {
    Signal sensorASignal = of(new Date(0l), ENTRY);
    assertThat(sensorASignal.isEntryLane(), is(true));

    Signal sensorBSignal = of(new Date(0), EXIT);
    assertThat(sensorBSignal.isEntryLane(), is(false));
  }

  @Test
  public void shouldEqualToSignalWithSameTimeAndLane() {
    Signal one = of(new Date(0), EXIT);
    Signal another = of(new Date(0), EXIT);
    assertThat(one, is(another));
  }
}
