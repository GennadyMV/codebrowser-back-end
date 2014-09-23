package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentService {

    Collection<Student> findAll(String instanceId) throws IOException;
    Collection<Student> findAllBy(String instanceId, String courseId) throws IOException;
    Collection<Student> findAllBy(String instanceId, String courseId, String exerciseId) throws IOException;

    Student find(String instanceId, String courseId, String exerciseId, String studentId) throws IOException;
    Student findByInstance(String instanceId, String studentId) throws IOException;

}
