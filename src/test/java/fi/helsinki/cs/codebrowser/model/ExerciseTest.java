package fi.helsinki.cs.codebrowser.model;

import org.apache.commons.codec.binary.Base64;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class ExerciseTest {

    private static final String NAME = "exerciseName";
    private static final String PLAIN_ID = "exerciseID";

    private Exercise exercise;

    @Before
    public void setUp() {

        this.exercise = new Exercise();
    }

    @Test
    public void canSetName() {

        exercise.setName(NAME);

        assertEquals(NAME, exercise.getName());
    }

    @Test
    public void canSetPlainId() {

        exercise.setPlainId(PLAIN_ID);

        assertEquals(PLAIN_ID, exercise.getPlainId());
    }

    @Test
    public void idIsSetBasedOnName() {

        exercise.setName(NAME);
        final String id = Base64.encodeBase64URLSafeString(NAME.getBytes());

        assertEquals(id, exercise.getId());
    }
}
