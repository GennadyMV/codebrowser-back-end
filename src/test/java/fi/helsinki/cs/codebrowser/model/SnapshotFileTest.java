package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class SnapshotFileTest {

    private static final String ID = "snapshotFileID";
    private static final String NAME = "snapshotFileName";
    private static final String PATH = "snapshotFilePath";

    private SnapshotFile snapshotFile;

    @Before
    public void setUp() {

        snapshotFile = new SnapshotFile(ID, NAME, PATH);
    }

    @Test
    public void constructorSetsValuesCorrectly() {

        assertEquals(ID, snapshotFile.getId());
        assertEquals(NAME, snapshotFile.getName());
    }
}
