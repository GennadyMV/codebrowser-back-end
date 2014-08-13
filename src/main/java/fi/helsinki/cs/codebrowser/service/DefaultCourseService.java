package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService implements CourseService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Collection<Course> findAll(final String studentId) {

        return restTemplate.getForObject("{studentId}/courses",
                                         List.class, studentId);
    }

    @Override
    public Course find(final String studentId, final String courseId) {

        return restTemplate.getForObject("{studentId}/courses/{courseId}",
                                         Course.class, studentId, courseId);
    }
}
