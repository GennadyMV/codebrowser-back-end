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
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "courses")
    public Collection<Course> list() throws IOException {

        return courseService.findAll();
    }

    @RequestMapping(value = "students/{studentId}/courses")
    public Collection<Course> list(@PathVariable final String studentId) throws IOException {

        return courseService.findAllBy(studentId);
    }

    @RequestMapping(value = "courses/{courseId}")
    public Course read(@PathVariable final String courseId) throws IOException {

        return courseService.findBy(courseId);
    }

    @RequestMapping(value = "students/{studentId}/courses/{courseId}")
    public Course read(@PathVariable final String studentId, @PathVariable final String courseId) throws IOException {

        return courseService.findBy(studentId, courseId);
    }
}
