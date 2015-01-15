package com.geeksaint.traffix;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import static com.geeksaint.traffix.Lane.*;

@EqualsAndHashCode
@ToString
public class VehicleData {
  @Getter
  private final Date timeAtHoseA;

  @Getter
  private final boolean isEntering;

  public VehicleData(Date timeAtHoseA, Lane lane) {
    this.timeAtHoseA = timeAtHoseA;
    this.isEntering = (lane == ENTRY);
  }

  public static VehicleData record(List<Reading> readings) {
    Date timeAtHoseA = frontAxleHoseATime(readings);
    return new VehicleData(timeAtHoseA, laneOf(readings));
  }

  private static Lane laneOf(List<Reading> readings) {
    return readings.size() == 2 ? ENTRY : EXIT;
  }

  private static Date frontAxleHoseATime(List<Reading> readings) {
    return readings.get(0).getTime();
  }
}
