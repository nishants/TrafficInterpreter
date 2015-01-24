package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;
import org.junit.Test;

import static com.geeksaint.traffix.Lane.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleTest {
  @Test
  public void shouldCreateLaneAVehicles() {
    Date timeAtSensorA = toDate(1000l);
    Signal frontAxleSensorASignal = new Signal(timeAtSensorA, ENTRY);
    Signal backAxleSensorASignal = new Signal(toDate(2000l), ENTRY);

    Vehicle vehicle = Vehicle.parse(asList(frontAxleSensorASignal, backAxleSensorASignal));

    assertThat(vehicle.isEntering(), is(true));
    assertThat(vehicle.getTimeObserved(), is(timeAtSensorA));
  }

  @Test
  public void shouldCreateLaneBVehicles() {
    Date timeAtSensorA = new Date(1000l);
    Signal frontAxleSignalASignal = new Signal(timeAtSensorA, ENTRY);
    Signal frontAxleSignalBSignal = new Signal(toDate(1003l), ENTRY);
    Signal backAxleSignalASignal = new Signal(toDate(2005l), ENTRY);
    Signal backAxleSignalBSignal = new Signal(toDate(2013l), ENTRY);

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
