package fi.helsinki.cs.codebrowser.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class EventTest {

    private static final String ID = "eventId";
    private static final String EVENT_TYPE = "test_insert";
    private static final Map<String, Object> METADATA = new HashMap<>();
    private static final Date TIMESTAMP = new Date(1409825104259L);

    private Event event;

    @Before
    public void setUp() {

        this.event = new Event();
    }

    @Test
    public void canSetId() {

        event.setId(ID);

        assertEquals(ID, event.getId());
    }

    @Test
    public void canSetEventType() {

        event.setEventType(EVENT_TYPE);

        assertEquals(EVENT_TYPE, event.getEventType());
    }

    @Test
    public void canSetMetadata() {

        event.setMetadata(METADATA);

        assertEquals(METADATA, event.getMetadata());
    }

    @Test
    public void canSetTimestamp() {

        event.setTimestamp(TIMESTAMP);

        assertEquals(TIMESTAMP, event.getTimestamp());
    }
}
