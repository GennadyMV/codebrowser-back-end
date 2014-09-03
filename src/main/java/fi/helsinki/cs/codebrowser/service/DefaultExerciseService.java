package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultExerciseService implements ExerciseService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Autowired
    private CourseService courseService;

    @Override
    public Collection<Exercise> findAllBy(final String instance, final String courseId) throws IOException {

        return courseService.findBy(instance, courseId).getExercises();
    }

    @Override
    public Collection<Exercise> findAllBy(final String instance,
                                          final String studentId,
                                          final String courseId) throws IOException {

        return Arrays.asList(restTemplate.getForObject("{instance}/participants/{studentId}/courses/{courseId}/exercises",
                                                       Exercise[].class,
                                                       instance,
                                                       studentId,
                                                       courseId));
    }

    @Override
    public Exercise findBy(final String instance, final String courseId, final String exerciseId) throws IOException {

        final Collection<Exercise> exercises = findAllBy(instance, courseId);

        // Find exercise with ID
        for (Exercise exercise : exercises) {
            if (exercise.getId().equals(exerciseId)) {
                return exercise;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public Exercise findBy(final String instance,
                           final String studentId,
                           final String courseId,
                           final String exerciseId) throws IOException {

        return restTemplate.getForObject("{instance}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}",
                                         Exercise.class,
                                         instance,
                                         studentId,
                                         courseId,
                                         exerciseId);
    }
}
