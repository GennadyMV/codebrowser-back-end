package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Instance {

    private final String id;
    private final String name;

    @JsonCreator
    public Instance(@JsonProperty("id") final String id, @JsonProperty("name") final String name) {

        this.id = id;
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }
}
