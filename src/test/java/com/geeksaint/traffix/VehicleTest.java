package com.geeksaint.traffix;

import com.geeksaint.traffix.maker.SignalMaker;
import com.geeksaint.traffix.util.Date;
import org.junit.Test;

import static com.geeksaint.traffix.Lane.*;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleTest {
  @Test
  public void shouldCreateLaneAVehicles() {
    Date timeAtSensorA = toDate(1000l);
    Signal frontAxleSensorASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, timeAtSensorA)));

    Signal backAxleSensorASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, new Date(2000l))));

    Vehicle vehicle = Vehicle.parse(asList(frontAxleSensorASignal, backAxleSensorASignal));

    assertThat(vehicle.isEntering(), is(true));
    assertThat(vehicle.getTimeObserved(), is(timeAtSensorA));
  }

  @Test
  public void shouldCreateLaneBVehicles() {
    Date timeAtSensorA = new Date(1000l);
    Signal frontAxleSignalASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, timeAtSensorA)));
    Signal frontAxleSignalBSignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(1003l))));

    Signal backAxleSignalASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(2005l))));

    Signal backAxleSignalBSignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(2013l))));

    Vehicle vehicle = Vehicle.parse(asList(
        frontAxleSignalASignal,
        frontAxleSignalBSignal,
        backAxleSignalASignal,
        backAxleSignalBSignal
    ));

    assertThat(vehicle.getTimeObserved(), is(timeAtSensorA));
    assertThat(vehicle.isEntering(), is(false));
  }

  private Date toDate(long time) {
    return new Date(time);
  }
}
