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

    @RequestMapping(value = "{instanceId}/students")
    public Collection<Student> list(@PathVariable final String instanceId) throws IOException {

        return studentService.findAll(instanceId);
    }

    @RequestMapping(value = "{instanceId}/courses/{courseId}/students")
    public Collection<Student> list(@PathVariable final String instanceId,
                                    @PathVariable final String courseId) throws IOException {

        return studentService.findAllBy(instanceId, courseId);
    }

    @RequestMapping(value = "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students")
    public Collection<Student> list(@PathVariable final String instanceId,
                                    @PathVariable final String courseId,
                                    @PathVariable final String exerciseId) throws IOException {

        return studentService.findAllBy(instanceId, courseId, exerciseId);
    }

    @RequestMapping(value = "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}")
    public Student read(@PathVariable final String instanceId,
                        @PathVariable final String courseId,
                        @PathVariable final String exerciseId,
                        @PathVariable final String studentId) throws IOException {

        return studentService.find(instanceId, courseId, exerciseId, studentId);
    }

    @RequestMapping(value = "{instanceId}/courses/{courseId}/students/{studentId}")
    public Student read(@PathVariable final String instanceId,
                        @PathVariable final String courseId,
                        @PathVariable final String studentId) throws IOException {

        return studentService.findByCourse(instanceId, courseId, studentId);
    }

    @RequestMapping(value = "{instanceId}/students/{studentId}")
    public Student read(@PathVariable final String instanceId, @PathVariable final String studentId) throws IOException {

        return studentService.findByInstance(instanceId, studentId);
    }
}
