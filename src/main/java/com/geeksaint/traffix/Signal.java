package com.geeksaint.traffix;

import com.geeksaint.traffix.util.SDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

import static com.geeksaint.traffix.Lane.*;

@Getter
@EqualsAndHashCode
@ToString
//Represents a signal form the source eg. A12322 or B542323
public class Signal {
  private final SDate time;
  private final Lane lane;

  public Signal(SDate time, Lane lane) {
    this.time = time;
    this.lane = lane;
  }

  public Date getTime(){
    return time.getTime();
  }

  public static Signal of(SDate recordedAt, Lane lane) {
    return new Signal(recordedAt, lane);
  }

  public boolean isEntryLane() {
    return lane.equals(ENTRY);
  }
}
