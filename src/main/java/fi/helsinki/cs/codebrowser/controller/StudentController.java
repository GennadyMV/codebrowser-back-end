package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.service.StudentService;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
public final class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "{instance}/students")
    public Collection<Student> list(@PathVariable final String instance) throws IOException {

        return studentService.findAll(instance);
    }

    @RequestMapping(value = "{instance}/courses/{courseId}/students")
    public Collection<Student> list(@PathVariable final String instance,
                                     @PathVariable final String courseId) throws IOException {

        return studentService.findAllBy(instance, courseId);
    }

    @RequestMapping(value = "{instance}/courses/{courseId}/exercises/{exerciseId}/students")
    public Collection<Student> list(@PathVariable final String instance,
                                    @PathVariable final String courseId,
                                    @PathVariable final String exerciseId) throws IOException {

        return studentService.findAllBy(instance, courseId, exerciseId);
    }

    @RequestMapping(value = "{instance}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}")
    public Student read(@PathVariable final String instance,
                        @PathVariable final String courseId,
                        @PathVariable final String exerciseId,
                        @PathVariable final String studentId) throws IOException {

        return studentService.find(instance, courseId, exerciseId, studentId);
    }

    @RequestMapping(value = "{instance}/courses/{courseId}/students/{studentId}")
    public Student read(@PathVariable final String instance,
                        @PathVariable final String courseId,
                        @PathVariable final String studentId) throws IOException {

        return studentService.findByCourse(instance, courseId, studentId);
    }

    @RequestMapping(value = "{instance}/students/{studentId}")
    public Student read2(@PathVariable final String instance, @PathVariable final String studentId) throws IOException {

        return studentService.findByInstance(instance, studentId);
    }
}
