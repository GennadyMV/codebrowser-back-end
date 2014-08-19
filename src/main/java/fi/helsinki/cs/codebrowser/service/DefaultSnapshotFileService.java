package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.SnapshotFile;
import fi.helsinki.cs.codebrowser.util.JsonMapper;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSnapshotFileService implements SnapshotFileService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    private final JsonMapper mapper = new JsonMapper();

    @Override
    public Collection<SnapshotFile> findAllBy(final String studentId,
                                              final String courseId,
                                              final String exerciseId,
                                              final String snapshotId) throws IOException {

        final String json =  restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files",
                                                       String.class, studentId, courseId, exerciseId, snapshotId);

        return mapper.readValueToList(json, SnapshotFile.class);
    }

    @Override
    public SnapshotFile findBy(final String studentId,
                               final String courseId,
                               final String exerciseId,
                               final String snapshotId,
                               final String fileId) {

        return restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files/{fileId}",
                                         SnapshotFile.class, studentId, courseId, exerciseId, snapshotId, fileId);
    }

    @Override
    public String findContentBy(final String studentId,
                                final String courseId,
                                final String exerciseId,
                                final String snapshotId,
                                final String fileId) {

        return restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files/{fileId}/content",
                                         String.class, studentId, courseId, exerciseId, snapshotId, fileId);
    }
}
