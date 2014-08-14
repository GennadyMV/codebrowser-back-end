package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.service.CourseService;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET, value = "courses")
    public Collection<Course> list() throws IOException {

        return courseService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "students/{studentId}/courses")
    public Collection<Course> list(@PathVariable final String studentId) {

        return courseService.findAllByStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "students/{studentId}/courses/{courseId}")
    public Course read(@PathVariable final String studentId, @PathVariable final String courseId) {

        return courseService.find(studentId, courseId);
    }
}
