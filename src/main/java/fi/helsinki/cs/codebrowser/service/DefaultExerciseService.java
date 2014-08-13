package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Exercise;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultExerciseService implements ExerciseService {

    @Value("${snapshot.api.url}")
    private String baseURL;

    private final RestTemplate restTemplate = new RestTemplate();

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
