package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.Vehicle;
import com.geeksaint.traffix.source.SignalReader;
import com.geeksaint.traffix.util.SDate;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;
import static com.geeksaint.traffix.Lane.EXIT;
import static com.geeksaint.traffix.maker.SignalMaker.*;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleInterpreterTest {
  @Test
  public void parsesSignalsToVehicleData() {
    List<Signal> signalList = asList(
        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, new SDate(268581l))))
        );
    SignalReader signalReader = mockedFor(signalList);

    Vehicle expectedOne = Vehicle.parse(signalList.subList(0, 2));
    Vehicle expectedTwo = Vehicle.parse(signalList.subList(2, 4));
    Vehicle expectedThree = Vehicle.parse(signalList.subList(4, 8));
    Vehicle expectedFour = Vehicle.parse(signalList.subList(8, 10));

    SignalInterpreter interpreter = new SignalInterpreter(signalReader);

    assertThat(interpreter.hasNext(), is(true));
    assertThat(interpreter.next(), is(expectedOne));
    assertThat(interpreter.next(), is(expectedTwo));
    assertThat(interpreter.next(), is(expectedThree));
    assertThat(interpreter.next(), is(expectedFour));
  }

  private SignalReader mockedFor(List<Signal> signalList) {
    final Iterator<Signal> iterator = signalList.iterator();
    //A (technically) mocked signal interpreter
    return new SignalReader(new SDate(0), new InputStream() {
      @Override
      public int read() throws IOException {
        return 0;
      }
    }) {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }
      @Override
      public Signal getNext() {
        return iterator.next();
      }
    };
  }
}
