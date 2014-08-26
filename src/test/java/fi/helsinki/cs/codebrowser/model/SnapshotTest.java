package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class SnapshotTest {

    private static final String ID = "snapshotID";

    private Snapshot snapshot;

    @Before
    public void setUp() {

        snapshot = new Snapshot();
    }

    @Test
    public void canSetID() {

        snapshot.setId(ID);

        assertEquals(ID, snapshot.getId());
    }

    @Test
    public void canSetTimestamp() {

        final Date timestamp = new Date();
        snapshot.setTimestamp(timestamp);

        assertEquals(timestamp, snapshot.getTimestamp());
    }

    @Test
    public void canSetFiles() {

        final List<SnapshotFile> files = new ArrayList<>();
        snapshot.setFiles(files);

        assertEquals(files, snapshot.getFiles());
    }
}
