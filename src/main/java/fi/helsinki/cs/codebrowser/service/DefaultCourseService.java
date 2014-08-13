package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultCourseService implements CourseService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Collection<Course> findAll(final String studentId) {

        return restTemplate.getForObject("http://localhost:8080/hy/participants/{studentId}/courses", List.class, studentId);
    }

    @Override
    public Course find(final String studentId, final String courseId) {

        return restTemplate.getForObject("http://localhost:8080/hy/participants/{studentId}/courses/{courseId}", Course.class, studentId, courseId);
    }
}
