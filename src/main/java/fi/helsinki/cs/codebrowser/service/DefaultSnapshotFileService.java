package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.SnapshotFile;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultSnapshotFileService implements SnapshotFileService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Override
    public Collection<SnapshotFile> findAllBy(final String instanceId,
                                              final String studentId,
                                              final String courseId,
                                              final String exerciseId,
                                              final String snapshotId) throws IOException {

        return Arrays.asList(snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files",
                                                               SnapshotFile[].class,
                                                               instanceId,
                                                               studentId,
                                                               courseId,
                                                               exerciseId,
                                                               snapshotId));
    }

    @Override
    public SnapshotFile findBy(final String instanceId,
                               final String studentId,
                               final String courseId,
                               final String exerciseId,
                               final String snapshotId,
                               final String fileId) throws IOException {

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files/{fileId}",
                                                 SnapshotFile.class,
                                                 instanceId,
                                                 studentId,
                                                 courseId,
                                                 exerciseId,
                                                 snapshotId,
                                                 fileId);
    }

    @Override
    public String findContentBy(final String instanceId,
                                final String studentId,
                                final String courseId,
                                final String exerciseId,
                                final String snapshotId,
                                final String fileId) {

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/files/{fileId}/content",
                                                 String.class,
                                                 instanceId,
                                                 studentId,
                                                 courseId,
                                                 exerciseId,
                                                 snapshotId,
                                                 fileId);
    }
}
