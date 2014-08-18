package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentService {

    Collection<Student> findAll() throws IOException;
    Collection<Student> findAllBy(String courseId) throws IOException;
    Collection<Student> findAllBy(String courseId, String exerciseId) throws IOException;

    Student find(String courseId, String exerciseId, String studentId) throws IOException;
    Student find(String courseId, String studentId) throws IOException;
    Student find(String studentId);

}
