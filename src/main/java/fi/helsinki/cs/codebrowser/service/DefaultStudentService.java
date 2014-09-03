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

    private Exercise getCourseExerciseById(final String instance, final String courseId, final String exerciseId) throws IOException {

        final Collection<Exercise> exercises = exerciseService.findAllBy(instance, courseId);

        // Find exercise with ID
        for (Exercise exercise : exercises) {
            if (exercise.getId().equals(exerciseId)) {
                return exercise;
            }
        }

        throw new NotFoundException();
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
    public Collection<Student> findAll(final String instance) throws IOException {

        return Arrays.asList(snapshotRestTemplate.getForObject("{instance}/participants", Student[].class, instance));
    }

    @Override
    public Collection<Student> findAllBy(final String instance, final String courseId) throws IOException {

        final Course course = courseService.findBy(instance, courseId);
        final String json = tmcRestTemplate.fetchJson(String.format("%s/courses/%s/points.json", instance, course.getPlainId()),
                                                      "api_version=7");

        return mapper.readSubElementValueToList(json, Student.class, "users");
    }

    @Override
    public Collection<Student> findAllBy(final String instance, final String courseId, final String exerciseId) throws IOException {

        final Exercise exercise = getCourseExerciseById(instance, courseId, exerciseId);

        final String json = tmcRestTemplate.fetchJson(String.format("%s/exercises/%s.json", instance, exercise.getPlainId()),
                                                      "api_version=7");

        final List<TmcSubmission> submissions = mapper.readSubElementValueToList(json, TmcSubmission.class, "submissions");

        final Collection<Student> courseStudents = findAllBy(instance, courseId);

        return studentsWithSubmissions(courseStudents, submissions);
    }

    @Override
    public Student find(final String instance, final String courseId, final String exerciseId, final String studentId) throws IOException {

        final Collection<Student> students = findAllBy(instance, courseId, exerciseId);

        // Find student by ID
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public Student findByCourse(final String instance, final String courseId, final String studentId) throws IOException {

        final Collection<Student> students = findAllBy(instance, courseId);

        // Find student by ID
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public Student findByInstance(final String instance, final String studentId) throws IOException {

        return snapshotRestTemplate.getForObject("{instance}/participants/{studentId}", Student.class, instance, studentId);
    }
}
