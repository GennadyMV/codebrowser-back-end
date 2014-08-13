package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultSnapshotService implements SnapshotService {

    @Value("${snapshot.api.url}")
    private String baseURL;

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Override
    public Collection<Snapshot> findAll(final String studentId, final String courseId, final String exerciseId) {

        return restTemplate.getForObject(baseURL + "{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                         List.class, studentId, courseId, exerciseId);
    }

    @Override
    public Snapshot find(final String studentId, final String courseId, final String exerciseId, final String snapshotId) {

        return restTemplate.getForObject(baseURL + "{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                         Snapshot.class, studentId, courseId, exerciseId, snapshotId);
    }
}
