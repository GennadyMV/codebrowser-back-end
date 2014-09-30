package fi.helsinki.cs.codebrowser.controller;

import com.fasterxml.jackson.annotation.JsonView;

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
public final class CourseController {

    @Autowired
    private CourseService courseService;

    @JsonView(Course.Default.class)
    @RequestMapping(value = "{instanceId}/courses")
    public Collection<Course> list(@PathVariable final String instanceId) throws IOException {

        return courseService.findAll(instanceId);
    }

    @JsonView(Course.WithExercises.class)
    @RequestMapping(value = "{instanceId}/students/{studentId}/courses")
    public Collection<Course> list(@PathVariable final String instanceId,
                                   @PathVariable final String studentId) throws IOException {

        return courseService.findAllBy(instanceId, studentId);
    }

    @JsonView(Course.WithExercises.class)
    @RequestMapping(value = "{instanceId}/courses/{courseId}")
    public Course read(@PathVariable final String instanceId,
                       @PathVariable final String courseId) throws IOException {

        return courseService.findBy(instanceId, courseId);
    }

    @JsonView(Course.WithExercises.class)
    @RequestMapping(value = "{instanceId}/students/{studentId}/courses/{courseId}")
    public Course read(@PathVariable final String instanceId,
                       @PathVariable final String studentId,
                       @PathVariable final String courseId) throws IOException {

        return courseService.findBy(instanceId, studentId, courseId);
    }
}
