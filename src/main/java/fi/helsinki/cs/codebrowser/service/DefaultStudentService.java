package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.model.Submission;
import fi.helsinki.cs.codebrowser.model.TmcParticipant;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private CourseService courseService;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Collection<TmcParticipant> findAll() throws IOException {

        final String json = tmcRestTemplate.fetchJson("participants.json", "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final TmcParticipant[] participants = mapper.treeToValue(rootNode.path("participants"), TmcParticipant[].class);
        return Arrays.asList(participants);
    }

    @Override
    public Collection<TmcParticipant> findAllBy(final String courseId) throws IOException {

        final Course course = courseService.findBy(courseId);

        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s/points.json", course.getPlainId()), "api_version=7");
        final JsonNode rootNode = mapper.readTree(json);

        final TmcParticipant[] participants = mapper.treeToValue(rootNode.path("users"), TmcParticipant[].class);
        return Arrays.asList(participants);
    }

    @Override
    public Collection<TmcParticipant> findAllBy(final String courseId, final String exerciseId) throws IOException {

        final Collection<Exercise> exercises = exerciseService.findAllBy(courseId);

        Exercise exercise = null;

        for (Exercise ex : exercises) {
            if (ex.getId().equals(exerciseId)) {
                exercise = ex;
            }
        }

        if (exercise == null) {
            return null;
        }

        final String json = tmcRestTemplate.fetchJson(String.format("exercises/%s.json", exercise.getPlainId()), "api_version=7");

        final JsonNode rootNode = mapper.readTree(json);

        final Submission[] submissions = mapper.treeToValue(rootNode.path("submissions"), Submission[].class);

        final Collection<TmcParticipant> courseStudents = findAllBy(courseId);
        final Collection<TmcParticipant> students = new ArrayList<>();

        for (TmcParticipant stud : courseStudents) {
            for (Submission sub : submissions) {

                if (stud.getPlainId().equals(sub.getUserId())) {

                    students.add(stud);
                    break;
                }
            }
        }

        return students;
    }

    @Override
    public TmcParticipant find(final String courseId, final String exerciseId, final String studentId) throws IOException {

        final Collection<TmcParticipant> students = findAllBy(courseId, exerciseId);

        for (TmcParticipant student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    @Override
    public TmcParticipant find(final String courseId, final String studentId) throws IOException {

        final Collection<TmcParticipant> students = findAllBy(courseId);

        for (TmcParticipant student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    @Override
    public Student find(final String studentId) throws IOException {

        return snapshotRestTemplate.getForObject("{studentId}", Student.class, studentId);
    }
}
