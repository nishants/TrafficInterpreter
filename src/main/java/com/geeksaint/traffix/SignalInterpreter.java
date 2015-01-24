package com.geeksaint.traffix;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class SignalInterpreter implements Iterator<Vehicle> {

  private final Iterator<Signal> signalReader;

  public SignalInterpreter(Iterator<Signal> signalReader) {
    this.signalReader = signalReader;
  }

  public boolean hasNext() {
    return signalReader.hasNext();
  }

  public Vehicle next() {
    Signal firstSignal = signalReader.next();
    Signal secondSignal = signalReader.next();
    if(secondSignal.isEntryLane()){
      return Vehicle.parse(asList(firstSignal, secondSignal));
    }
    Signal thirdSignal = signalReader.next();
    Signal fourthSignal = signalReader.next();
    return Vehicle.parse(asList(firstSignal, secondSignal, thirdSignal, fourthSignal));
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
