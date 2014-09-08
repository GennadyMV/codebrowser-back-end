package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Instance {

    private final String id;

    @JsonCreator
    public Instance(@JsonProperty("id") final String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }
}
