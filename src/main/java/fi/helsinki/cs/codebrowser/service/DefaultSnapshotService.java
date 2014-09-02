package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultSnapshotService implements SnapshotService {

    @Autowired
    private SnapshotApiRestTemplate snapshotRestTemplate;

    @Override
    public Collection<Snapshot> findAllBy(final String studentId,
                                          final String courseId,
                                          final String exerciseId,
                                          final String level) throws IOException {

        snapshotRestTemplate.addParameter("level", level);

        return Arrays.asList(snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                                               Snapshot[].class,
                                                               studentId,
                                                               courseId,
                                                               exerciseId));
    }

    @Override
    public Snapshot findBy(final String studentId,
                           final String courseId,
                           final String exerciseId,
                           final String snapshotId,
                           final String level) throws IOException {

        snapshotRestTemplate.addParameter("level", level);

        return snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                                 Snapshot.class,
                                                 studentId,
                                                 courseId,
                                                 exerciseId,
                                                 snapshotId);
    }

    @Override
    public byte[] findAllFilesAsZip(final String studentId,
                                    final String courseId,
                                    final String exerciseId,
                                    final String level) throws IOException {

        snapshotRestTemplate.addParameter("level", level);

        return snapshotRestTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/files.zip",
                                                 byte[].class,
                                                 studentId,
                                                 courseId,
                                                 exerciseId);
    }
}
