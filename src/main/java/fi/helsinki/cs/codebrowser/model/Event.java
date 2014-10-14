package fi.helsinki.cs.codebrowser.model;

import java.util.Date;
import java.util.Map;

public final class Event {

    private String id;
    private String eventType;
    private Date timestamp;
    private Map<String, Object> metadata;

    public String getId() {

        return id;
    }

    public String getEventType() {

        return eventType;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public Map<String, Object> getMetadata() {

        return metadata;
    }
}
