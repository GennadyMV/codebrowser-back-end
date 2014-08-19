package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SnapshotFile {

    private String id = "1";
    private String name = "ex";
    private String filepath;

    @JsonIgnore
    private long filesize;

    public SnapshotFile(final String filepath) {

        this.filepath = filepath;
    }

    public String getFilepath() {

        return filepath;
    }

    public void setFilepath(final String filepath) {

        this.filepath = filepath;
    }

    @JsonIgnore
    public String getContent() throws IOException {

        final File file = new File(getFilepath());

        return FileUtils.readFileToString(file);
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
