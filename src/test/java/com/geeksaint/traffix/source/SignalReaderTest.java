package com.geeksaint.traffix.source;

import com.geeksaint.traffix.Signal;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;
import static com.geeksaint.traffix.Lane.EXIT;
import static com.geeksaint.traffix.maker.SignalMaker.makeSignal;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SignalReaderTest {
    @Test
    public void shouldReadFile() {
        SignalReader dataSource =
            new SignalReader(
                new Date(0),
              fileAsStream("/data/test_data_reader.txt"));

        List<Signal> readList = new ArrayList<Signal>();
        while (dataSource.hasNext()) {
            readList.add(dataSource.getNext());
        }

        Date recordingDate = new Date(0);
        List<Signal> expectedList = asList(
                makeSignal(recordingDate, 268981l, ENTRY),
                makeSignal(recordingDate, 269123l, ENTRY),

                makeSignal(recordingDate, 604957l, ENTRY),
                makeSignal(recordingDate, 604960l, EXIT),

                makeSignal(recordingDate, 605128l, ENTRY),
                makeSignal(recordingDate, 605132l, EXIT),

                makeSignal(recordingDate, 1089807l, ENTRY),
                makeSignal(recordingDate, 1089810l, EXIT),

                makeSignal(recordingDate, 1089948l, ENTRY),
                makeSignal(recordingDate, 1089951l, EXIT),

                makeSignal(increment(recordingDate), 100, ENTRY),
                makeSignal(increment(increment(recordingDate)), 20, ENTRY)
        );

        assertThat(readList, is(expectedList));
    }

    private Date increment(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private InputStream fileAsStream(String fileName) {
        return getClass().getResourceAsStream(fileName);
    }
}
