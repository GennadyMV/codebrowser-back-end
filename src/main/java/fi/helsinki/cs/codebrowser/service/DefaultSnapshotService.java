package fi.helsinki.cs.codebrowser.service;


import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSnapshotService implements SnapshotService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    private final JsonMapper mapper = new JsonMapper();

    @Override
    public Collection<Snapshot> findAllBy(final String studentId,
                                          final String courseId,
                                          final String exerciseId) throws IOException {

        final String json =  snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                                       String.class, studentId, courseId, exerciseId);

        return mapper.readValueToList(json, Snapshot.class);
    }

    @Override
    public Snapshot findBy(final String studentId,
                           final String courseId,
                           final String exerciseId,
                           final String snapshotId) throws IOException {

        final String json = snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                         String.class, studentId, courseId, exerciseId, snapshotId);

        return mapper.readValue(json, Snapshot.class);
    }
}
