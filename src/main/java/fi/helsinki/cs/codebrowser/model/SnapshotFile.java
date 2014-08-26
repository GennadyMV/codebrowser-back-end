package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SnapshotFile {

    private final String id;
    private final String name;

    @JsonIgnore
    private final String path;

    @JsonCreator
    public SnapshotFile(@JsonProperty("id") final String id,
                        @JsonProperty("name") final String name,
                        @JsonProperty("path") final String path) {

        this.id = id;
        this.name = name;
        this.path = path;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }
}
