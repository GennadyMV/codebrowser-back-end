package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Exercise;

import java.util.Collection;

public interface ExerciseService {

    Collection<Exercise> findAll(String studentId, String courseId);
    Exercise find(String studentId, String courseId, String exerciseId);

}
