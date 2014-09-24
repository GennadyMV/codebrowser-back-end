package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.ClassRule;
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
public final class DefaultStudentServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final BackendServerStub SERVER = new BackendServerStub();

    @Autowired
    StudentService studentService;

    @BeforeClass
    public static void setUpClass() {

        SERVER.initialiseServer();
    }

    private void assertOneHasUsername(final Collection<Student> students, final String username) {

        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return;
            }
        }

        fail("No element has name " + username);
    }

    @Test
    public void findAllReturnsCorrectStudents() throws IOException {

        final Collection<Student> students =  studentService.findAll(INSTANCE_ID);

        assertEquals(2, students.size());
        assertOneHasUsername(students, STUDENT_USERNAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllThrowsNotFoundIfNoStudentsAreFound() throws IOException {

        studentService.findAll(NO_SUCH_ID);
    }

    @Test
    public void findAllByInstanceAndCourseReturnsCorrectStudents() throws IOException {

        final Collection<Student> students = studentService.findAllBy(INSTANCE_ID, COURSE_ID);

        assertEquals(1, students.size());
        assertOneHasUsername(students, STUDENT_USERNAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceAndCourseThrowsNotFoundForNonexistantCourse() throws IOException {

        studentService.findAllBy(INSTANCE_ID, NO_SUCH_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceAndCourseThrowsNotFoundForNonexistantInstance() throws IOException {

        studentService.findAllBy(NO_SUCH_ID, COURSE_ID);
    }

    @Test
    public void findAllByInstanceCourseAndExerciseReturnsCorrectStudents() throws IOException {

        final Collection<Student> students = studentService.findAllBy(INSTANCE_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(1, students.size());
        assertOneHasUsername(students, STUDENT_USERNAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceCourseAndExerciseThrowsNotFoundForNonexistantInstance() throws IOException {

        studentService.findAllBy(NO_SUCH_ID, COURSE_ID, EXERCISE_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceCourseAndExerciseThrowsNotFoundForNonexistantCourse() throws IOException {

        studentService.findAllBy(INSTANCE_ID, NO_SUCH_ID, EXERCISE_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByInstanceCourseAndExerciseThrowsNotFoundForNonexistantExercise() throws IOException {

        studentService.findAllBy(INSTANCE_ID, COURSE_ID, NO_SUCH_ID);
    }

    @Test
    public void findReturnsCorrectStudent() throws IOException {

        final Student student = studentService.find(INSTANCE_ID, COURSE_ID, EXERCISE_ID, STUDENT_ID);

        assertEquals(STUDENT_ID, student.getId());
        assertEquals(STUDENT_USERNAME, student.getUsername());
        assertEquals(STUDENT_FIRSTNAME + " " + STUDENT_LASTNAME, student.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findThrowsNotFoundForNonexistantInstance() throws IOException {

        studentService.find(NO_SUCH_ID, COURSE_ID, EXERCISE_ID, STUDENT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findThrowsNotFoundForNonexistantCourse() throws IOException {

        studentService.find(INSTANCE_ID, NO_SUCH_ID, EXERCISE_ID, STUDENT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findThrowsNotFoundForNonexistantExercise() throws IOException {

        studentService.find(INSTANCE_ID, COURSE_ID, NO_SUCH_ID, STUDENT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findThrowsNotFoundForNonexistantStudent() throws IOException {

        studentService.find(INSTANCE_ID, COURSE_ID, EXERCISE_ID, NO_SUCH_ID);
    }

    @Test
    public void findByInstanceReturnsCorrectStudent() throws IOException {

        final Student student = studentService.findByInstance(INSTANCE_ID, STUDENT_ID);

        assertEquals(STUDENT_ID, student.getId());
        assertEquals(STUDENT_USERNAME, student.getUsername());
    }

    @Test(expected = NotFoundException.class)
    public void findByInstaceThrowsNotFoundForNonexistantInstance() throws IOException {

        studentService.findByInstance(NO_SUCH_ID, STUDENT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void findByInstaceThrowsNotFoundForNonexistantStudent() throws IOException {

        studentService.findByInstance(INSTANCE_ID, NO_SUCH_ID);
    }
}
