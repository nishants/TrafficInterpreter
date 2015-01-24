package com.geeksaint.traffix;

import com.geeksaint.traffix.util.Date;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;
import static com.geeksaint.traffix.Lane.EXIT;
import static com.geeksaint.traffix.maker.SignalMaker.*;
import static com.geeksaint.traffix.maker.SignalMaker.SIGNAL;
import static com.geeksaint.traffix.maker.SignalMaker.time;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SignalReaderTest {
  private InputStream fileAsStream;
  private List<Signal> expectedList;
  private Date recordingDate;

  @Before
  public void setup() {
    this.fileAsStream = new ByteArrayInputStream((
        String.format("A268981%n" +
            "A269123%n" +
            "A604957%n" +
            "B604960%n" +
            "A605128%n" +
            "B605132%n" +
            "A1089807%n" +
            "B1089810%n" +
            "A1089948%n" +
            "B1089951%n" +
            "A100%n" +
            "A20")).getBytes());

    recordingDate = new Date(0l);

    expectedList = asList(
        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(268981l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(269123l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(604957l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, recordingDate.addMillis(604960l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(605128l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, recordingDate.addMillis(605132l)))),


        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(1089807l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, recordingDate.addMillis(1089810l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addMillis(1089948l)))),

        make(a(SIGNAL,
            with(lane, EXIT),
            with(time, recordingDate.addMillis(1089951l)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addOneDay().addMillis(100)))),

        make(a(SIGNAL,
            with(lane, ENTRY),
            with(time, recordingDate.addOneDay().addOneDay().addMillis(20))))
    );
  }

  @Test
  public void shouldReadFile() {
    SignalReader dataSource =
        new SignalReader(
            new Date(0l),
            fileAsStream);

    List<Signal> readList = new ArrayList<Signal>();
    while (dataSource.hasNext()) {
      readList.add(dataSource.next());
    }

    assertThat(readList, is(expectedList));
  }
}
