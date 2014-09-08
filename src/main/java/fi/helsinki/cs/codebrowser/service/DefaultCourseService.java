package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultCourseService implements CourseService {

    @Autowired
    private TmcApiRestTemplate tmcRestTemplate;

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    private final JsonMapper mapper = new JsonMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Collection<Course> findAll(final String instanceId) throws IOException {

        final String json = tmcRestTemplate.fetchJson(String.format("%s/courses.json", instanceId), "api_version=7");

        return mapper.readSubElementValueToList(json, Course.class, "courses");
    }

    @Override
    public Collection<Course> findAllBy(final String instanceId, final String studentId) throws IOException {

        return Arrays.asList(snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses",
                                                               Course[].class,
                                                               instanceId,
                                                               studentId));
    }

    @Override
    public Course findBy(final String instanceId, final String courseId) throws IOException {

        final String courseName = new String(Base64.decodeBase64(courseId));
        final Collection<Course> tmcCourses = findAll(instanceId);

        Course tmcCourse = null;

        // Find course with course name
        for (Course course : tmcCourses) {
            if (course.getName().equals(courseName)) {
                tmcCourse = course;
            }
        }

        if (tmcCourse == null) {
            throw new NotFoundException();
        }

        final String json = tmcRestTemplate.fetchJson(String.format("%s/courses/%s.json", instanceId, tmcCourse.getPlainId()),
                                                      "api_version=7");

        return mapper.readSubElementValue(json, Course.class, "course");
    }

    @Override
    public Course findBy(final String instanceId, final String studentId, final String courseId) throws IOException {

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}",
                                                 Course.class,
                                                 instanceId,
                                                 studentId,
                                                 courseId);
    }
}
