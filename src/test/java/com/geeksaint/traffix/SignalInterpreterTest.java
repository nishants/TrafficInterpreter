package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;
import org.junit.Test;

import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;

import static com.geeksaint.traffix.Lane.EXIT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SignalInterpreterTest {
  @Test
  public void parsesSignalsToVehicleData() {
    List<Signal> signalList = asList(
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), EXIT),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), EXIT),
        new Signal(new Date(268581l), ENTRY),
        new Signal(new Date(268581l), ENTRY)
    );

    Vehicle expectedOne = Vehicle.parse(signalList.subList(0, 2));
    Vehicle expectedTwo = Vehicle.parse(signalList.subList(2, 4));
    Vehicle expectedThree = Vehicle.parse(signalList.subList(4, 8));
    Vehicle expectedFour = Vehicle.parse(signalList.subList(8, 10));

    SignalInterpreter interpreter = new SignalInterpreter(signalList.iterator());

    assertThat(interpreter.hasNext(), is(true));
    assertThat(interpreter.next(), is(expectedOne));
    assertThat(interpreter.next(), is(expectedTwo));
    assertThat(interpreter.next(), is(expectedThree));
    assertThat(interpreter.next(), is(expectedFour));
  }
}
