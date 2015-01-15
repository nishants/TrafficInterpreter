package com.geeksaint.traffix.source;

import com.geeksaint.traffix.Reading;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.geeksaint.traffix.Lane.ENTRY;
import static com.geeksaint.traffix.Lane.EXIT;
import static com.geeksaint.traffix.maker.ReadingMaker.makeReading;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileDataSourceTest {
    @Test
    public void shouldReadFile() {
        FileDataSource dataSource = new FileDataSource(1970, 1, 1, fileAsStream("/data/test_data_reader.txt"));
        List<Reading> readList = new ArrayList<Reading>();
        while (dataSource.hasNext()) {
            readList.add(dataSource.getNext());
        }

        Date recordingDate = toDate(1970, 1, 1);
        List<Reading> expectedList = asList(
                makeReading(recordingDate, 268981l, ENTRY),
                makeReading(recordingDate, 269123l, ENTRY),

                makeReading(recordingDate, 604957l, ENTRY),
                makeReading(recordingDate, 604960l, EXIT),

                makeReading(recordingDate, 605128l, ENTRY),
                makeReading(recordingDate, 605132l, EXIT),

                makeReading(recordingDate, 1089807l, ENTRY),
                makeReading(recordingDate, 1089810l, EXIT),

                makeReading(recordingDate, 1089948l, ENTRY),
                makeReading(recordingDate, 1089951l, EXIT),

                makeReading(increment(recordingDate), 100, ENTRY),
                makeReading(increment(increment(recordingDate)), 20, ENTRY)
        );

        assertThat(readList, is(expectedList));
    }

    private Date toDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    private Date addToDate(Date date, long timeInMillis) {
        return new Date(date.getTime() + timeInMillis);
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
