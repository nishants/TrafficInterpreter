package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.geeksaint.traffix.Lane.*;

@EqualsAndHashCode
@ToString
public class Vehicle {
  @Getter
  private final Date timeObserved;

  @Getter
  private final boolean isEntering;

  public Vehicle(Date timeObserved, Lane lane) {
    this.timeObserved = timeObserved;
    this.isEntering = (lane == ENTRY);
  }

  public static Vehicle parse(List<Signal> signals) {
    Date timeObserved = signals.get(0).getTime();
    return new Vehicle(timeObserved, laneOf(signals));
  }

  private static Lane laneOf(List<Signal> signals) {
    return signals.size() == 2 ? ENTRY : EXIT;
  }
}
