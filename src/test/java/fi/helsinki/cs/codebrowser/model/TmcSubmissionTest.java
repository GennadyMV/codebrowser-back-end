package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class TmcSubmissionTest {

    private static final String EXERCISE = "submissionExerciseName";
    private static final String ID = "submissionID";
    private static final String COURSE = "submissionCourseID";
    private static final String USER = "submissionUserID";

    private TmcSubmission submission;

    @Before
    public void setUp() {

        submission = new TmcSubmission();
    }

    @Test
    public void canSetExerciseName() {

        submission.setExerciseName(EXERCISE);

        assertEquals(EXERCISE, submission.getExerciseName());
    }

    @Test
    public void canSetId() {

        submission.setId(ID);

        assertEquals(ID, submission.getId());
    }

    @Test
    public void canSetCourseId() {

        submission.setCourseId(COURSE);

        assertEquals(COURSE, submission.getCourseId());
    }

    @Test
    public void canSetUserId() {

        submission.setUserId(USER);

        assertEquals(USER, submission.getUserId());
    }
}
