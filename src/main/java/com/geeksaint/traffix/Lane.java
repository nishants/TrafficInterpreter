package com.geeksaint.traffix;

public enum Lane {
  ENTRY("A"),
  EXIT("B");

  private String readingPrefix;

  Lane(String prefix) {
    readingPrefix = prefix;
  }

  public static Lane of(String token) {
    for (Lane lane : values()) {
      if (token.startsWith(lane.readingPrefix)) return lane;
    }
    return null;
  }
}
