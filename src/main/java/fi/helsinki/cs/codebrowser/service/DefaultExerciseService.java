package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultExerciseService implements ExerciseService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Autowired
    private CourseService courseService;

    private final JsonMapper mapper = new JsonMapper();

    @Override
    public Collection<Exercise> findAllBy(final String courseId) throws IOException {

        return courseService.findBy(courseId).getExercises();
    }

    @Override
    public Collection<Exercise> findAllBy(final String studentId, final String courseId) throws IOException {

        final String json = restTemplate.getForObject("{studentId}/courses/{courseId}/exercises",
                                                      String.class, studentId, courseId);

        return mapper.readValueToList(json, Exercise.class);
    }

    @Override
    public Exercise findBy(final String courseId, final String exerciseId) throws IOException {

        final Collection<Exercise> exercises = findAllBy(courseId);

        for (Exercise exercise : exercises) {
            if (exercise.getId().equals(exerciseId)) {
                return exercise;
            }
        }

        return null;
    }

    @Override
    public Exercise findBy(final String studentId, final String courseId, final String exerciseId) throws IOException {

        final String json = restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}",
                                                      String.class, studentId, courseId, exerciseId);

        return mapper.readValue(json, Exercise.class);
    }
}
