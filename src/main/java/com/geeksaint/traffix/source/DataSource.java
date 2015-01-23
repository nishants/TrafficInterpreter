package com.geeksaint.traffix.source;

import com.geeksaint.traffix.Signal;

public interface DataSource {
  boolean hasNext();
  Signal getNext();
}
