package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Course;

import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultCourseServiceTest {

    private static final String ANY = ".*";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APP_JSON = "application/json";

    private static final String INSTANCE = "hy";
    private static final String STUDENT = "111";
    private static final String COURSE = "czIwMTMtd2VwYQ";
    private static final String API_VERSION = "api_version=7";

    private static final String COURSES_JSON_URL = "/hy/courses.json";
    private static final String COURSE_JSON_URL = "/hy/courses/22.json";
    private static final String STUDENT_COURSES_URL = "/hy/participants/111/courses";
    private static final String STUDENT_COURSE_URL = "/hy/participants/111/courses/czIwMTMtd2VwYQ";

    private static final String COURSES_JSON = "{\"api_version\":7,\"courses\":[{\"id\":22,\"name\":\"s2013-wepa\",\"details_url\":\"detUrl1\",\"unlock_url\":\"unlockUrl1\",\"reviews_url\":\"reviewsUrl1\",\"comet_url\":\"cometUrl1\",\"spyware_urls\":[\"spywareUrl1\"]},{\"id\":2,\"name\":\"name2\",\"details_url\":\"detailsUrl2\",\"unlock_url\":\"unlockUrl2\",\"reviews_url\":\"reviewsUrl2\",\"comet_url\":\"cometUrl2\",\"spyware_urls\":[\"spywareUrl2\"]}]}";
    private static final String COURSE_JSON = "{\"api_version\":7,\"course\":{\"id\":22,\"name\":\"s2013-wepa\",\"details_url\":\"a\",\"unlock_url\":\"a\",\"reviews_url\":\"a\",\"comet_url\":\"a\",\"spyware_urls\":[\"a\"],\"unlockables\":[],\"exercises\":[{\"id\":1234,\"name\":\"aa\",\"locked\":false,\"deadline_description\":\"2014-03-14 23:59:00 +0200\",\"deadline\":\"2014-03-14T23:59:00+02:00\",\"checksum\":\"11223344556677889900\",\"return_url\":\"a\",\"zip_url\":\"a\",\"returnable\":true,\"requires_review\":false,\"attempted\":false,\"completed\":false,\"reviewed\":false,\"all_review_points_given\":true,\"memory_limit\":null,\"runtime_params\":[],\"solution_zip_url\":\"a\",\"exercise_submissions_url\":\"a\"}]}}";
    private static final String STUDENT_COURSES_JSON = "[{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]}]";
    private static final String STUDENT_COURSE_JSON = "{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"}]}";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    private CourseService courseService;

    @Test
    public void findAllRetrievesJsonAndParsesItToCourses() throws IOException {

        stubFor(get(urlMatching(ANY))
                .willReturn(aResponse()
                            .withBody(COURSES_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        final List<Course> courses = (List<Course>) courseService.findAll(INSTANCE);

        assertEquals(2, courses.size());
        assertEquals("s2013-wepa", courses.get(0).getName());
        assertEquals("name2", courses.get(1).getName());

        verify(getRequestedFor(urlMatching(COURSES_JSON_URL + ANY)));
    }

    @Test
    public void findAllByInstanceAndStudentRetrievesJsonAndParsesItToCourses() throws IOException {

        stubFor(get(urlMatching(ANY))
                .willReturn(aResponse()
                            .withBody(STUDENT_COURSES_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        final List<Course> courses = (List<Course>) courseService.findAllBy(INSTANCE, STUDENT);

        assertEquals(1, courses.size());
        assertEquals("s2013-wepa", courses.get(0).getName());

        verify(getRequestedFor(urlMatching(STUDENT_COURSES_URL + ANY)));
    }

    @Test
    public void findByInstanceCourseAndStudentRetrievesJsonAndParsesItToCourse() throws IOException {

        stubFor(get(urlMatching(ANY))
                .willReturn(aResponse()
                            .withBody(STUDENT_COURSE_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        final Course course = courseService.findBy(INSTANCE, STUDENT, COURSE);

        assertEquals("s2013-wepa", course.getName());

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_URL + ANY)));
    }

    @Test
    public void findByInstanceAndCourseRetrievesJsonAndParsesItToCourses() throws IOException {
        stubFor(get(urlMatching(COURSES_JSON_URL + ANY))
                .willReturn(aResponse()
                            .withBody(COURSES_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        stubFor(get(urlMatching(COURSE_JSON_URL + ANY))
                .willReturn(aResponse()
                            .withBody(COURSE_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        final Course course = courseService.findBy(INSTANCE, COURSE);

        assertEquals("s2013-wepa", course.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findByInstanceAndCourseThrowsNotFoundWhenNoMatchingCourseIsFound() throws IOException {

        stubFor(get(urlMatching(COURSES_JSON_URL + ANY))
                .willReturn(aResponse()
                            .withBody(COURSES_JSON)
                            .withHeader(CONTENT_TYPE, APP_JSON)));

        courseService.findBy(INSTANCE, "NoSuchCourse");
    }
}
