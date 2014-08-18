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
@RequestMapping(method = RequestMethod.GET, value = "students", produces = "application/json")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public Collection<Student> list() throws IOException {

        return studentService.findAll();
    }

    @RequestMapping(value = "{studentId}")
    public Student read(@PathVariable final String studentId) {

        return studentService.find(studentId);
    }
}
