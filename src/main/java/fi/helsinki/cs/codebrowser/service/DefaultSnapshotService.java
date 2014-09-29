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
    public Collection<Snapshot> findAllBy(final String instanceId,
                                          final String studentId,
                                          final String courseId,
                                          final String exerciseId,
                                          final String level) throws IOException {

        snapshotRestTemplate.addParameter("level", level);

        return Arrays.asList(snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                                               Snapshot[].class,
                                                               instanceId,
                                                               studentId,
                                                               courseId,
                                                               exerciseId));
    }

    @Override
    public Snapshot findBy(final String instanceId,
                           final String studentId,
                           final String courseId,
                           final String exerciseId,
                           final String snapshotId,
                           final String level) throws IOException {

        snapshotRestTemplate.addParameter("level", level);

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                                 Snapshot.class,
                                                 instanceId,
                                                 studentId,
                                                 courseId,
                                                 exerciseId,
                                                 snapshotId);
    }

    @Override
    public byte[] findFilesAsZip(final String instanceId,
                                    final String studentId,
                                    final String courseId,
                                    final String exerciseId,
                                    final String level,
                                    final String from,
                                    final int count) throws IOException {

        snapshotRestTemplate.addParameter("level", level);
        snapshotRestTemplate.addParameter("from", from);
        snapshotRestTemplate.addParameter("count", Integer.toString(count));

        return snapshotRestTemplate.getForObject("{instanceId}/participants/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/files.zip",
                                                 byte[].class,
                                                 instanceId,
                                                 studentId,
                                                 courseId,
                                                 exerciseId);
    }
}
