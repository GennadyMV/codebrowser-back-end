package fi.helsinki.cs.codebrowser.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSnapshotService implements SnapshotService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Collection<Snapshot> findAllBy(final String studentId,
                                          final String courseId,
                                          final String exerciseId) throws IOException {

        final String json =  restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                                       String.class, studentId, courseId, exerciseId);

        System.out.println(json);

        return new ObjectMapper().readValue(json, new TypeReference<List<Snapshot>>() { });
    }

    @Override
    public Snapshot findBy(final String studentId,
                           final String courseId,
                           final String exerciseId,
                           final String snapshotId) throws IOException {

        return restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                         Snapshot.class, studentId, courseId, exerciseId, snapshotId);
    }
}
