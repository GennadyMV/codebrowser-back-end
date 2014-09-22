package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Course;
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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultCourseServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    private CourseService courseService;

    private final BackendServerStub testUtil = new BackendServerStub();

    @Before
    public void setUp() {

        testUtil.reset();
    }

    @Test
    public void findAllRetrievesJsonAndParsesItToCourses() throws IOException {

        final List<Course> courses = (List<Course>) courseService.findAll(INSTANCE_ID);

        assertEquals(COURSES_LENGTH, courses.size());
        assertEquals(COURSE_NAME, courses.get(1).getName());
    }

    @Test
    public void findAllByInstanceAndStudentRetrievesJsonAndParsesItToCourses() throws IOException {

        final List<Course> courses = (List<Course>) courseService.findAllBy(INSTANCE_ID, STUDENT_ID);

        assertEquals(1, courses.size());
        assertEquals(COURSE_NAME, courses.get(0).getName());
    }

    @Test
    public void findByInstanceCourseAndStudentRetrievesJsonAndParsesItToCourse() throws IOException {

        final Course course = courseService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID);

        assertEquals(COURSE_NAME, course.getName());
    }

    @Test
    public void findByInstanceAndCourseRetrievesJsonAndParsesItToCourses() throws IOException {

        final Course course = courseService.findBy(INSTANCE_ID, COURSE_ID);

        assertEquals(COURSE_NAME, course.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findByInstanceAndCourseThrowsNotFoundWhenNoMatchingCourseIsFound() throws IOException {

        courseService.findBy(INSTANCE_ID, "NoSuchCourse");
    }
}
