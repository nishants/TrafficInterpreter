package com.geeksaint.traffix.maker;

import com.geeksaint.traffix.Lane;
import com.geeksaint.traffix.Signal;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import java.util.Date;

import static com.geeksaint.traffix.Lane.*;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;

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

  public static Signal makeSignal(Date observedOn, long timeInMillis, Lane lane){
    return make(a(SIGNAL, with(SignalMaker.lane, lane), with(time, addToDate(observedOn, timeInMillis))));
  }

  private static Date addToDate(Date observedOn, long timeInMillis) {
    return new Date(observedOn.getTime() + timeInMillis);
  }
}


