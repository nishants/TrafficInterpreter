package com.geeksaint.traffix.maker;

import com.geeksaint.traffix.Lane;
import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.util.Date;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import static com.geeksaint.traffix.Lane.*;

public class SignalMaker {
  public static final Property<Signal, Date> time = new Property<Signal, Date>();
  public static final Property<Signal, Lane> lane = new Property<Signal, Lane>();

  public static final Instantiator<Signal> SIGNAL = new Instantiator<Signal>() {
    public Signal instantiate(PropertyLookup<Signal> lookup) {
      Date recordedAt = lookup.valueOf(time, new Date(0l));
      Lane goingNorth = lookup.valueOf(lane, ENTRY);
      return Signal.of(recordedAt, goingNorth);
    }
  };

}


