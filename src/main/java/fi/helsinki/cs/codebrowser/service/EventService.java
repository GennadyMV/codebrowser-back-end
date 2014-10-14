package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllBy(String instanceId, String studentId, String courseId, String exerciseId);
    Event findBy(String instanceId, String studentId, String courseId, String exerciseId, String eventId);

}
