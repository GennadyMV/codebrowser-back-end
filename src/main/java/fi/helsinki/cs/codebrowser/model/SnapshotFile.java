package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SnapshotFile {

    private final String id;
    private final String name;
    private String filepath;

    @JsonIgnore
    private long filesize;

    @JsonCreator
    public SnapshotFile(@JsonProperty("id") final String id, @JsonProperty("name") final String name, @JsonProperty("path") final String filepath) {

        this.id = id;
        this.name = name;
        this.filepath = filepath;
    }

    public String getFilepath() {

        return filepath;
    }

    public void setFilepath(final String filepath) {

        this.filepath = filepath;
    }

    public long getFilesize() {

        return filesize;
    }

    public void setFilesize(final long filesize) {

        this.filesize = filesize;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }
}
