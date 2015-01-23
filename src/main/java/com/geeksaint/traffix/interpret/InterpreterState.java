package com.geeksaint.traffix.interpret;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.VehicleData;

public interface InterpreterState {
  //Takes the input and returns the next state of the interpreter
  InterpreterState input(Signal signal);

  //Returns true if the current state can have an output
  boolean hasOutput();

  //Returns output or null
  VehicleData getOutput();
}
