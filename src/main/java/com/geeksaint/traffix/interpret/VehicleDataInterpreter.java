package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.VehicleData;
import com.geeksaint.traffix.source.SignalInterpreter;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class VehicleDataInterpreter implements Iterator<VehicleData> {

  private final SignalInterpreter signalInterpreter;

  public VehicleDataInterpreter(SignalInterpreter signalInterpreter) {
    this.signalInterpreter = signalInterpreter;
  }

  @Override
  public boolean hasNext() {
    return signalInterpreter.hasNext();
  }

  @Override
  public VehicleData next() {
    Signal firstSignal = signalInterpreter.getNext();
    Signal secondSignal = signalInterpreter.getNext();
    if(secondSignal.isEntryLane()){
      return VehicleData.parse(asList(firstSignal, secondSignal));
    }
    Signal thirdSignal = signalInterpreter.getNext();
    Signal fourthSignal = signalInterpreter.getNext();
    return VehicleData.parse(asList(firstSignal, secondSignal, thirdSignal, fourthSignal));
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
