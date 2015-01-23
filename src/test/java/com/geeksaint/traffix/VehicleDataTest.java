package com.geeksaint.traffix;

import com.geeksaint.traffix.maker.SignalMaker;
import org.junit.Test;

import java.util.Date;

import static com.geeksaint.traffix.Lane.*;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleDataTest {
  @Test
  public void shouldSetSpeedForLaneAVehicles() {
    Signal frontAxleSensorASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(1000l))));

    Signal backAxleSensorASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(2000l))));

    VehicleData vehicleData = VehicleData.parse(asList(frontAxleSensorASignal, backAxleSensorASignal));

    assertThat(vehicleData.isEntering(), is(true));
    assertThat(vehicleData.isEntering(), is(true));
  }

  @Test
  public void shouldSetSpeedForLaneBVehicles() {
    Signal frontAxleSignalASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(1000l))));
    Signal frontAxleSignalBSignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(1003l))));

    Signal backAxleSignalASignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(2005l))));

    Signal backAxleSignalBSignal = make(a(SignalMaker.SIGNAL,
        with(SignalMaker.lane, ENTRY),
        with(SignalMaker.time, toDate(2013l))));

    VehicleData vehicleData = VehicleData.parse(asList(
        frontAxleSignalASignal,
        frontAxleSignalBSignal,
        backAxleSignalASignal,
        backAxleSignalBSignal
    ));

    assertThat(vehicleData.isEntering(), is(false));
    assertThat(vehicleData.isEntering(), is(false));
  }

  private Date toDate(long time) {
    return new Date(time);
  }
}
