package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.io.IOException;
import java.util.Collection;

public interface CourseService {

    Collection<Course> findAll() throws IOException;
    Collection<Course> findAllByStudent(String studentId);
    Course find(String studentId, String courseId);

}
