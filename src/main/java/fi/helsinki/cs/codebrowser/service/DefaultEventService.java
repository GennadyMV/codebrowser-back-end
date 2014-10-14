package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Event;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Override
    public List<Event> findAll(final String instanceId,
                                final String studentId,
                                final String courseId,
                                final String exerciseId) {

        return Arrays.asList(snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/events",
                                                               Event[].class,
                                                               instanceId,
                                                               studentId,
                                                               courseId,
                                                               exerciseId));
    }

    @Override
    public Event find(final String instanceId,
                      final String studentId,
                      final String courseId,
                      final String exerciseId,
                      final String eventId) {

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/events/{eventId}",
                                                 Event.class,
                                                 instanceId,
                                                 studentId,
                                                 courseId,
                                                 exerciseId,
                                                 eventId);
    }
}
