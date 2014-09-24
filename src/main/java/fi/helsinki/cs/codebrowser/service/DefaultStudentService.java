package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.model.TmcSubmission;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultStudentService implements StudentService {

    @Autowired
    private TmcApiRestTemplate tmcRestTemplate;

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private CourseService courseService;

    private final JsonMapper mapper = new JsonMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private Collection<Student> studentsWithSubmissions(final Collection<Student> courseStudents,
                                                        final List<TmcSubmission> submissions) {

        final Set<String> submitters = new HashSet<>();

        for (TmcSubmission submission : submissions) {
            submitters.add(submission.getUserId());
        }

        final Collection<Student> students = new ArrayList<>();

        for (Student student : courseStudents) {
            if (submitters.contains(student.getPlainId())) {
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public Collection<Student> findAll(final String instanceId) throws IOException {

        final String json = tmcRestTemplate.fetchJson(String.format("%s/participants.json", instanceId), "api_version=7");

        // Fetch all students from TMC API
        final List<Student> tmcStudents = mapper.readSubElementValueToList(json, Student.class, "participants");

        // Fetch students from Snapshot API
        final List<Student> snapshotStudents = Arrays.asList(snapshotRestTemplate.getForObject("{instanceId}/participants", Student[].class, instanceId));

        final Collection<Student> students = new ArrayList<>();

        // Return students which are found both in TMC API and Snapshot API
        for (Student student : tmcStudents) {
            if (snapshotStudents.contains(student)) {
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public Collection<Student> findAllBy(final String instanceId, final String courseId) throws IOException {

        final Course course = courseService.findBy(instanceId, courseId);
        final String json = tmcRestTemplate.fetchJson(String.format("%s/courses/%s/points.json", instanceId, course.getPlainId()),
                                                      "api_version=7");

        return mapper.readSubElementValueToList(json, Student.class, "users");
    }

    @Override
    public Collection<Student> findAllBy(final String instanceId, final String courseId, final String exerciseId) throws IOException {

        // Fetch exercise
        final Exercise exercise = exerciseService.findBy(instanceId, courseId, exerciseId);

        final String json = tmcRestTemplate.fetchJson(String.format("%s/exercises/%s.json", instanceId, exercise.getPlainId()),
                                                      "api_version=7");

        // Fetch student submissions containing student IDs
        final List<TmcSubmission> submissions = mapper.readSubElementValueToList(json, TmcSubmission.class, "submissions");

        // Fetch all students from the course
        final Collection<Student> courseStudents = findAllBy(instanceId, courseId);

        // Select only students who have been working on the exercise
        final Collection<Student> submissionStudents = studentsWithSubmissions(courseStudents, submissions);

        // Fetch all students from TMC participants list to get their first and last name
        final String tmcJson = tmcRestTemplate.fetchJson(String.format("%s/participants.json", instanceId), "api_version=7");
        final List<Student> tmcStudents = mapper.readSubElementValueToList(tmcJson, Student.class, "participants");

        final Collection<Student> students = new ArrayList<>();

        for (Student student : tmcStudents) {
            if (submissionStudents.contains(student)) {
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public Student find(final String instanceId, final String courseId, final String exerciseId, final String studentId) throws IOException {

        final Collection<Student> students = findAllBy(instanceId, courseId, exerciseId);

        // Find student by ID
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public Student findByInstance(final String instanceId, final String studentId) throws IOException {

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}", Student.class, instanceId, studentId);
    }
}
