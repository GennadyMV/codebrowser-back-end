package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.io.IOException;
import java.util.Collection;

public interface CourseService {

    Collection<Course> findAll() throws IOException;
    Collection<Course> findAllBy(String studentId) throws IOException;

    Course findBy(String courseId) throws IOException;
    Course findBy(String studentId, String courseId);

}
