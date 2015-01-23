package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Reading;
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
    Reading firstReading = dataSource.getNext();
    Reading secondReading = dataSource.getNext();
    if(secondReading.isEntryLane()){
      return VehicleData.record(asList(firstReading, secondReading));
    }
    Reading thirdReading = dataSource.getNext();
    Reading fourthReading = dataSource.getNext();
    return VehicleData.record(asList(firstReading, secondReading, thirdReading, fourthReading));
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
