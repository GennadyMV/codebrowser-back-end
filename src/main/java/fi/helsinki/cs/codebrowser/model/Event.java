package fi.helsinki.cs.codebrowser.model;

import java.util.Date;
import java.util.Map;

public final class Event {

    private String id;
    private String eventType;
    private Date timestamp;
    private Map<String, Object> metadata;

    public void setId(final String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setEventType(final String eventType) {

        this.eventType = eventType;
    }

    public String getEventType() {

        return eventType;
    }

    public void setTimestamp(final Date timestamp) {

        this.timestamp = timestamp;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setMetadata(final Map<String, Object> metadata) {

        this.metadata = metadata;
    }

    public Map<String, Object> getMetadata() {

        return metadata;
    }
}
