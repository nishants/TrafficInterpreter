package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.Vehicle;
import com.geeksaint.traffix.source.SignalReader;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class SignalInterpreter implements Iterator<Vehicle> {

  private final SignalReader signalReader;

  public SignalInterpreter(SignalReader signalReader) {
    this.signalReader = signalReader;
  }

  @Override
  public boolean hasNext() {
    return signalReader.hasNext();
  }

  @Override
  public Vehicle next() {
    Signal firstSignal = signalReader.getNext();
    Signal secondSignal = signalReader.getNext();
    if(secondSignal.isEntryLane()){
      return Vehicle.parse(asList(firstSignal, secondSignal));
    }
    Signal thirdSignal = signalReader.getNext();
    Signal fourthSignal = signalReader.getNext();
    return Vehicle.parse(asList(firstSignal, secondSignal, thirdSignal, fourthSignal));
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
