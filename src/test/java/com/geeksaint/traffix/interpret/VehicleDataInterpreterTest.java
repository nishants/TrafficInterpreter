package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.VehicleData;
import com.geeksaint.traffix.source.DataSource;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;
import static com.geeksaint.traffix.Lane.EXIT;
import static com.geeksaint.traffix.maker.ReadingMaker.makeReading;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleDataInterpreterTest {
  @Test
  public void takesReadingsFromSourceAndReturnsVehicle() {
    List<Signal> signalList = asList(
        makeReading((new Date()), 268581l, ENTRY),
        makeReading((new Date()), 268681l, ENTRY),
        makeReading((new Date()), 268781l, ENTRY),
        makeReading((new Date()), 268581l, ENTRY),
        makeReading((new Date()), 268581l, ENTRY),
        makeReading((new Date()), 268581l, EXIT),
        makeReading((new Date()), 268581l, ENTRY),
        makeReading((new Date()), 268581l, EXIT),
        makeReading((new Date()), 268581l, ENTRY),
        makeReading((new Date()), 268581l, ENTRY)
    );
    DataSource dataSource = mockedFor(signalList);

    VehicleData expectedOne = VehicleData.record(signalList.subList(0, 2));
    VehicleData expectedTwo = VehicleData.record(signalList.subList(2, 4));
    VehicleData expectedThree = VehicleData.record(signalList.subList(4, 8));
    VehicleData expectedFour = VehicleData.record(signalList.subList(8, 10));

    VehicleDataInterpreter interpreter = new VehicleDataInterpreter(dataSource);

    assertThat(interpreter.hasNext(), is(true));
    assertThat(interpreter.next(), is(expectedOne));
    assertThat(interpreter.next(), is(expectedTwo));
    assertThat(interpreter.next(), is(expectedThree));
    assertThat(interpreter.next(), is(expectedFour));
  }

  private DataSource mockedFor(List<Signal> signalList) {
    final Iterator<Signal> iterator = signalList.iterator();
    DataSource dataSource = new DataSource() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }
      @Override
      public Signal getNext() {
        return iterator.next();
      }
    };
    return dataSource;
  }
}
