package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultStudentService implements StudentService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Student find(final String studentId) {

        return restTemplate.getForObject("http://localhost:8080/hy/participants/{studentId}", Student.class, studentId);
    }
}
