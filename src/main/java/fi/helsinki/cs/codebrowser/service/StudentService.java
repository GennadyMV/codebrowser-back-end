package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.model.TmcParticipant;

import java.io.IOException;
import java.util.Collection;

public interface StudentService {

    Collection<TmcParticipant> findAll() throws IOException;
    Collection<TmcParticipant> findAllBy(String courseId) throws IOException;
    Collection<TmcParticipant> findAllBy(String courseId, String exerciseId) throws IOException;

    TmcParticipant find(String courseId, String exerciseId, String studentId) throws IOException;
    TmcParticipant find(String courseId, String studentId) throws IOException;
    Student find(String studentId) throws IOException;

}
