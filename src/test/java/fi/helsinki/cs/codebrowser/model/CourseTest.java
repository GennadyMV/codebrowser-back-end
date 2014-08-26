package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class CourseTest {

    private static final String NAME = "courseName";
    private static final String PLAIN_ID = "coursePlainID";
    private static final String ID = Base64.encodeBase64URLSafeString(NAME.getBytes());

    private Course course;

    @Before
    public void setUp() {

        course = new Course();
    }

    @Test
    public void canSetName() {

        course.setName(NAME);

        assertEquals(NAME, course.getName());
    }

    @Test
    public void canSetPlainId() {

        course.setPlainId(PLAIN_ID);

        assertEquals(PLAIN_ID, course.getPlainId());
    }

    @Test
    public void idIsSetCorrectlyFromName() {

        course.setName(NAME);

        assertEquals(ID, course.getId());
    }

    @Test
    public void canSetExercises() {

        final List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise());

        course.setExercises(exercises);
    }

    @Test
    public void exercisesInitializeAsEmptyList() {

        assertNotNull(course.getExercises());
        assertEquals(0, course.getExercises().size());
    }

    @Test
    public void canAddExerciseWithoutPreviousExercises() {

        final Exercise exercise = new Exercise();

        course.addExercise(exercise);

        assertEquals(1, course.getExercises().size());
        assertEquals(exercise, course.getExercises().get(0));
    }

    @Test
    public void canNotAddSameExerciseMultipleTimes() {

        final Exercise exercise = new Exercise();

        course.addExercise(exercise);
        course.addExercise(exercise);

        assertEquals(1, course.getExercises().size());
    }
}
