package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.web.client.HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication;
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

    private JsonNode fetchJson(final String url, final String... parameters) throws IOException {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory =
              (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) tmcRestTemplate.getRequestFactory();

        for (String parameter : parameters) {
            final String[] split = parameter.split("=");

            requestFactory.addParameter(split[0], split[1]);
        }

        final String json = tmcRestTemplate.getForObject(url, String.class);
        return mapper.readTree(json);
    }

    @Override
    public Collection<Course> findAll() throws IOException {

        final JsonNode rootNode = fetchJson("courses.json", "api_version=7");

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

        final JsonNode rootNode = fetchJson(String.format("courses/%s.json", courseId), "api_version=7");

        final Course course = mapper.treeToValue(rootNode.path("course"), Course.class);
        return course;
    }

    @Override
    public Course findBy(final String studentId, final String courseId) {

        return snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}",
                                                 Course.class, studentId, courseId);
    }
}
