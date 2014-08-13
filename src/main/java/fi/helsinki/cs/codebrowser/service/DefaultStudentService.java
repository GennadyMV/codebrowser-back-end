package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    @Value("${snapshot.api.url}")
    private String baseURL;

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Student find(final String studentId) {

        return restTemplate.getForObject(baseURL + "{studentId}",
                                         Student.class, studentId);
    }
}
