package com.geeksaint.traffix;

import com.geeksaint.traffix.util.DateSupport;
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
  private final Lane lane;

  public VehicleData(Date timeAtHoseA, Lane lane) {
    this.timeAtHoseA = timeAtHoseA;
    this.lane = lane;
  }

  public static VehicleData record(List<Reading> readings) {
    Date timeAtHoseA = frontAxleHoseATime(readings);
    return new VehicleData(timeAtHoseA, laneOf(readings));
  }

  public boolean isEntering() {
    return lane == LANE_A;
  }

  private static Lane laneOf(List<Reading> readings) {
    return readings.size() == 2 ? LANE_A : LANE_B;
  }

  private static Date frontAxleHoseATime(List<Reading> readings) {
    return readings.get(0).getTime();
  }
}
