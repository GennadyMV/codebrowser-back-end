package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.util.Collection;

public interface CourseService {

    Collection<Course> findAll(String studentId);
    Course find(String studentId, String courseId);

}
