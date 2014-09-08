package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.service.ExerciseService;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
public final class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "{instanceId}/courses/{courseId}/exercises")
    public Collection<Exercise> list(@PathVariable final String instanceId,
                                     @PathVariable final String courseId) throws IOException {

        return exerciseService.findAllBy(instanceId, courseId);
    }

    @RequestMapping(value = "{instanceId}/students/{studentId}/courses/{courseId}/exercises")
    public Collection<Exercise> list(@PathVariable final String instanceId,
                                     @PathVariable final String studentId,
                                     @PathVariable final String courseId) throws IOException {

        return exerciseService.findAllBy(instanceId, studentId, courseId);
    }

    @RequestMapping(value = "{instanceId}/courses/{courseId}/exercises/{exerciseId}")
    public Exercise read(@PathVariable final String instanceId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId) throws IOException {

        return exerciseService.findBy(instanceId, courseId, exerciseId);
    }

    @RequestMapping(value = "{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}")
    public Exercise read(@PathVariable final String instanceId,
                         @PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId) throws IOException {

        return exerciseService.findBy(instanceId, studentId, courseId, exerciseId);
    }
}
