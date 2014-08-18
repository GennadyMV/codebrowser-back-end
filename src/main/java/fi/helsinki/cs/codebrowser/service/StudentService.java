package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentService {

    Collection<Student> findAll() throws IOException;
    Student find(String studentId);

}
