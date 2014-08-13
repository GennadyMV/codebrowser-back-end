package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.service.ExerciseService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "students/{studentId}/courses/{courseId}/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Exercise> list(@PathVariable final String studentId, @PathVariable final String courseId) {

        return exerciseService.findAll(studentId, courseId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{exerciseId}")
    public Exercise read(@PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId) {

        return exerciseService.find(studentId, courseId, exerciseId);
    }
}
