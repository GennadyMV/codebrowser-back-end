package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Student find(final String studentId) {

        return restTemplate.getForObject("{studentId}",
                                         Student.class, studentId);
    }
}
