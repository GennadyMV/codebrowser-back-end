package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Event;
import fi.helsinki.cs.codebrowser.service.EventService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/events",
                          "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/events" },
                produces = "application/json")
public final class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> list(@PathVariable final String instanceId,
                            @PathVariable final String studentId,
                            @PathVariable final String courseId,
                            @PathVariable final String exerciseId) throws IOException {

        return eventService.findAll(instanceId, studentId, courseId, exerciseId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{eventId}")
    public Event read(@PathVariable final String instanceId,
                      @PathVariable final String studentId,
                      @PathVariable final String courseId,
                      @PathVariable final String exerciseId,
                      @PathVariable final String eventId) throws IOException {

        return eventService.find(instanceId, studentId, courseId, exerciseId, eventId);
    }
}
