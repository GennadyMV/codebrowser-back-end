package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService implements CourseService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Autowired
    private TmcApiRestTemplate tmcRestTemplate;

    private final JsonMapper mapper = new JsonMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Collection<Course> findAll() throws IOException {

        final String json = tmcRestTemplate.fetchJson("courses.json", "api_version=7");

        return mapper.readSubElementValueToList(json, Course.class, "courses");
    }

    @Override
    public Collection<Course> findAllBy(final String studentId) throws IOException {

        final String json = snapshotRestTemplate.getForObject("{studentId}/courses",
                                                              String.class, studentId);

        return mapper.readValueToList(json, Course.class);
    }

    @Override
    public Course findBy(final String courseId) throws IOException {

        final String courseName = new String(Base64.decodeBase64(courseId));

        final Collection<Course> courses = findAll();
        Course course = null;

        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
            }
        }

        if (course == null) {
            return null;
        }

        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s.json", course.getPlainId()), "api_version=7");

        return mapper.readSubElementValue(json, Course.class, "course");
    }

    @Override
    public Course findBy(final String studentId, final String courseId) throws IOException {

        final String json = snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}",
                                                              String.class, studentId, courseId);

        return mapper.readValue(json, Course.class);
    }
}
