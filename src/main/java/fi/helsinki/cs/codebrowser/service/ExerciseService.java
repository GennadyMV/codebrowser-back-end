package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Exercise;

import java.io.IOException;
import java.util.Collection;

public interface ExerciseService {

    Collection<Exercise> findAllBy(String instanceId, String courseId) throws IOException;
    Collection<Exercise> findAllBy(String instanceId, String studentId, String courseId) throws IOException;

    Exercise findBy(String instanceId, String courseId, String exerciseId) throws IOException;
    Exercise findBy(String instanceId, String studentId, String courseId, String exerciseId) throws IOException;

}
