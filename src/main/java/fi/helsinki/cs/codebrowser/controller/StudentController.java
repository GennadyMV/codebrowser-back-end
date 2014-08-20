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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "students")
    public Collection<Student> list() throws IOException {

        return studentService.findAll();
    }

    @RequestMapping(value = "courses/{courseId}/students")
    public Collection<Student> list(@PathVariable final String courseId) throws IOException {

        return studentService.findAllBy(courseId);
    }

    @RequestMapping(value = "courses/{courseId}/exercises/{exerciseId}/students")
    public Collection<Student> list(@PathVariable final String courseId,
                                    @PathVariable final String exerciseId) throws IOException {

        return studentService.findAllBy(courseId, exerciseId);
    }

    @RequestMapping(value = "courses/{courseId}/exercises/{exerciseId}/students/{studentId}")
    public Student read(@PathVariable final String courseId,
                               @PathVariable final String exerciseId,
                               @PathVariable final String studentId) throws IOException {

        return studentService.find(courseId, exerciseId, studentId);
    }

    @RequestMapping(value = "courses/{courseId}/students/{studentId}")
    public Student read(@PathVariable final String courseId,
                               @PathVariable final String studentId) throws IOException {

        return studentService.find(courseId, studentId);
    }

    @RequestMapping(value = "students/{studentId}")
    public Student read(@PathVariable final String studentId) throws IOException {

        return studentService.find(studentId);
    }
}
