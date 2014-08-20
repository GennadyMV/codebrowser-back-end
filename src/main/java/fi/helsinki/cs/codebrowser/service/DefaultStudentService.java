package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.model.Submission;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;
import fi.helsinki.cs.codebrowser.web.client.TmcApiRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private final JsonMapper mapper = new JsonMapper();

    @PostConstruct
    private void initialise() {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private Exercise getCourseExerciseById(final String courseId, final String exerciseId) throws IOException {

        final Collection<Exercise> exercises = exerciseService.findAllBy(courseId);

        Exercise exercise = null;
        for (Exercise ex : exercises) {
            if (ex.getId().equals(exerciseId)) {
                exercise = ex;
            }
        }

        return exercise;
    }

    private Collection<Student> studentsWithSubmissions(final Collection<Student> courseStudents, final List<Submission> submissions) {

        final Set<String> submitters = new HashSet<>();
        for (Submission submission : submissions) {
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
    public Collection<Student> findAll() throws IOException {

        final String json = snapshotRestTemplate.getForObject("#", String.class);

        return mapper.readValueToList(json, Student.class);
    }

    @Override
    public Collection<Student> findAllBy(final String courseId) throws IOException {

        final Course course = courseService.findBy(courseId);
        final String json = tmcRestTemplate.fetchJson(String.format("courses/%s/points.json", course.getPlainId()), "api_version=7");

        return mapper.readSubElementValueToList(json, Student.class, "users");
    }

    @Override
    public Collection<Student> findAllBy(final String courseId, final String exerciseId) throws IOException {

        final Exercise exercise = getCourseExerciseById(courseId, exerciseId);

        if (exercise == null) {
            return null;
        }

        final String json = tmcRestTemplate.fetchJson(String.format("exercises/%s.json", exercise.getPlainId()), "api_version=7");
        final List<Submission> submissions = mapper.readSubElementValueToList(json, Submission.class, "submissions");

        final Collection<Student> courseStudents = findAllBy(courseId);

        return studentsWithSubmissions(courseStudents, submissions);
    }

    @Override
    public Student find(final String courseId, final String exerciseId, final String studentId) throws IOException {

        final Collection<Student> students = findAllBy(courseId, exerciseId);

        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    @Override
    public Student find(final String courseId, final String studentId) throws IOException {

        final Collection<Student> students = findAllBy(courseId);

        for (Student student : students) {
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
