package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultExerciseServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private final BackendServerStub server = new BackendServerStub();

    @Autowired
    private ExerciseService exerciseService;

    private void assertEitherHasName(final List<Exercise> exercises, final String name) {

        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name)) {
                return;
            }
        }

        fail("No element has name " + name);
    }

    @Before
    public void setUp() {

        server.reset();
    }

    @Test
    public void findAllByInstanceAndCourseReturnsCorrectExercises() throws IOException {

        final List<Exercise> exercises = (List<Exercise>) exerciseService.findAllBy(INSTANCE_ID, COURSE_ID);

        assertEquals(2, exercises.size());
        assertEitherHasName(exercises, EXERCISE_NAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceAndCourseThrowsNotFoundWhenNoMatchingItemIsFound() throws IOException {

        exerciseService.findAllBy(NO_SUCH_ID, NO_SUCH_ID);
    }

    @Test
    public void findAllByInstanceStudentAndCourseReturnsCorrectExercises() throws IOException {

        final List<Exercise> exercises = (List<Exercise>) exerciseService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID);

        assertEquals(2, exercises.size());
        assertEitherHasName(exercises, EXERCISE_NAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceStudentAndCourseThrowsNotFoundWhenNoMatchingItemIsFound() throws IOException {

        exerciseService.findAllBy(NO_SUCH_ID, NO_SUCH_ID, NO_SUCH_ID);
    }

    @Test
    public void findByInstanceCourseAndExerciseReturnsCorrectExercise() throws IOException {

        final Exercise exercise = exerciseService.findBy(INSTANCE_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(EXERCISE_NAME, exercise.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findByInstanceCourseAndExerciseThrowsNotFoundWhenNoMatchingItemIsFound() throws IOException {

        exerciseService.findBy(NO_SUCH_ID, NO_SUCH_ID, NO_SUCH_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findByInstanceCourseAndExerciseThrowsNotFoundWhenNoMatchingItemIsFoundEvenIfCourseExists() throws IOException {

        exerciseService.findBy(INSTANCE_ID, COURSE_ID, NO_SUCH_ID);
    }

    @Test
    public void findByInstanceStudentCourseAndExerciseReturnsCorrectExercise() throws IOException {

        final Exercise exercise = exerciseService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(EXERCISE_NAME, exercise.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findByInstanceStudentCourseAndExerciseThrowsNotFoundWhenNoMatchingItemIsFound() throws IOException {

        exerciseService.findBy(NO_SUCH_ID, NO_SUCH_ID, NO_SUCH_ID, NO_SUCH_ID);
    }
}
