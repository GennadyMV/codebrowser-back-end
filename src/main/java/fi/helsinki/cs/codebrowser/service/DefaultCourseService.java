package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService implements CourseService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Autowired
    private TmcApiRestTemplate tmcRestTemplate;

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
    public Collection<Course> findAllBy(final String studentId) {

        return snapshotRestTemplate.getForObject("{studentId}/courses",
                                                 List.class, studentId);
    }

    @Override
    public Course findBy(final String courseId) throws IOException {

        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s.json", courseId), "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final Course course = mapper.treeToValue(rootNode.path("course"), Course.class);
        return course;
    }

    @Override
    public Course findBy(final String studentId, final String courseId) {

        return snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}",
                                                 Course.class, studentId, courseId);
    }
}
