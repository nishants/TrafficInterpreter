package com.geeksaint.traffix.source;

import com.geeksaint.traffix.Lane;
import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.util.SDate;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Long.*;

public class SignalReader {
  private final Scanner scanner;
  private SDate currentDayOfRecording;
  private long lastRecordingTime;

  public SignalReader(SDate date, InputStream inputStream) {
    currentDayOfRecording = date;
    scanner = new Scanner(inputStream);
  }

  public boolean hasNext() {
    return scanner.hasNext();
  }

  public Signal getNext() {
    return parse(scanner.next());
  }

  // Converts the time part of Strings like "A1242" or "B3848" to time,
  // combining with the start date.
  private Signal parse(String token) {
    return Signal.of(
        currentDateOf(token),
        Lane.of(token)
    );
  }

  private SDate currentDateOf(String token) {
    long time = parseLong(token.substring(1));
    if (lastRecordingTime > time) {
      currentDayOfRecording = currentDayOfRecording.addOneDay();
    }
    lastRecordingTime = time;
    return currentDayOfRecording.addTime(time);
  }

}
