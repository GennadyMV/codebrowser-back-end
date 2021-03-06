package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;

import java.io.IOException;
import java.util.Collection;

public interface SnapshotService {

    Collection<Snapshot> findAllBy(String instanceId, String studentId, String courseId, String exerciseId, String level) throws IOException;
    Snapshot findBy(String instanceId, String studentId, String courseId, String exerciseId, String snapshotId, String level) throws IOException;
    byte[] findFilesAsZip(String instanceId, String studentId, String courseId, String exerciseId, String level, String from, int count) throws IOException;

}
