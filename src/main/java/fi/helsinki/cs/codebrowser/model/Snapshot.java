package fi.helsinki.cs.codebrowser.model;

import java.util.Date;
import java.util.List;

public final class Snapshot {

    private String id;
    private Date timestamp;
    private List<SnapshotFile> files;

    public void setId(final String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setTimestamp(final Date timestamp) {

        this.timestamp = timestamp;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setFiles(final List<SnapshotFile> snapshotFiles) {

        this.files = snapshotFiles;
    }

    public List<SnapshotFile> getFiles() {

        return files;
    }
}
