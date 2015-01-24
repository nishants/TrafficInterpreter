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
        new Signal(recordingDate.addMillis(268981l), ENTRY),
        new Signal(recordingDate.addMillis(269123l), ENTRY),

        new Signal(recordingDate.addMillis(604957l), ENTRY),
        new Signal(recordingDate.addMillis(604960l), EXIT),
        new Signal(recordingDate.addMillis(605128l), ENTRY),
        new Signal(recordingDate.addMillis(605132l), EXIT),

        new Signal(recordingDate.addMillis(1089807l), ENTRY),
        new Signal(recordingDate.addMillis(1089810l), EXIT),
        new Signal(recordingDate.addMillis(1089948l), ENTRY),
        new Signal(recordingDate.addMillis(1089951l), EXIT),

        new Signal(recordingDate.addOneDay().addMillis(100), ENTRY),
        new Signal(recordingDate.addOneDay().addOneDay().addMillis(20), ENTRY)
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
