package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultStudentService implements StudentService {

    @Value("${snapshot.api.url}")
    private String baseURL;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Student find(final String studentId) {

        return restTemplate.getForObject(baseURL + "{studentId}",
                                         Student.class, studentId);
    }
}
