package com.geeksaint.traffix;

public enum Lane {
  ENTRY("A"),
  EXIT("B");

  private String signalPrefix;

  Lane(String prefix) {
    signalPrefix = prefix;
  }

  public static Lane of(String token) {
    for (Lane lane : values()) {
      if (token.startsWith(lane.signalPrefix)) return lane;
    }
    return null;
  }
}
