package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.io.IOException;
import java.util.Collection;

public interface CourseService {

    Collection<Course> findAll(String instance) throws IOException;
    Collection<Course> findAllBy(String instance, String studentId) throws IOException;

    Course findBy(String instance, String courseId) throws IOException;
    Course findBy(String instance, String studentId, String courseId) throws IOException;

}
