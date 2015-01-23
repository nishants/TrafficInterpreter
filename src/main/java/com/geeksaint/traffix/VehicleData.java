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
  private final Date timeObserved;

  @Getter
  private final boolean isEntering;

  public VehicleData(Date timeObserved, Lane lane) {
    this.timeObserved = timeObserved;
    this.isEntering = (lane == ENTRY);
  }

  public static VehicleData parse(List<Signal> signals) {
    Date timeObserved = signals.get(0).getTime();
    return new VehicleData(timeObserved, laneOf(signals));
  }

  private static Lane laneOf(List<Signal> signals) {
    return signals.size() == 2 ? ENTRY : EXIT;
  }
}
