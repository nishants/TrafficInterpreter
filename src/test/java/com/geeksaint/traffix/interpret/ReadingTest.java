package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Reading;
import org.junit.Test;

import java.util.Date;

import static com.geeksaint.traffix.Lane.*;
import static com.geeksaint.traffix.Reading.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReadingTest {

  @Test
  public void shouldMakeAReading() {
    Reading pointAReading = of(new Date(0l), ENTRY);
    assertThat(pointAReading.isEntryLane(), is(true));

    Reading pointBReading = of(new Date(0l), EXIT);
    assertThat(pointBReading.isEntryLane(), is(false));
  }
}
