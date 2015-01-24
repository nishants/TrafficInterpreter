package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.Long.*;

public class SignalReader implements Iterator<Signal> {
  private final Scanner scanner;
  private Date currentDayOfRecording;
  private long lastRecordingTime;

  public SignalReader(Date date, InputStream inputStream) {
    currentDayOfRecording = date;
    scanner = new Scanner(inputStream);
  }

  @Override
  public boolean hasNext() {
    return scanner.hasNext();
  }

  @Override
  public Signal next() {
    String token = scanner.next();

    return Signal.of(
        currentDateOf(token),
        Lane.of(token)
    );
  }

  private Date currentDateOf(String token) {
    long time = parseLong(token.substring(1));
    if (lastRecordingTime > time) {
      currentDayOfRecording = currentDayOfRecording.addOneDay();
    }
    lastRecordingTime = time;
    return currentDayOfRecording.addTime(time);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
