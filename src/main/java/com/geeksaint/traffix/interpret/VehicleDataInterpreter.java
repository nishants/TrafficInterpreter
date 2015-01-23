package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.VehicleData;
import com.geeksaint.traffix.source.DataSource;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class VehicleDataInterpreter implements Iterator<VehicleData> {

  private final DataSource dataSource;

  public VehicleDataInterpreter(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public boolean hasNext() {
    return dataSource.hasNext();
  }

  @Override
  public VehicleData next() {
    Signal firstSignal = dataSource.getNext();
    Signal secondSignal = dataSource.getNext();
    if(secondSignal.isEntryLane()){
      return VehicleData.record(asList(firstSignal, secondSignal));
    }
    Signal thirdSignal = dataSource.getNext();
    Signal fourthSignal = dataSource.getNext();
    return VehicleData.record(asList(firstSignal, secondSignal, thirdSignal, fourthSignal));
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
