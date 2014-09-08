package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.io.IOException;
import java.util.Collection;

public interface CourseService {

    Collection<Course> findAll(String instanceId) throws IOException;
    Collection<Course> findAllBy(String instanceId, String studentId) throws IOException;

    Course findBy(String instanceId, String courseId) throws IOException;
    Course findBy(String instanceId, String studentId, String courseId) throws IOException;

}
