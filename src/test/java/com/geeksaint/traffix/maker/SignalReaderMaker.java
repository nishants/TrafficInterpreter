package com.geeksaint.traffix.maker;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.source.SignalReader;
import com.geeksaint.traffix.util.SDate;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SignalReaderMaker {
  public static final Property<SignalReader, List<Signal>> signals = new Property<SignalReader, List<Signal>>();

  public static final Instantiator<SignalReader> SIGNAL = new Instantiator<SignalReader>() {
    public SignalReader instantiate(PropertyLookup<SignalReader> lookup) {
      List<Signal> signalsList = lookup.valueOf(signals, new ArrayList<Signal>());
      final Iterator<Signal> iterator = signalsList.iterator();
      //A (technically) mocked signal interpreter
      return new SignalReader(new SDate(0), new InputStream() {
        @Override
        public int read() throws IOException {
          return 0;
        }
      }) {
        @Override
        public boolean hasNext() {
          return iterator.hasNext();
        }
        @Override
        public Signal getNext() {
          return iterator.next();
        }
      };
    }
  };
}


