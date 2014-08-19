package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SnapshotTest {

    private static final String TYPE = "snapshotType";
    private static final String ID = "snapshotID";

    private Snapshot snapshot;

    @Before
    public void setUp() {

        snapshot = new Snapshot();
    }

    @Test
    public void canSetType() {

        snapshot.setType(TYPE);

        assertEquals(TYPE, snapshot.getType());
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

    @Test
    public void canSetExercises() {

        final Exercise exercise = new Exercise();
        snapshot.setExercise(exercise);

        assertEquals(exercise, snapshot.getExercise());
    }

    @Test
    public void canSetCourse() {

        final Course course = new Course();
        snapshot.setCourse(course);

        assertEquals(course, snapshot.getCourse());
    }

    @Test
    public void canSetCompilesBoolean() {

        snapshot.setCompiles(true);

        assertTrue(snapshot.getCompiles());
    }

    @Test
    public void comparedBasedOnTimestamp() {

        final Snapshot s1 = new Snapshot();
        s1.setTimestamp(new Date(1L));

        final Snapshot s2 = new Snapshot();
        s2.setTimestamp(new Date(2L));

        final Snapshot s3 = new Snapshot();
        s3.setTimestamp(new Date(2L));

        assertTrue(s1.compareTo(s2) < 0);
        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s2.compareTo(s3) == 0);

    }

}
