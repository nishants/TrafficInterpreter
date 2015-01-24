package com.geeksaint.traffix.source;

import com.geeksaint.traffix.Signal;
import com.geeksaint.traffix.util.SDate;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

  @Before
  public void setup(){
    this.fileAsStream = new ByteArrayInputStream((
        String.format("A268981%n" +
        "A269123%n" +
        "A604957%n"+
        "B604960%n"+
        "A605128%n"+
        "B605132%n"+
        "A1089807%n"+
        "B1089810%n"+
        "A1089948%n"+
        "B1089951%n"+
        "A100%n" +
        "A20")).getBytes());
  }

    @Test
    public void shouldReadFile() {
        SignalReader dataSource =
            new SignalReader(
                new SDate(new Date(0)),
                fileAsStream);

        List<Signal> readList = new ArrayList<Signal>();
        while (dataSource.hasNext()) {
            readList.add(dataSource.getNext());
        }

        SDate recordingDate = new SDate(0);
        List<Signal> expectedList = asList(
            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(268981l)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(269123l)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(604957l)))),

            make(a(SIGNAL,
                with(lane, EXIT),
                with(time, recordingDate.addTime(604960l)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(605128l)))),

            make(a(SIGNAL,
                with(lane, EXIT),
                with(time, recordingDate.addTime(605132l)))),


            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(1089807l)))),

            make(a(SIGNAL,
                with(lane, EXIT),
                with(time, recordingDate.addTime(1089810l)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addTime(1089948l)))),

            make(a(SIGNAL,
                with(lane, EXIT),
                with(time, recordingDate.addTime(1089951l)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addOneDay().addTime(100)))),

            make(a(SIGNAL,
                with(lane, ENTRY),
                with(time, recordingDate.addOneDay().addOneDay().addTime(20))))
        );

        assertThat(readList, is(expectedList));
    }
}
