package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnapshotFileTest {

    private static final String ID = "snapshotFileId";
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
        assertEquals(PATH, snapshotFile.getFilepath());
    }

    @Test
    public void canSetFilePath() {

        snapshotFile.setFilepath("newFilePath");

        assertEquals("newFilePath", snapshotFile.getFilepath());
    }

    @Test public void canSetFileSize() {

        snapshotFile.setFilesize(10L);

        assertEquals(10L, snapshotFile.getFilesize());
    }
}
