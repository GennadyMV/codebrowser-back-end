package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentService {

    Collection<Student> findAll(String instance) throws IOException;
    Collection<Student> findAllBy(String instance, String courseId) throws IOException;
    Collection<Student> findAllBy(String instance, String courseId, String exerciseId) throws IOException;

    Student find(String instance, String courseId, String exerciseId, String studentId) throws IOException;
    Student findByCourse(String instance, String courseId, String studentId) throws IOException;
    Student findByInstance(String instance, String studentId) throws IOException;

}
