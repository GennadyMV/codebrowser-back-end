package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

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
    public Collection<Student> findAll() throws IOException {

        final String json = tmcRestTemplate.fetchJson("participants.json", "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final Student[] students = mapper.treeToValue(rootNode.path("participants"), Student[].class);
        return Arrays.asList(students);
    }

    @Override
    public Collection<Student> findAllBy(final String courseId) throws IOException {

        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s/points.json", courseId), "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final Student[] students = mapper.treeToValue(rootNode.path("users"), Student[].class);
        return Arrays.asList(students);
    }

    @Override
    public Student find(final String studentId) {

        return snapshotRestTemplate.getForObject("{studentId}", Student.class, studentId);
    }
}
