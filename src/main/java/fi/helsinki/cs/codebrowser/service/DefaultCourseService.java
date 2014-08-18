package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    @Autowired
    private StudentService studentService;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Collection<Course> findAll() throws IOException {

        final String json = tmcRestTemplate.fetchJson("courses.json", "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final Course[] courses = mapper.treeToValue(rootNode.path("courses"), Course[].class);
        return Arrays.asList(courses);
    }

    @Override
    public Collection<Course> findAllBy(final String studentId) throws IOException {

        final Student student = studentService.find(studentId);
        final String username = Base64.encodeBase64URLSafeString(student.getName().getBytes());

        return snapshotRestTemplate.getForObject("{username}/courses",
                                                 List.class, username);
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

        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s.json", course.getId()), "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        course = mapper.treeToValue(rootNode.path("course"), Course.class);
        return course;
    }

    @Override
    public Course findBy(final String studentId, final String courseId) {

        return snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}",
                                                 Course.class, studentId, courseId);
    }
}
