package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultExerciseService implements ExerciseService {

    @Value("${snapshot.api.url}")
    private String baseURL;

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Collection<Exercise> findAll(final String studentId, final String courseId) {

        return restTemplate.getForObject(baseURL + "{studentId}/courses/{courseId}/exercises",
                                         List.class, studentId, courseId);
    }

    @Override
    public Exercise find(final String studentId, final String courseId, final String exerciseId) {

        return restTemplate.getForObject(baseURL + "{studentId}/courses/{courseId}/exercises/{exerciseId}",
                                         Exercise.class, studentId, courseId, exerciseId);
    }
}
